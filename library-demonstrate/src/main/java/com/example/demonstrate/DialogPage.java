package com.example.demonstrate;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

/**
 * Created by think on 2018/3/8.
 */

public class DialogPage implements DialogInterface.OnClickListener {
    private Activity mActivity;
    private String[] items;
    protected static DialogPage mDialogPage;
    private OnDialogItemListener mOnPageItemListener;
    private OnDialogItemNorListener mOnDialogItemNorListener;

    protected DialogPage() {
    }

    public static DialogPage getInstance() {
        if (null == mDialogPage) {
            synchronized (DialogPage.class) {
                if (null == mDialogPage) {
                    mDialogPage = new DialogPage();
                }
            }
        }
        return mDialogPage;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (null == mOnPageItemListener) {
            DemonstrateUtil.showLogResult("未设置对话框列表监听!!!");
            return;
        }
        if (mOnPageItemListener.getStartActivity(which) == null) {
            DemonstrateUtil.showToastResult(mOnPageItemListener.getActivity(), "请设置要跳转的Activity!!!");
            return;
        }
        mOnPageItemListener
                .getActivity()
                .startActivity(new Intent(mOnPageItemListener.getActivity(), mOnPageItemListener.getStartActivity(which)));
    }

    public interface OnDialogItemListener {
        Activity getActivity();

        String getTitle();

        Class<?> getStartActivity(int which);

        int getDialogListId();
    }

    public void setOnDialogItemListener(OnDialogItemListener listener) {
        mOnPageItemListener = listener;
        if (null == mOnPageItemListener) {
            return;
        }
        if (mOnPageItemListener.getActivity() == null) {
            return;
        }
        if (null == items) {
            items = mOnPageItemListener.getActivity().getResources().getStringArray(mOnPageItemListener.getDialogListId());/*R.array.items*/
        }

        DialogUtil.showListDialog(mOnPageItemListener.getActivity(), mOnPageItemListener.getTitle(), items, mDialogPage);
    }

    public void setOnDialogItemNorListener(Activity activity, OnDialogItemNorListener listener) {
        mOnDialogItemNorListener = listener;
        if (null == mOnDialogItemNorListener) {
            return;
        }
        DialogUtil.showListDialog(activity, mOnDialogItemNorListener.titile(), mOnDialogItemNorListener.items(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mOnDialogItemNorListener.listDialog(which);
            }
        });
    }

    public interface OnDialogItemNorListener {

        void listDialog(int which);

        String titile();

        String[] items();
    }
}
