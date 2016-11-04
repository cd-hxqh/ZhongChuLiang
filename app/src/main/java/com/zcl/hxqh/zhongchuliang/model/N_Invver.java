package com.zcl.hxqh.zhongchuliang.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by apple on 15/6/3.
 * 库存盘点
 */
@JsonType
public class N_Invver extends Entity  {
    private static final String TAG = "Inventory";

    @JsonField(fieldName = "invvernum")
    public String invvernum; //编号

    @JsonField(fieldName = "description")
    public String description; //描述

    @JsonField(fieldName = "location")
    public String location; //库房

    @JsonField(fieldName = "locdesc")
    public String locdesc; //库房描述

    @JsonField(fieldName = "status")
    public String status; //状态
    @JsonField(fieldName = "statusdate")
    public String statusdate; //状态时间
    @JsonField(fieldName = "reportname")
    public String reportname; //创建人
    @JsonField(fieldName = "reportdate")
    public String reportdate; //创建时间


    public String getInvvernum() {
        return invvernum;
    }

    public void setInvvernum(String invvernum) {
        this.invvernum = invvernum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocdesc() {
        return locdesc;
    }

    public void setLocdesc(String locdesc) {
        this.locdesc = locdesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusdate() {
        return statusdate;
    }

    public void setStatusdate(String statusdate) {
        this.statusdate = statusdate;
    }

    public String getReportname() {
        return reportname;
    }

    public void setReportname(String reportname) {
        this.reportname = reportname;
    }

    public String getReportdate() {
        return reportdate;
    }

    public void setReportdate(String reportdate) {
        this.reportdate = reportdate;
    }
}
