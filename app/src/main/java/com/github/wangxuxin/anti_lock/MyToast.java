package com.github.wangxuxin.anti_lock;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by a1274 on 2017/7/12.
 */
public class MyToast {
    public static void makeText(Context context, CharSequence text, int duration){
        if(isDebug(context)){
            Toast.makeText(context,"Anti-lock:" + text,duration);
        }
    }

    //检测是否是Debug模式
    private static boolean isDebug(Context context){
        SharedPreferences isDebug = context.getSharedPreferences("data", 0);
        return isDebug.getString("isDebug", "null").equals("true");
    }
}
