package com.mark.wsp;

import java.util.HashSet;
import java.util.Set;

public  class AbstractSlotPositiveInvScorer implements Scorer {

    @Override
    public float score(Object candidate, ScorerContext context, Param param)  {

        Slot slot = (Slot)candidate;

        SlotParam slotParam = (SlotParam) param;

        //是否库存大于0
        Object positiveInv = context.get(String.valueOf(slotParam.getSkuId()));
        Set<Long> positiveInvSlots;
        if (positiveInv != null && positiveInv instanceof Set) {
            positiveInvSlots = (Set<Long>) positiveInv;
            if (positiveInvSlots.contains(slot.getId())) {
                return 1.0f;
            }
        } else {
            positiveInvSlots = getPositiveInvSlots(slotParam.getSkuId());
            context.addArgument(String.valueOf(slotParam.getSkuId()), positiveInvSlots);

            if (positiveInvSlots.contains(slot.getId())) {
                return 1.0f;
            }
        }

        return 0.0f;
    }

    private Set<Long> getPositiveInvSlots(long skuId){
        Set<Long> set1 = new HashSet<>();
        if(skuId == 1){
            set1.add(1000L);
        }else if(skuId == 2){
            set1.add(1001L);
        }
        return set1;
    }



    @Override
    public ScorerConfiguration parseConfiguration(ScorerInfo scorerInfo) {
        return null;
    }
}