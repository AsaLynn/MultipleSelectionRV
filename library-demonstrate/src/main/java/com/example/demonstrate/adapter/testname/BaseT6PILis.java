package com.example.demonstrate.adapter.testname;

import android.app.Activity;

import com.example.demonstrate.R;
import com.example.demonstrate.adapter.BasePageItemListener;

/**
 * Created by think on 2018/3/13.
 *
 */

public abstract class BaseT6PILis extends BasePageItemListener {


    public BaseT6PILis(Activity activity) {
        super(activity);
    }

    @Override
    protected int getTestNameId() {
        return R.string.test6_name;
    }

}
