package com.mark.wsp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class AbstractSlotTypeScorer implements Scorer {

    @Override
    public float score(Object candidate, ScorerContext context, Param param) {
        Slot slot = (Slot)candidate;
        SlotTypeScorerConfiguration slotTypeScorerConfiguration = (SlotTypeScorerConfiguration) context.getScorerConfiguration();
        Float score = slotTypeScorerConfiguration.getSlotTypeScoreMap().get(slot.getType());
        return score != null ? score : 0;
    }

    @Override
    public ScorerConfiguration parseConfiguration(ScorerInfo scorerInfo) {
        List<Integer> slotTypeRuleList = new ArrayList<>();
        slotTypeRuleList.add(103);
        slotTypeRuleList.add(101);
        slotTypeRuleList.add(102);
        Map<Integer, Float> slotTypeScoreMap = new HashMap<>();
        //根据库位类型进行打分
        for (int i = 0; i < slotTypeRuleList.size(); i++) {
            //按照db中的规则顺序 如原料位优先级最高,分拣位其次,存储位最低  那么在库中的顺序就是103 101 102
            float score = 1.0f / slotTypeRuleList.size() * (slotTypeRuleList.size() - i);
            slotTypeScoreMap.put(slotTypeRuleList.get(i), score);
        }
        return new SlotTypeScorerConfiguration(slotTypeScoreMap);
    }

    class SlotTypeScorerConfiguration implements ScorerConfiguration {

        private Map<Integer, Float> slotTypeScoreMap;

        SlotTypeScorerConfiguration(Map<Integer, Float> slotTypeScoreMap) {
            this.slotTypeScoreMap = slotTypeScoreMap;
        }


        public Map<Integer, Float> getSlotTypeScoreMap() {
            return slotTypeScoreMap;
        }
    }

}