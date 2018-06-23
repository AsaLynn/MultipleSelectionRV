package com.example.my01;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by think on 2018/1/12.
 */

public class MyHeaderViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.title_tv)
    public TextView titleTv;
    @BindView(R.id.title_checke_all_cb)
    public CheckBox titleCheckeAllCb;

    public MyHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
