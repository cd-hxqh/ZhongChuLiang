package com.zcl.hxqh.zhongchuliang.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 15/12/16.
 * 入库管理物料
 */
@JsonType
public class Poline extends Entity {

    @JsonField(fieldName = "itemnum")
    public String itemnum; //项目编号
    @JsonField(fieldName = "polinenum")
    public String polinenum;//行号
    @JsonField(fieldName = "description")
    public String description; //描述
    @JsonField(fieldName = "modelnum")
    public String modelnum; //型号
    @JsonField(fieldName = "orderqty")
    public String orderqty; //订购量
    @JsonField(fieldName = "orderunit")
    public String orderunit; //发放单位
    @JsonField(fieldName = "remark")
    public String remark; //备注
    @JsonField(fieldName = "storeloc")
    public String storeloc; //目标库房

    public boolean ischeck;//是否被选中

    public String getPolinenum() {
        return polinenum;
    }

    public void setPolinenum(String polinenum) {
        this.polinenum = polinenum;
    }

    public String getItemnum() {
        return itemnum;
    }

    public void setItemnum(String itemnum) {
        this.itemnum = itemnum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrderunit() {
        return orderunit;
    }

    public void setOrderunit(String orderunit) {
        this.orderunit = orderunit;
    }

    public String getOrderqty() {
        return orderqty;
    }

    public void setOrderqty(String orderqty) {
        this.orderqty = orderqty;
    }

    public String getStoreloc() {
        return storeloc;
    }

    public void setStoreloc(String storeloc) {
        this.storeloc = storeloc;
    }

    public String getModelnum() {
        return modelnum;
    }

    public void setModelnum(String modelnum) {
        this.modelnum = modelnum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean ischeck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }
}
