package com.bootcamp.databinding.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedUtil {
    public static SharedPreferences preferences(Context context) {
        return context.getSharedPreferences(ConstantUtil.SHARED_KEY, Context.MODE_PRIVATE);
    }
}
