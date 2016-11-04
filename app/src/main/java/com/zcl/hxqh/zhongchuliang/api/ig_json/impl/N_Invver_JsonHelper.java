package com.zcl.hxqh.zhongchuliang.api.ig_json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.zcl.hxqh.zhongchuliang.api.ig_json.JsonHelper;
import com.zcl.hxqh.zhongchuliang.model.N_Invver;

import java.io.IOException;
import java.util.ArrayList;

/**
 * N_Invver*
 */
public final class N_Invver_JsonHelper
        implements JsonHelper<N_Invver> {
    private static final String TAG = "N_Invver_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<N_Invver> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<N_Invver> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<N_Invver>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                N_Invver parsed = parseFromJson(jp);
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
    public static N_Invver parseFromJson(JsonParser jp)
            throws IOException {
        N_Invver instance = new N_Invver();

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

    public static boolean processSingleField(N_Invver instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("INVVERNUM".equals(fieldName)) {
            instance.invvernum = jp.getValueAsString();
            return true;
        } else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
            return true;
        } else if ("LOCATION".equals(fieldName)) {
            instance.location = jp.getValueAsString();
        } else if ("LOCDESC".equals(fieldName)) {
            instance.locdesc = jp.getValueAsString();
            return true;
        } else if ("STATUS".equals(fieldName)) {
            instance.status = jp.getValueAsString();
            return true;
        } else if ("STATUSDATE".equals(fieldName)) {
            instance.statusdate = jp.getValueAsString();
            return true;
        } else if ("REPORTNAME".equals(fieldName)) {
            instance.reportname = jp.getValueAsString();
            return true;
        } else if ("REPORTDATE".equals(fieldName)) {
            instance.reportdate = jp.getValueAsString();
            return true;
        }

        return false;
    }

    /**
     * 解析Item*
     */
    public static ArrayList<N_Invver> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Item*
     */
    public static N_Invver parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
