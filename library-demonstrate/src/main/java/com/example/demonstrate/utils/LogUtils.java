package com.example.demonstrate.utils;

import android.util.Log;

/**
 * Created by think on 2018/3/19.
 * 打印日志工具类
 * 使用方法:level = VERBOSE可以打印所有的日志;level = NOTHING所有的日志都打印不出来，上线时设置level = NOTHING
 */

public class LogUtils {
    static String STR_TAG = "--->***";
    static String TAG = "LogUtils";
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static final int level = VERBOSE;

    private LogUtils() {
        throw new UnsupportedOperationException("本日志工具类不能被实例化,直接调用静态方法就好");
    }

    /**
     * 打印Verbose级别日志
     *
     * @param msg
     */
    public static void v(String tag, String msg) {
        if (level <= VERBOSE) {
            Log.i(TAG, msg + STR_TAG);
        }
    }

    /**
     * 打印Info级别日志
     *
     * @param msg
     */
    public static void i(String msg) {
//        if (level <= INFO) {
        Log.i(TAG, STR_TAG + msg);
//        }
    }
}
