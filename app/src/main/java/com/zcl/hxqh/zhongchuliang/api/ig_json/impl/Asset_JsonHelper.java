package com.zcl.hxqh.zhongchuliang.api.ig_json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.zcl.hxqh.zhongchuliang.api.ig_json.JsonHelper;
import com.zcl.hxqh.zhongchuliang.model.Asset;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Asset*
 */
public final class Asset_JsonHelper
        implements JsonHelper<Asset> {
    private static final String TAG = "Asset_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Asset> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Asset> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Asset>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Asset parsed = parseFromJson(jp);
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
    public static Asset parseFromJson(JsonParser jp)
            throws IOException {
        Asset instance = new Asset();

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

    public static boolean processSingleField(Asset instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("ASSETNUM".equals(fieldName)) {
            instance.assetnum = jp.getValueAsString();
            return true;
        } else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
            return true;
        } else if ("N_LOCANAME".equals(fieldName)) {
            instance.n_locaname = jp.getValueAsString();
        } else if ("N_MODELNUM".equals(fieldName)) {
            instance.n_modelnum = jp.getValueAsString();
            return true;
        } else if ("ASSETTYPE".equals(fieldName)) {
            instance.assettype = jp.getValueAsString();
            return true;
        } else if ("PARENT".equals(fieldName)) {
            instance.parent = jp.getValueAsString();
            return true;
        }else if ("STATUS".equals(fieldName)) {
            instance.status = jp.getValueAsString();
            return true;
        }

        return false;
    }

    /**
     * 解析Item*
     */
    public static ArrayList<Asset> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Item*
     */
    public static Asset parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
