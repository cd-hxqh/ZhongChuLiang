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
}
