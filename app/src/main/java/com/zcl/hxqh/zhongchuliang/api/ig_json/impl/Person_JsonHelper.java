package com.zcl.hxqh.zhongchuliang.api.ig_json.impl;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.instagram.common.json.JsonFactoryHolder;
import com.zcl.hxqh.zhongchuliang.api.ig_json.JsonHelper;
import com.zcl.hxqh.zhongchuliang.model.Person;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Asset*
 */
public final class Person_JsonHelper
        implements JsonHelper<Person> {
    private static final String TAG = "Person_JsonHelper";

    /**
     * 解析List*
     */
    public static ArrayList<Person> parseFromJsonList(JsonParser jp)
            throws IOException {

        ArrayList<Person> results = null;

        // validate that we're on the right token
        if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
            results = new ArrayList<Person>();
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                Person parsed = parseFromJson(jp);
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
    public static Person parseFromJson(JsonParser jp)
            throws IOException {
        Person instance = new Person();

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

    public static boolean processSingleField(Person instance, String fieldName, JsonParser jp)
            throws IOException {
        if ("N_CARDNUM".equals(fieldName)) {
            instance.n_cardnum = jp.getValueAsString();
            return true;
        } else if ("DISPLAYNAME".equals(fieldName)) {
            instance.displayname = jp.getValueAsString();
            return true;
        }

        return false;
    }

    /**
     * 解析Item*
     */
    public static ArrayList<Person> parseFromJsonList(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJsonList(jp);
    }

    /**
     * 解析Item*
     */
    public static Person parseFromJson(String inputString)
            throws IOException {
        JsonParser jp = JsonFactoryHolder.APP_FACTORY.createParser(inputString);
        jp.nextToken();
        return parseFromJson(jp);
    }


}
