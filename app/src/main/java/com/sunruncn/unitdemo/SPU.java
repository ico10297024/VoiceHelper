package com.sunruncn.unitdemo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by admin on 2016/4/1.
 */
public class SPU {
    public static final String FILENAME = "user";//文件名
    public static final String TAG_ACCESS_TOKEN = "assessToekn";//token
    public static final String TAG_EXPIRES = "expaires";//expaires

    public static SharedPreferences getSp(Context context) {
        return context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
    }

    public static String getAccessToken(Context context) {
        return getSp(context).getString(TAG_ACCESS_TOKEN, "");
    }

    public static void setAccessToken(Context context, String accessToken) {
        getSp(context).edit().putString(TAG_ACCESS_TOKEN, accessToken).commit();
    }

    public static long getExpaires(Context context) {
        return getSp(context).getLong(TAG_EXPIRES, -1l);
    }

    public static void setExpaires(Context context, long expaires) {
        getSp(context).edit().putLong(TAG_EXPIRES, expaires).commit();
    }

}
