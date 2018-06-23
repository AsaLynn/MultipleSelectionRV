package com.example.demonstrate.adapter;

import android.app.Activity;

/**
 * Created by think on 2018/3/15.
 */

public abstract class BaseNorPILis extends BasePageItemListener{

    public BaseNorPILis(Activity activity) {
        super(activity);
    }

    @Override
    public Class<?> getStartActivity(int which) {
        return null;
    }

    @Override
    public String getTitle() {
        return getNormalTitle();
    }

    public abstract String getNormalTitle();

    @Override
    protected int getPageNumId() {
        return 0;
    }

    @Override
    protected int getWeekNumId() {
        return 0;
    }

    @Override
    protected int getTestNameId() {
        return 0;
    }
}
