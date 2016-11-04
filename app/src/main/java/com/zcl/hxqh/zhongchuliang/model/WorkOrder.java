package com.zcl.hxqh.zhongchuliang.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by apple on 15/6/3.
 * 出库WorkOrder
 */
@JsonType
public class WorkOrder extends Entity {
    private static final String TAG = "WorkOrder";

    @JsonField(fieldName = "wonum")
    public String wonum; //工单

    @JsonField(fieldName = "description")
    public String description; //描述

    @JsonField(fieldName = "n_location")
    public String n_location; //库房

    @JsonField(fieldName = "locdesc")
    public String locdesc; //库房描述

    @JsonField(fieldName = "n_purpose")
    public String n_purpose; //领用用途

    @JsonField(fieldName = "department")
    public String department; //部门

    @JsonField(fieldName = "status")
    public String status; //状态

    @JsonField(fieldName = "statusdate")
    public String statusdate; //状态日期

    @JsonField(fieldName = "reportname")
    public String reportname; //申请人

    @JsonField(fieldName = "reportdate")
    public String reportdate; //申请日期

    @JsonField(fieldName = "onbehalfof")
    public String onbehalfof; //领用人




    public String getWonum() {
        return wonum;
    }

    public void setWonum(String wonum) {
        this.wonum = wonum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOnbehalfof() {
        return onbehalfof;
    }

    public void setOnbehalfof(String onbehalfof) {
        this.onbehalfof = onbehalfof;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getN_location() {
        return n_location;
    }

    public void setN_location(String n_location) {
        this.n_location = n_location;
    }

    public String getLocdesc() {
        return locdesc;
    }

    public void setLocdesc(String locdesc) {
        this.locdesc = locdesc;
    }

    public String getN_purpose() {
        return n_purpose;
    }

    public void setN_purpose(String n_purpose) {
        this.n_purpose = n_purpose;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
