package com.example.my01.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.my01.R;
import com.example.my01.SingleAdapter;
import com.example.my01.entity.ResultEntity;
import com.example.my01.entity.SearchEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zxn.divider.ItemDivider;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SingleSelectCancleActivity extends AppCompatActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.rv_skills)
    RecyclerView rvSkills;
    private SingleAdapter adapter;
    private int mPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_selection);
        ButterKnife.bind(this);

        onInitView();


        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        Request request = new Request.Builder()
                .get()
                .url("http://api.wdy330.com/guns-rest-0.0.1-SNAPSHOT/skills/list")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Type type = new TypeToken<ResultEntity<List<SearchEntity>>>() {
                }.getType();
                final ResultEntity<List<SearchEntity>> entity = new Gson().fromJson(result, type);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setNewData(entity.result);
                    }
                });
            }
        });

    }

    private void onInitView() {
        rvSkills.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        rvSkills.setLayoutManager(gridLayoutManager);
        adapter = new SingleAdapter();
        rvSkills.setAdapter(adapter);
        rvSkills.setBackgroundColor(getResources().getColor(R.color.c_ffffff));
        ItemDivider divider = new ItemDivider.Builder(this)
                .bgColor(R.color.c_ffffff)
                .widthDp(5)
                .spanCount(gridLayoutManager.getSpanCount())
                .createGrid();
        rvSkills.addItemDecoration(divider);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        SingleAdapter.ViewHolder holder
                = (SingleAdapter.ViewHolder) rvSkills
                .findViewHolderForLayoutPosition(mPosition != -1 ? mPosition : position);
        if (null != holder && mPosition != -1) {
            holder.itemSelectCb.setBackgroundResource(R.drawable.bg_d_sp_selected_off);
        } else {
            adapter.notifyItemChanged(mPosition);
        }

        if (mPosition == position) {
            if (null != holder) {
                holder.itemSelectCb.setBackgroundResource(R.drawable.bg_d_sp_selected_off);
            }
        } else {
            SingleAdapter.ViewHolder newHolder
                    = (SingleAdapter.ViewHolder) rvSkills
                    .findViewHolderForLayoutPosition(position);
            newHolder.itemSelectCb.setBackgroundResource(R.drawable.bg_d_sp_selected);
        }
        mPosition = position;
    }

}
