package com.example.demonstrate.adapter.testname.p1;

import android.app.Activity;

import com.example.demonstrate.R;
import com.example.demonstrate.adapter.testname.BaseT5PILis;

/**
 * Created by think on 2018/3/13.
 */

public abstract class BaseT5P1ILis extends BaseT5PILis {
    public BaseT5P1ILis(Activity activity) {
        super(activity);
    }

    @Override
    protected int getPageNumId() {
        return R.string.page_num1;
    }

}
