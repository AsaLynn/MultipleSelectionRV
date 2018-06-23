package com.example.demonstrate.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by think on 2018/3/21.
 * UI操作工具类
 */

public class UIUtils {

    /**
     * 获取手机屏幕宽度.
     *
     * @param activity
     * @return
     */
    public static int getScreenWidth(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthPx = displayMetrics.widthPixels;
        Log.i(TAG, "onCreate: 屏幕的宽度是***" + widthPx);
        int densityDpi = displayMetrics.densityDpi;
        Log.i(TAG, "onCreate: densityDpi***" + densityDpi);
        float density = displayMetrics.density;
        Log.i(TAG, "onCreate: density***" + density);
        //屏幕宽度px
        return widthPx;
    }


    /**
     * dp单位转换成px后的值.
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context, int dpValue) {
        float density = getDensity(context);
        int resultValue = (int) (dpValue * density + 0.5);
        Log.i(TAG, "dp2px: dp转换px***" + resultValue);
        return resultValue;
    }

    /**
     * 获取手机屏幕密度
     *
     * @param context 上下文
     * @return
     */
    private static float getDensity(Context context) {
        return context.getResources()
                .getDisplayMetrics()
                .density;
    }

    private static String TAG = "UIUtils";

}
