package com.mark.wsp;

public class SlotParam implements Param{

    private long skuId;
    private long lotAttrId;
    private long departmentId;
    private long storeId;
    private long ownerId;

    public SlotParam(long skuId, long lotAttrId, long departmentId, long storeId,long ownerId) {
        this.skuId = skuId;
        this.lotAttrId = lotAttrId;
        this.departmentId = departmentId;
        this.storeId = storeId;
        this.ownerId = ownerId;
    }

    public SlotParam() {
    }

    public long getSkuId() {
        return skuId;
    }

    public void setSkuId(long skuId) {
        this.skuId = skuId;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getLotAttrId() {
        return lotAttrId;
    }

    public void setLotAttrId(long lotAttrId) {
        this.lotAttrId = lotAttrId;
    }

    @Override
    public String toString() {
        return "SlotParam{" +
                "skuId=" + skuId +
                ", lotAttrId=" + lotAttrId +
                ", departmentId=" + departmentId +
                ", storeId=" + storeId +
                ", ownerId=" + ownerId +
                '}';
    }
}