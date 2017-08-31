package com.example.avil.currencyconverter.model;

import android.app.Activity;
import android.content.SharedPreferences;

import java.util.HashMap;

import dictionary.CurrencyDict;


public class Converter implements IConverter {

    // rub eur usd
    HashMap<String, Float> course = new HashMap<>();

    public Converter() {
        for (int i = 0; i < CurrencyDict.ALL.length; i++) {
            course.put(CurrencyDict.ALL[i], 1f);
        }
    }


    @Override
    public void put(String key, float v) {
        course.put(key, v);
    }

    @Override
    public void saveRow(Activity activity, String k, float v) {
        SharedPreferences.Editor editor = activity.getSharedPreferences("com.avil.cache", activity.MODE_PRIVATE).edit();
        editor.putFloat(k, v);
        editor.commit();
    }

    @Override
    public void loadLocalData(Activity activity) {
        SharedPreferences sp = activity.getSharedPreferences("com.avil.cache", activity.MODE_PRIVATE);

        for (int i = 0; i < CurrencyDict.ALL.length; i++) {
            put(CurrencyDict.ALL[i], sp.getFloat(CurrencyDict.ALL[i], 1f));
        }
    }


    public float convert(String from, String to, float v) {
        float fromVal = course.get(from);
        float toVal = course.get(to);

        return (fromVal / toVal) * v;
    }


}
