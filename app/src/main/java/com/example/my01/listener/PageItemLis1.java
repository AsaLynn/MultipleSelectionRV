package com.example.my01.listener;

import android.app.Activity;

import com.example.demonstrate.adapter.testname.p1.w3.BaseT6P1W3ILis;
import com.example.my01.MainActivity;
import com.example.my01.R;
import com.example.my01.activity.Test1Activity;

/**
 * Created by think on 2018/3/21.
 * 核心原理周考3的测试题第一页面条目点击监听.
 */

public class PageItemLis1 extends BaseT6P1W3ILis {

    public PageItemLis1(Activity activity) {
        super(activity);
    }

    @Override
    public Class<?> getStartActivity(int which) {
        if (which == 0) {
            return Test1Activity.class;
        } else if (which == 1) {
            return MainActivity.class;
        }

        return null;
    }

    @Override
    public int getDialogListId() {
        return R.array.test4_week3_dialog1_items;
    }
}
