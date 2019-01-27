package com.example.my01;

import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.my01.entity.SearchEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zxn on 2019/1/27.
 */
public class MultipleAdapter extends BaseQuickAdapter<SearchEntity, MultipleAdapter.ViewHolder> {


    public MultipleAdapter() {
        super(R.layout.item_multiple_skills);
    }

    @Override
    protected void convert(ViewHolder helper, SearchEntity item) {
        helper.itemSelectCb.setText(item.name);
    }

    static class ViewHolder extends BaseViewHolder{
        @BindView(R.id.item_select_cb)
        CheckBox itemSelectCb;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
