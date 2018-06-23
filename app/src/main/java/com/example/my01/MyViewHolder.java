package com.example.my01;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by think on 2018/1/12.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_select_cb)
    public CheckBox itemSelectCb;
    @BindView(R.id.bg_ll)
    public LinearLayout bg_ll;

    public MyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
