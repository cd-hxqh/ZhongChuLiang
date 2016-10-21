package com.zcl.hxqh.zhongchuliang.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by think on 2015/12/12.
 * 库存转移临时类
 */
@JsonType
public class Matrectrans extends Entity {

    @JsonField(fieldName = "polinenum")
    public String polinenum;//PO行
    @JsonField(fieldName = "itemnum")
    public String itemnum;//项目
    @JsonField(fieldName = "description")
    public String description;//描述
    @JsonField(fieldName = "type")
    public String type;//型号
    @JsonField(fieldName = "receiptquantity")
    public String receiptquantity;//数量
    @JsonField(fieldName = "quantity")
    public String quantity;//数量
    @JsonField(fieldName = "receivedunit")
    public String receivedunit;//单位
    @JsonField(fieldName = "issuetype")
    public String issuetype;//类型
    @JsonField(fieldName = "status")
    public String status;//状态
    @JsonField(fieldName = "actualdate")
    public String actualdate;//到货时间
    @JsonField(fieldName = "statusdate")
    public String statusdate;//入库时间
    @JsonField(fieldName = "curbaltotal")
    public String curbaltotal;//仓库当前余量
    @JsonField(fieldName = "linecost")
    public String linecost;//行成本
    @JsonField(fieldName = "fromstoreloc")
    public String fromstoreloc;//原仓库
    @JsonField(fieldName = "frombin")
    public String frombin;//原库位号
    @JsonField(fieldName = "tostoreloc")
    public String tostoreloc;//目标仓库
    @JsonField(fieldName = "tobin")
    public String tobin;//目标库位号
    @JsonField(fieldName = "fromlot")
    public String fromlot;//原批次
    @JsonField(fieldName = "tolot")
    public String tolot;//目标批次

    @JsonField(fieldName = "ponum")
    public String ponum;//单号


    public String getFromlot() {
        return fromlot;
    }

    public void setFromlot(String fromlot) {
        this.fromlot = fromlot;
    }

    public String getTolot() {
        return tolot;
    }

    public void setTolot(String tolot) {
        this.tolot = tolot;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReceiptquantity() {
        return receiptquantity;
    }

    public void setReceiptquantity(String receiptquantity) {
        this.receiptquantity = receiptquantity;
    }

    public String getCurbaltotal() {
        return curbaltotal;
    }

    public void setCurbaltotal(String curbaltotal) {
        this.curbaltotal = curbaltotal;
    }

    public String getLinecost() {
        return linecost;
    }

    public void setLinecost(String linecost) {
        this.linecost = linecost;
    }

    public String getFromstoreloc() {
        return fromstoreloc;
    }

    public void setFromstoreloc(String fromstoreloc) {
        this.fromstoreloc = fromstoreloc;
    }

    public String getFrombin() {
        return frombin;
    }

    public void setFrombin(String frombin) {
        this.frombin = frombin;
    }

    public String getTostoreloc() {
        return tostoreloc;
    }

    public void setTostoreloc(String tostoreloc) {
        this.tostoreloc = tostoreloc;
    }

    public String getTobin() {
        return tobin;
    }

    public void setTobin(String tobin) {
        this.tobin = tobin;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPonum() {
        return ponum;
    }

    public void setPonum(String ponum) {
        this.ponum = ponum;
    }

    public String getPolinenum() {
        return polinenum;
    }

    public void setPolinenum(String polinenum) {
        this.polinenum = polinenum;
    }

    public String getReceivedunit() {
        return receivedunit;
    }

    public void setReceivedunit(String receivedunit) {
        this.receivedunit = receivedunit;
    }

    public String getIssuetype() {
        return issuetype;
    }

    public void setIssuetype(String issuetype) {
        this.issuetype = issuetype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActualdate() {
        return actualdate;
    }

    public void setActualdate(String actualdate) {
        this.actualdate = actualdate;
    }

    public String getStatusdate() {
        return statusdate;
    }

    public void setStatusdate(String statusdate) {
        this.statusdate = statusdate;
    }
}
