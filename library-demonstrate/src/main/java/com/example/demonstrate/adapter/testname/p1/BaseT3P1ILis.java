package com.example.demonstrate.adapter.testname.p1;

import android.app.Activity;

import com.example.demonstrate.R;
import com.example.demonstrate.adapter.testname.BaseT3PILis;

/**
 * Created by think on 2018/3/13.
 */

public abstract class BaseT3P1ILis extends BaseT3PILis {
    public BaseT3P1ILis(Activity activity) {
        super(activity);
    }

    @Override
    protected int getPageNumId() {
        return R.string.page_num1;
    }

}
