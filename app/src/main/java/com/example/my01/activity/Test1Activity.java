package com.example.my01.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.example.demonstrate.DemonstrateUtil;
import com.example.demonstrate.utils.UIUtils;
import com.example.my01.DoctorInfo;
import com.example.my01.MyFootHeader;
import com.example.my01.MyHeaderViewHolder;
import com.example.my01.MyViewHolder;
import com.example.my01.R;
import com.example.my01.UC;
import com.google.gson.Gson;
import com.truizlop.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.truizlop.sectionedrecyclerview.SectionedSpanSizeLookup;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Test1Activity extends AppCompatActivity {

    private StringBuilder checkedBuilder = new StringBuilder();

    public String getCheckedInfo() {
        if (doctorInfo != null) {
            checkedBuilder.setLength(0);
            for (int i = 0; i < doctorInfo.getData().size(); i++) {
                for (int j = 0; j < doctorInfo.getData().get(i).getChilds().size(); j++) {
                    if (doctorInfo.getData().get(i).getChilds().get(j).getIsFocus() == 1) {
                        checkedBuilder.append(doctorInfo.getData().get(i).getChilds().get(j).getDepName());
                        checkedBuilder.append(",");
                    }
                }
            }
        }
        return checkedBuilder.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
//            Toast.makeText(this, "勾选了:" + getCheckedInfo(), Toast.LENGTH_SHORT).show();
            DemonstrateUtil.showToastResult(this, getCheckedInfo());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private int widthPixels;
    private RecyclerView recyclerRv;
    private MyRecyclerAdapter adapter;
    protected DoctorInfo doctorInfo;
    private Gson gson;
    private String TAG = "Test1Activity";

    private void request() {
        gson = new Gson();
        new OkHttpClient
                .Builder()
                .build()
                .newCall(new Request.Builder().url(UC.URL_TAB0).build())
                .enqueue(new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
                        Log.i(TAG, result);
                        doctorInfo = gson.fromJson(result, DoctorInfo.class);
                        Log.i(TAG, "doctorInfo: ***" + doctorInfo.toString());
                        Test1Activity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler);
        getSupportActionBar()
                .setTitle("订阅");

        widthPixels = UIUtils.getScreenWidth(this);
        initView();
        request();
    }

    private void initView() {
        recyclerRv = (RecyclerView) findViewById(R.id.recycler_rv);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerRv.setLayoutManager(manager);
        recyclerRv.setHasFixedSize(true);
        adapter = new MyRecyclerAdapter();
        recyclerRv.setAdapter(adapter);
        //设置列的跨度
        manager.setSpanSizeLookup(new SectionedSpanSizeLookup(adapter, manager));
    }

    class MyRecyclerAdapter extends SectionedRecyclerViewAdapter<MyHeaderViewHolder, MyViewHolder, MyFootHeader> {

        int itemSpaceWidth;
        int cbShouldWidth;

        public MyRecyclerAdapter() {
            itemSpaceWidth = UIUtils.dp2px(Test1Activity.this, 15);

            cbShouldWidth = (widthPixels - 3 * itemSpaceWidth) / 2;
        }

        public MyRecyclerAdapter(int dpValue) {
            itemSpaceWidth = UIUtils.dp2px(Test1Activity.this, dpValue);
        }


        @Override
        protected int getSectionCount() {
            return doctorInfo == null ? 0 : doctorInfo.getData().size();
        }

        @Override
        protected int getItemCountForSection(int section) {
            return doctorInfo == null ? 0 : doctorInfo.getData().get(section).getChilds().size();
        }

        @Override
        protected boolean hasFooterInSection(int section) {
            return false;
        }

        @Override
        protected MyHeaderViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_header, null);
            return new MyHeaderViewHolder(view);
        }

        @Override
        protected MyFootHeader onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        protected MyViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_normal, null);
            return new MyViewHolder(view);
        }

        @Override
        protected void onBindSectionHeaderViewHolder(final MyHeaderViewHolder holder, final int section) {
            holder.titleTv.setText(doctorInfo.getData().get(section).getDataName());
            holder.titleCheckeAllCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        holder.titleCheckeAllCb.setText(R.string.cb_select_all_cancel);
                        for (DoctorInfo.DataBean.ChildsBean bean : doctorInfo.getData().get(section).getChilds()) {
                            bean.setIsFocus(1);
                        }
                    } else {
                        holder.titleCheckeAllCb.setText(R.string.cb_select_all_checed);
                        for (DoctorInfo.DataBean.ChildsBean bean : doctorInfo.getData().get(section).getChilds()) {
                            bean.setIsFocus(0);
                        }
                    }
                    notifyDataSetChanged();
                }
            });
        }


        @Override
        protected void onBindItemViewHolder(final MyViewHolder holder, final int section, final int position) {
            holder.itemSelectCb.setText(doctorInfo.getData().get(section).getChilds().get(position).getDepName());
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.bg_ll.getLayoutParams();
            layoutParams.width = cbShouldWidth;
            if (position % 2 == 0) {
                layoutParams.leftMargin = itemSpaceWidth;
            } else if (position % 2 == 1) {
                layoutParams.leftMargin = itemSpaceWidth / 2;
            }
            holder.bg_ll.setLayoutParams(layoutParams);
            holder.itemSelectCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        holder.bg_ll.setBackgroundDrawable(holder.bg_ll.getResources().getDrawable(R.drawable.cb_d_bg_sp_subscribe_checked));
                        doctorInfo.getData().get(section).getChilds().get(position).setIsFocus(1);
                    } else {
                        holder.bg_ll.setBackgroundDrawable(holder.bg_ll.getResources().getDrawable(R.drawable.cb_d_bg_sp_subscribe_normal));
                        doctorInfo.getData().get(section).getChilds().get(position).setIsFocus(0);
                    }

                }
            });

            if (doctorInfo.getData().get(section).getChilds().get(position).getIsFocus() == 0) {
                holder.itemSelectCb.setChecked(false);
            } else {
                holder.itemSelectCb.setChecked(true);
            }
        }

        @Override
        protected void onBindSectionFooterViewHolder(MyFootHeader holder, int section) {
        }
    }
}
