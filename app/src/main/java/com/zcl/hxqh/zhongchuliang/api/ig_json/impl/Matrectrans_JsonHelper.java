package com.zcl.hxqh.zhongchuliang.api.ig_json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.zcl.hxqh.zhongchuliang.api.ig_json.JsonHelper;
import com.zcl.hxqh.zhongchuliang.model.Matrectrans;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Matrectrans解析*
 */
public final class Matrectrans_JsonHelper
        implements JsonHelper<Matrectrans> {
    private static final String TAG = "Po_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Matrectrans> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Matrectrans> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Matrectrans>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Matrectrans parsed = parseFromJson(jp);
                Log.i(TAG, "parsed=" + parsed);
                if (parsed != null) {
                    results.add(parsed);
                }
            }
        }


        return results;
    }


    /**
     * 解析Po
     */
    public static Matrectrans parseFromJson(JsonParser jp)
            throws IOException {
        Matrectrans instance = new Matrectrans();

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

    public static boolean processSingleField(Matrectrans instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("PONUM".equals(fieldName)) {
            instance.ponum = jp.getValueAsString();
            return true;
        } else if ("POLINENUM".equals(fieldName)) {
            instance.polinenum = jp.getValueAsString();
            return true;
        }else if ("DESCRIPTION".equals(fieldName)) {
            instance.description = jp.getValueAsString();
            return true;
        } else if ("ITEMNUM".equals(fieldName)) {
            instance.itemnum = jp.getValueAsString();
            return true;
        }else if ("TYPE".equals(fieldName)) {
            instance.type = jp.getValueAsString();
            return true;
        }else if ("RECEIPTQUANTITY".equals(fieldName)) {
            instance.receiptquantity = jp.getValueAsString();
        }else if ("QUANTITY".equals(fieldName)) {
            instance.quantity = jp.getValueAsString();
        } else if ("RECEIVEDUNIT".equals(fieldName)) {
            instance.receivedunit = jp.getValueAsString();
        }else if ("ISSUETYPE".equals(fieldName)) {
            instance.issuetype = jp.getValueAsString();
        }else if ("STATUS".equals(fieldName)) {
            instance.status = jp.getValueAsString();
        }else if ("ACTUALDATE".equals(fieldName)) {
            instance.actualdate = jp.getValueAsString();
        }else if ("STATUSDATE".equals(fieldName)) {
            instance.statusdate = jp.getValueAsString();
        }else if ("CURBALTOTAL".equals(fieldName)) {
            instance.curbaltotal = jp.getValueAsString();
            return true;
        }else if ("CURBALTOTAL".equals(fieldName)) {
            instance.curbaltotal = jp.getValueAsString();
            return true;
        }else if ("LINECOST".equals(fieldName)) {
            instance.linecost = jp.getValueAsString();
            return true;
        }else if ("FROMSTORELOC".equals(fieldName)) {
            instance.fromstoreloc = jp.getValueAsString();
            return true;
        }else if ("FROMBIN".equals(fieldName)) {
            instance.frombin = jp.getValueAsString();
            return true;
        }else if ("FROMBIN".equals(fieldName)) {
            instance.frombin = jp.getValueAsString();
            return true;
        }else if ("TOSTORELOC".equals(fieldName)) {
            instance.tostoreloc = jp.getValueAsString();
            return true;
        }else if ("TOBIN".equals(fieldName)) {
            instance.tobin = jp.getValueAsString();
            return true;
        }else if ("FROMLOT".equals(fieldName)) {
            instance.fromlot = jp.getValueAsString();
            return true;
        }else if ("TOLOT".equals(fieldName)) {
            instance.tolot = jp.getValueAsString();
            return true;
        }

        return false;
    }

    /**
     * 解析Po*
     */
    public static ArrayList<Matrectrans> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Item*
     */
    public static Matrectrans parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
