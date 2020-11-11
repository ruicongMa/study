package com.mark.wsp;

import com.google.common.collect.MinMaxPriorityQueue;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Mark
 * @date 2020/11/9 10:57
 */
public class DefaultScorerEngine<T> {

    Map<Integer,Scorer<T>> scorerRegistry = new HashMap<>();

    public DefaultScorerEngine(){
        scorerRegistry.put(407,new AbstractSlotTypeScorer());//按照库位类型打分
        scorerRegistry.put(401,new AbstractSlotPositiveInvScorer());//按照库存大于0打分
    }

    static ExecutorService executorService = Executors.newCachedThreadPool();

    public List<ScoreResult<T>> run(List<ScorerInfo> scorerInfos, List<FilterResult<T>> filterResults, List<? extends Param> params, int num)  {

        List<Tuple<Scorer<T>, ScorerContext>> scorerTuples = getScorers(scorerInfos, params);

        List<ScoreResult<T>> results = new ArrayList<>();
        List<Action<ScoreResult<T>>> payload = new ArrayList<>();
        for (FilterResult<T> filterResult : filterResults) {
            ActionUnit actionUnit = new ActionUnit(scorerTuples, filterResult, num);
            payload.add(() -> actionUnit.run());
        }


        BatchRunner<ScoreResult<T>> batchRunner = new BatchRunner<ScoreResult<T>>(payload, this.executorService, 10, 2000);

        try {
            results = batchRunner.run();
        }  catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    /**
     * 针对一个Param的执行单元
     */
    private class ActionUnit {
        private final List<Tuple<Scorer<T>, ScorerContext>> scorerTuples;
        private final FilterResult<T> filterResult;
        private final int num;

        private int loopCount = 0;

        public ActionUnit(List<Tuple<Scorer<T>, ScorerContext>> scorerTuples, FilterResult<T> filterResult, int num) {
            this.scorerTuples = scorerTuples;
            this.filterResult = filterResult;
            if (num <= 0) {
                this.num = filterResult.getResults().size();
            } else {
                this.num = num;
            }

        }


        public ScoreResult<T> run()  {

            List<Score<T>> scoreList = new ArrayList<>();

            if (filterResult.getResults() == null || filterResult.getResults().size() <= 0) {
                return new ScoreResult<T>(filterResult.getParam(), scoreList);
            }

            Comparator<Score<T>> comparator = new ScoreComparator(getScorerWrappers());
            MinMaxPriorityQueue<Score<T>> heap_topN = MinMaxPriorityQueue.<Score<T>>orderedBy(comparator)
                    .maximumSize(this.num)
                    .create();


            for (T candidate : filterResult.getResults()) {
                heap_topN.add(new Score<T>(candidate, scorerTuples.size()));
            }


            while (true) {
                Score<T> score = heap_topN.poll();
                if (score == null) {
                    break;
                }
                scoreList.add(score);
            }

            return new ScoreResult<T>(filterResult.getParam(), scoreList);
        }

        private List<ScorerWrapper<T>> getScorerWrappers() {

            List<ScorerWrapper<T>> wrappers = new ArrayList<>();
            if (this.scorerTuples == null) {
                return wrappers;
            }
            for (Tuple<Scorer<T>, ScorerContext> tuple : scorerTuples) {
                wrappers.add(new ScorerWrapper<T>(tuple._1(), tuple._2(), filterResult.getParam()));
            }
            return wrappers;
        }

    }


    private class ScoreComparator implements Comparator<Score<T>> {

        private final List<ScorerWrapper<T>> scorerWrappers;

        private ScoreComparator(List<ScorerWrapper<T>> scorerWrappers) {
            this.scorerWrappers = scorerWrappers;
        }


        float getScoreValue(Score<T> score, int i)  {
            if (score.getScores()[i] == null) {
                score.getScores()[i] = scorerWrappers.get(i).score(score.getEntity());
            }
            return score.getScores()[i];
        }

        @Override
        public int compare(Score<T> o1, Score<T> o2) {
            for (int i = 0; i < scorerWrappers.size(); i++) {
                try {
                    int r = 0;
                    if ((r = Float.compare(getScoreValue(o2, i), getScoreValue(o1, i))) != 0) {
                        return r;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            return 0;
        }
    }



    private List<Tuple<Scorer<T>, ScorerContext>> getScorers(List<ScorerInfo> scorerInfos,  List<? extends Param> params) {

        List<Tuple<Scorer<T>, ScorerContext>> tuples = new ArrayList<>();

        if (scorerInfos == null || scorerInfos.size() == 0) {
            return tuples;
        }

        scorerInfos.sort(new Comparator<ScorerInfo>() {
            @Override
            public int compare(ScorerInfo o1, ScorerInfo o2) {
                return Integer.compare(o1.getPriority(), o2.getPriority());
            }
        });

        if (scorerInfos != null && scorerInfos.size() > 0) {

            for (ScorerInfo scorerInfo : scorerInfos) {
                Scorer<T> scorer = scorerRegistry.get(scorerInfo.getType());
                if (scorer == null) {
                }
                ScorerConfiguration configuration = null;
                configuration = scorer.parseConfiguration(scorerInfo);
                ScorerContext context = new ScorerContext(configuration);

                // if (scorer instanceof BatchInitScorer) {
                //     ((BatchInitScorer) scorer).batchInit(context, params);
                // }
                tuples.add(new Tuple<Scorer<T>, ScorerContext>(scorer, context));
            }

        }
        return tuples;
    }

}
