package com.mark.wsp;

import lombok.Data;

@Data
public class Slot {

    private long id;// 库位id
    private long poiId;// 门店id
    private long whAreaId;// 库区id
    private String code;// 库位编码
    private String name;// 库位名称
    private String nameSpell;// 库位名称全拼
    private int type;// 库位类型(101:分拣位 102:存储位 103:原料位 104:包材位 105:退换货位 106:残次品位 107:暂存位 108:越库位)
    private long departmentId;// 所属部门id,没有绑定部门值为-1
    private long ctime;// 创建时间
    private int isBuiltin;// 是否是内置(0:非内置 1:内置)
    private int canSale;// 是否可销售， 0:不可销售 1:可销售
    private int canPacking;// 是否可分拣， 0:不可分拣 1:可分拣
    private int saleType;// 销售属性， 1:常规 2:促销位 3:堆头
    private int packingOrder;// 分拣排序
    private int groudingOrder;// 上架排序
    private int canLotCombine;// 是否允许批次混放， 0:不可混放 1:可混放
    private int canSupplierCombine;// 是否允许不同供应商混放， 0:不可混放 1:可混放
    private int canSupply;// 是否可补货，0：不能做补货位，1：能做补货位
    private int supplyOrder;// 补货顺序
    private int bizId;
    private int storageType; //存储类型 10=整箱 20=拆零
}