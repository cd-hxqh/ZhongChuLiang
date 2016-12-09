package com.zcl.hxqh.zhongchuliang.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by apple on 15/6/3.
 * 设备表
 */
@JsonType
public class Asset extends Entity {

    @JsonField(fieldName = "assetnum")
    public String assetnum; //设备编号
    @JsonField(fieldName = "description")
    public String description; //描述
    @JsonField(fieldName = "n_locaname")
    public String n_locaname; //位置
    @JsonField(fieldName = "n_modelnum")
    public String n_modelnum; //设备全称
    @JsonField(fieldName = "assettype")
    public String assettype; //类型
    @JsonField(fieldName = "parent")
    public String parent; //父级
    @JsonField(fieldName = "status")
    public String status; //状态

    public String getAssetnum() {
        return assetnum;
    }

    public void setAssetnum(String assetnum) {
        this.assetnum = assetnum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getN_locaname() {
        return n_locaname;
    }

    public void setN_locaname(String n_locaname) {
        this.n_locaname = n_locaname;
    }

    public String getN_modelnum() {
        return n_modelnum;
    }

    public void setN_modelnum(String n_modelnum) {
        this.n_modelnum = n_modelnum;
    }

    public String getAssettype() {
        return assettype;
    }

    public void setAssettype(String assettype) {
        this.assettype = assettype;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
