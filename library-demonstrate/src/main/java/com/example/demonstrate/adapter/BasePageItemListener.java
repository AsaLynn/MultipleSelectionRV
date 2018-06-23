package com.example.demonstrate.adapter;

import android.app.Activity;

import com.example.demonstrate.DialogPage;

/**
 * Created by think on 2018/3/13.
 */

public abstract class BasePageItemListener implements DialogPage.OnDialogItemListener {

    private final Activity mActivity;
    public BasePageItemListener(Activity activity) {
        mActivity = activity;
    }

    @Override
    public Activity getActivity() {
        return mActivity;
    }

    @Override
    public String getTitle() {
        return mActivity
                .getResources()
                .getString(getTestNameId())
                .concat(
                        mActivity
                                .getResources()
                                .getString(getWeekNumId())
                ).concat(mActivity
                        .getResources()
                        .getString(getPageNumId())
                );
    }

    protected abstract int getPageNumId();

    protected abstract int getWeekNumId();

    protected abstract int getTestNameId();


    //R.string.test4_name
    //R.string.week_num2
    //R.string.page_num2
}
