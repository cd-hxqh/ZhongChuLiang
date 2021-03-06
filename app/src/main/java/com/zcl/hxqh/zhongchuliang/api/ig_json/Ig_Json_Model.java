// Copyright 2004-present Facebook. All Rights Reserved.

package com.zcl.hxqh.zhongchuliang.api.ig_json;


import com.zcl.hxqh.zhongchuliang.api.ig_json.impl.Asset_JsonHelper;
import com.zcl.hxqh.zhongchuliang.api.ig_json.impl.Invbalances_JsonHelper;
import com.zcl.hxqh.zhongchuliang.api.ig_json.impl.Inventory_JsonHelper;
import com.zcl.hxqh.zhongchuliang.api.ig_json.impl.Invreserve_JsonHelper;
import com.zcl.hxqh.zhongchuliang.api.ig_json.impl.Locations_JsonHelper;
import com.zcl.hxqh.zhongchuliang.api.ig_json.impl.Matrectrans_JsonHelper;
import com.zcl.hxqh.zhongchuliang.api.ig_json.impl.N_Invver_JsonHelper;
import com.zcl.hxqh.zhongchuliang.api.ig_json.impl.N_Invverline_JsonHelper;
import com.zcl.hxqh.zhongchuliang.api.ig_json.impl.Person_JsonHelper;
import com.zcl.hxqh.zhongchuliang.api.ig_json.impl.Po_JsonHelper;
import com.zcl.hxqh.zhongchuliang.api.ig_json.impl.Poline_JsonHelper;
import com.zcl.hxqh.zhongchuliang.api.ig_json.impl.WorkOrder_JsonHelper;
import com.zcl.hxqh.zhongchuliang.model.Asset;
import com.zcl.hxqh.zhongchuliang.model.Invbalances;
import com.zcl.hxqh.zhongchuliang.model.Inventory;
import com.zcl.hxqh.zhongchuliang.model.Invreserve;
import com.zcl.hxqh.zhongchuliang.model.Locations;
import com.zcl.hxqh.zhongchuliang.model.Matrectrans;
import com.zcl.hxqh.zhongchuliang.model.N_Invver;
import com.zcl.hxqh.zhongchuliang.model.N_Invverline;
import com.zcl.hxqh.zhongchuliang.model.Person;
import com.zcl.hxqh.zhongchuliang.model.Po;
import com.zcl.hxqh.zhongchuliang.model.Poline;
import com.zcl.hxqh.zhongchuliang.model.WorkOrder;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Helper class to parse the model.
 */
public class Ig_Json_Model {

    private static final String TAG = "Ig_Json_Model";


    /**
     * 出库管理
     */
    public static ArrayList<WorkOrder> parseWorkOrderFromString(String input) throws IOException {
        return WorkOrder_JsonHelper.parseFromJsonList(input);
    }
    /**
     * 出库管理Invreserve
     */
    public static ArrayList<Invreserve> parseInvreserveFromString(String input) throws IOException {
        return Invreserve_JsonHelper.parseFromJsonList(input);
    }

    /**
     *入库管理采购单*
     */
    public static ArrayList<Po> parsePoFromString(String input) throws IOException {
        return Po_JsonHelper.parseFromJsonList(input);
    }
    /**
     *入库管理采购单*
     */
    public static ArrayList<Matrectrans> parseMatrectransFromString(String input) throws IOException {
        return Matrectrans_JsonHelper.parseFromJsonList(input);
    }
    /**
     *入库管理物料单*
     */
    public static ArrayList<Poline> parsePolineFromString(String input) throws IOException {
        return Poline_JsonHelper.parseFromJsonList(input);
    }

    /**
     * 库存盘点
     */
    public static ArrayList<N_Invver> parseN_InvverFromString(String input) throws IOException {
        return N_Invver_JsonHelper.parseFromJsonList(input);
    }

    /**
     * 库存盘点
     */
    public static ArrayList<N_Invverline> parseN_InvverlineFromString(String input) throws IOException {
        return N_Invverline_JsonHelper.parseFromJsonList(input);
    }

    /**
     * 库存盘点
     */
    public static ArrayList<Asset> parseAssetFromString(String input) throws IOException {
        return Asset_JsonHelper.parseFromJsonList(input);
    }

    /**
     * 库存使用情况
     */
    public static ArrayList<Inventory> parseInventoryFromString(String input) throws IOException {
        return Inventory_JsonHelper.parseFromJsonList(input);
    }
//    /**
//     * 物资编码申请行
//     */
//    public static ArrayList<Itemreqline> parseItemreqlineFromString(String input) throws IOException {
//        return Itemreqline_JsonHelper.parseFromJsonList(input);
//    }
//    /**
//     * 物资编码申请
//     */
//    public static ArrayList<Itemreq> parseItemreqFromString(String input) throws IOException {
//        return Itemreq_JsonHelper.parseFromJsonList(input);
//    }
    /**
     * 解析库存转移
     */
    public static ArrayList<Locations> parseLocationsFromString(String input) throws IOException {
        return Locations_JsonHelper.parseFromJsonList(input);
    }
    /**
     * 解析库存盘点
     */
    public static ArrayList<Invbalances> parseInvbalancesFromString(String input) throws IOException {
        return Invbalances_JsonHelper.parseFromJsonList(input);
    }

    /**
     * 人员解析Person
     */
    public static ArrayList<Person> parsePersonFromString(String input) throws IOException {
        return Person_JsonHelper.parseFromJsonList(input);
    }


}
