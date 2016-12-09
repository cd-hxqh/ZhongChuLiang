package com.zcl.hxqh.zhongchuliang.model;

import com.instagram.common.json.annotation.JsonField;
import com.instagram.common.json.annotation.JsonType;

/**
 * Created by apple on 15/6/3.
 * 人员表
 */
@JsonType
public class Person extends Entity {

    @JsonField(fieldName = "n_cardnum")
    public String n_cardnum; //卡号
    @JsonField(fieldName = "displayname")
    public String displayname; //使用人

    public String getN_cardnum() {
        return n_cardnum;
    }

    public void setN_cardnum(String n_cardnum) {
        this.n_cardnum = n_cardnum;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }
}
