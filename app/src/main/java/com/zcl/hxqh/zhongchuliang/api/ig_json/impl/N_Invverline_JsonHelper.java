package com.zcl.hxqh.zhongchuliang.api.ig_json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.zcl.hxqh.zhongchuliang.api.ig_json.JsonHelper;
import com.zcl.hxqh.zhongchuliang.model.N_Invverline;

import java.io.IOException;
import java.util.ArrayList;

/**
 * N_Invverline*
 */
public final class N_Invverline_JsonHelper
        implements JsonHelper<N_Invverline> {
    private static final String TAG = "N_Invverline_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<N_Invverline> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<N_Invverline> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<N_Invverline>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                N_Invverline parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Inventory
     */
    public static N_Invverline parseFromJson(JsonParser jp)
            throws IOException {
        N_Invverline instance = new N_Invverline();

        // validate that we're on the right token
        if (jp.getCurrentToken() != JsonToken.START_OBJECT) {
            jp.skipChildren();
            return null;
        }

        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jp.getCurrentName();
            jp.nextToken();
            processSingleField(instance, fieldName, jp);
            jp.skipChildren();
        }

        return instance;
    }

    public static boolean processSingleField(N_Invverline instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("SN".equals(fieldName)) {
            instance.sn = jp.getValueAsString();
            return true;
        } else if ("INVVERNUM".equals(fieldName)) {
            instance.invvernum = jp.getValueAsString();
            return true;
        } else if ("ITEMNUM".equals(fieldName)) {
            instance.itemnum = jp.getValueAsString();
            return true;
        } else if ("ITEMDESC".equals(fieldName)) {
            instance.itemdesc = jp.getValueAsString();
        } else if ("PHYSCNTDATE".equals(fieldName)) {
            instance.physcntdate = jp.getValueAsString();
            return true;
        } else if ("BINNUM".equals(fieldName)) {
            instance.binnum = jp.getValueAsString();
            return true;
        } else if ("LOTNUM".equals(fieldName)) {
            instance.lotnum = jp.getValueAsString();
            return true;
        } else if ("PHYSCNT".equals(fieldName)) {
            instance.physcnt = jp.getValueAsString();
            return true;
        } else if ("ITEMIN19".equals(fieldName)) {
            instance.itemin19 = jp.getValueAsString();
            return true;
        }else if ("AVBLBALANCE".equals(fieldName)) {
            instance.avblbalance = jp.getValueAsString();
            return true;
        }

        return false;
    }

    /**
     * 解析Item*
     */
    public static ArrayList<N_Invverline> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Item*
     */
    public static N_Invverline parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
