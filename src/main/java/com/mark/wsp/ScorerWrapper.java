package com.mark.wsp;

public class ScorerWrapper<T> {

    private final Scorer<T> scorer;
    private final ScorerContext context;
    private final Param param;

    public ScorerWrapper(Scorer<T> scorer, ScorerContext context, Param param) {
        this.scorer = scorer;
        this.context = context;
        this.param = param;
    }

    public float score(T candidate) {
        float scoreValue = this.scorer.score(candidate,this.context,param);
        return scoreValue;
    }

}