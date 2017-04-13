package com.zcl.hxqh.zhongchuliang.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by apple on 15/6/3.
 * 位置表
 */
@JsonType
public class Invreserve extends Entity {
    @JsonField(fieldName = "wonum")
    public String wonum; //所属工单号

    @JsonField(fieldName = "requestnum")
    public String requestnum; //请求
    @JsonField(fieldName = "itemnum")
    public String itemnum; //项目
    @JsonField(fieldName = "location")
    public String location; //库房
    @JsonField(fieldName = "description")
    public String description; //描述
    @JsonField(fieldName = "reservedqty")
    public String reservedqty; //已预留数量
    @JsonField(fieldName = "restype")
    public String restype; //预留类型
    @JsonField(fieldName = "issueto")
    public String issueto; //发放到
    @JsonField(fieldName = "n_cardnum")
    public String n_cardnum; //卡号
    @JsonField(fieldName = "enterby")
    public String enterby; //使用人

    public boolean ischeck;//是否被选中

    public String getWonum() {
        return wonum;
    }

    public void setWonum(String wonum) {
        this.wonum = wonum;
    }

    public String getRequestnum() {
        return requestnum;
    }

    public void setRequestnum(String requestnum) {
        this.requestnum = requestnum;
    }

    public String getItemnum() {
        return itemnum;
    }

    public void setItemnum(String itemnum) {
        this.itemnum = itemnum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReservedqty() {
        return reservedqty;
    }

    public void setReservedqty(String reservedqty) {
        this.reservedqty = reservedqty;
    }

    public String getRestype() {
        return restype;
    }

    public void setRestype(String restype) {
        this.restype = restype;
    }

    public String getIssueto() {
        return issueto;
    }

    public void setIssueto(String issueto) {
        this.issueto = issueto;
    }

    public String getN_cardnum() {
        return n_cardnum;
    }

    public void setN_cardnum(String n_cardnum) {
        this.n_cardnum = n_cardnum;
    }

    public String getEnterby() {
        return enterby;
    }

    public void setEnterby(String enterby) {
        this.enterby = enterby;
    }
}
