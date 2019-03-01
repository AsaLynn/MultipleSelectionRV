package com.example.my01;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.my01.entity.SearchEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxn on 2019/1/27.
 */
public class SingleAdapter extends BaseQuickAdapter<SearchEntity, SingleAdapter.ViewHolder> {


    public SingleAdapter() {
        super(R.layout.item_single_skills);
    }

    @Override
    protected void convert(ViewHolder helper, SearchEntity item) {
        helper.itemSelectCb.setText(item.name);
    }

    public static class ViewHolder extends BaseViewHolder{
        @BindView(R.id.item_select_cb)
        public TextView itemSelectCb;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
