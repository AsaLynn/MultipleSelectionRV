package com.example.demonstrate.adapter.testname;

import android.app.Activity;

import com.example.demonstrate.R;
import com.example.demonstrate.adapter.BasePageItemListener;

/**
 * Created by think on 2018/3/13.
 */

public abstract class BaseT3PILis extends BasePageItemListener {


    public BaseT3PILis(Activity activity) {
        super(activity);
    }

    @Override
    protected int getTestNameId() {
        return R.string.test3_name;
    }

}
