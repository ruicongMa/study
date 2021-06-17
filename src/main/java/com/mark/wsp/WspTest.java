package com.mark.wsp;

import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mark
 * @date 2020/11/9 11:11
 */
public class WspTest {

    public static void main(String[] args) {

        List<? extends Param> params = getParams();
        List<FilterResult> filterResults = getFilterResults();
        List<ScorerInfo> scorerInfos = getScorerInfos();

        DefaultScorerEngine scorerEngine = new DefaultScorerEngine();
        List<ScoreResult<Slot>> scoreResults = scorerEngine.run(scorerInfos, filterResults, params, -1);
        System.out.println(scoreResults);
        DefaultScorerEngine.executorService.shutdown();
    }

    private static List<FilterResult> getFilterResults(){
        List<FilterResult> filterResults = new ArrayList<>();

        //skuId = 1对应两个库位
        //===============================
        SlotParam sku1 = new SlotParam();
        sku1.setOwnerId(1);
        sku1.setStoreId(1);
        sku1.setDepartmentId(1);
        sku1.setLotAttrId(1);
        sku1.setSkuId(1);

        List<Slot> results = new ArrayList<>();
        Slot slot1 = new Slot();
        slot1.setId(1000);
        slot1.setBizId(1);
        slot1.setType(101);
        results.add(slot1);

        Slot slot2 = new Slot();
        slot2.setId(1001);
        slot2.setBizId(1);
        slot2.setType(101);
        results.add(slot2);

        FilterResult<Slot> filterResult = new FilterResult<>(sku1,results);
        filterResults.add(filterResult);
        //===============================

        //skuId = 2对应两个库位
        //===============================
        SlotParam sku2 = new SlotParam();
        sku2.setOwnerId(2);
        sku2.setStoreId(2);
        sku2.setDepartmentId(2);
        sku2.setLotAttrId(2);
        sku2.setSkuId(2);

        List<Slot> results2 = new ArrayList<>();
        Slot slot21 = new Slot();
        slot21.setId(1000);
        slot21.setBizId(1);
        slot21.setType(101);
        results2.add(slot21);

        Slot slot22 = new Slot();
        slot22.setId(1001);
        slot22.setBizId(1);
        slot22.setType(101);
        results2.add(slot22);

        FilterResult<Slot> filterResult2 = new FilterResult<>(sku2,results2);
        filterResults.add(filterResult2);
        //===============================


        return filterResults;
    }

    private static List<ScorerInfo> getScorerInfos(){
        List<ScorerInfo> scorerInfos = new ArrayList<>();

        ScorerInfo scorerInfo1 = new ScorerInfo();//按照库位类型打分
        scorerInfo1.setType(407);
        scorerInfo1.setPriority(1);
        scorerInfo1.setContent("103,101,102");
        scorerInfos.add(scorerInfo1);

        ScorerInfo scorerInfo2 = new ScorerInfo();//库位库存大于0
        scorerInfo2.setType(401);
        scorerInfo2.setPriority(2);
        // scorerInfo1.setContent();
        scorerInfos.add(scorerInfo2);
        return scorerInfos;
    }

    private static List<? extends Param> getParams(){
        List<SlotParam> params = new ArrayList<>();
        SlotParam sku1 = new SlotParam();
        sku1.setOwnerId(1);
        sku1.setStoreId(1);
        sku1.setDepartmentId(1);
        sku1.setLotAttrId(1);
        sku1.setSkuId(1);
        params.add(sku1);

        SlotParam sku2 = new SlotParam();
        sku2.setOwnerId(2);
        sku2.setStoreId(2);
        sku2.setDepartmentId(2);
        sku2.setLotAttrId(2);
        sku2.setSkuId(2);
        params.add(sku2);
        return params;
    }
}
