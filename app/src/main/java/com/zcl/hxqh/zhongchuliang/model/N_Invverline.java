package com.zcl.hxqh.zhongchuliang.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by apple on 15/6/3.
 * 库存盘点行
 */
@JsonType
public class N_Invverline extends Entity  {
    private static final String TAG = "N_Invverline";

    @JsonField(fieldName = "invvernum")
    public String invvernum; //主表编号

    @JsonField(fieldName = "sn")
    public String sn; //序号

    @JsonField(fieldName = "itemnum")
    public String itemnum; //项目

    @JsonField(fieldName = "itemdesc")
    public String itemdesc; //项目描述

    @JsonField(fieldName = "physcntdate")
    public String physcntdate; //计划盘点日期

    @JsonField(fieldName = "binnum")
    public String binnum; //货柜
    @JsonField(fieldName = "lotnum")
    public String lotnum; //批次
    @JsonField(fieldName = "physcnt")
    public String physcnt; //实际库存
    @JsonField(fieldName = "itemin19")
    public String itemin19; //规格型号
    @JsonField(fieldName = "avblbalance")
    public String avblbalance; //可用量

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getItemnum() {
        return itemnum;
    }

    public void setItemnum(String itemnum) {
        this.itemnum = itemnum;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public String getPhyscntdate() {
        return physcntdate;
    }

    public void setPhyscntdate(String physcntdate) {
        this.physcntdate = physcntdate;
    }

    public String getBinnum() {
        return binnum;
    }

    public void setBinnum(String binnum) {
        this.binnum = binnum;
    }

    public String getLotnum() {
        return lotnum;
    }

    public void setLotnum(String lotnum) {
        this.lotnum = lotnum;
    }

    public String getPhyscnt() {
        return physcnt;
    }

    public void setPhyscnt(String physcnt) {
        this.physcnt = physcnt;
    }

    public String getItemin19() {
        return itemin19;
    }

    public void setItemin19(String itemin19) {
        this.itemin19 = itemin19;
    }

    public String getAvblbalance() {
        return avblbalance;
    }

    public void setAvblbalance(String avblbalance) {
        this.avblbalance = avblbalance;
    }

    public String getInvvernum() {
        return invvernum;
    }

    public void setInvvernum(String invvernum) {
        this.invvernum = invvernum;
    }
}
