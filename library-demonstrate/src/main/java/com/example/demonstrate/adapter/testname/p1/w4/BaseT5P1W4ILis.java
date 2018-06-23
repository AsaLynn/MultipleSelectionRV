package com.example.demonstrate.adapter.testname.p1.w4;

import android.app.Activity;

import com.example.demonstrate.R;
import com.example.demonstrate.adapter.testname.p1.BaseT5P1ILis;

/**
 * Created by think on 2018/3/13.
 */

public abstract class BaseT5P1W4ILis extends BaseT5P1ILis {
    public BaseT5P1W4ILis(Activity activity) {
        super(activity);
    }

    @Override
    protected int getWeekNumId() {
        return R.string.week_num3;
    }

}
