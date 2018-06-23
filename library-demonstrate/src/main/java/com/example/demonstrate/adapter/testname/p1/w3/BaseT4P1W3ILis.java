package com.example.demonstrate.adapter.testname.p1.w3;

import android.app.Activity;

import com.example.demonstrate.R;
import com.example.demonstrate.adapter.testname.p1.BaseT4P1ILis;

/**
 * Created by think on 2018/3/13.
 */

public abstract class BaseT4P1W3ILis extends BaseT4P1ILis {
    public BaseT4P1W3ILis(Activity activity) {
        super(activity);
    }

    @Override
    protected int getWeekNumId() {
        return R.string.week_num3;
    }

}
