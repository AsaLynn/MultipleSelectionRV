package com.example.my01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.truizlop.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.truizlop.sectionedrecyclerview.SectionedSpanSizeLookup;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by think on 2018/1/12.
 */

public abstract class BaseFragment extends Fragment {

    protected DoctorInfo doctorInfo;
    private MyRecyclerAdapter adapter;
    private float density;
    private int widthPixels;

    public BaseFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        density = getContext().getResources()
                .getDisplayMetrics()
                .density;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //屏幕宽度px
        widthPixels = displayMetrics.widthPixels;
        Log.i(TAG, "onCreate: 屏幕的宽度是***" + widthPixels);
        int densityDpi = displayMetrics.densityDpi;
        Log.i(TAG, "onCreate: densityDpi***" + densityDpi);
        float density = displayMetrics.density;
        Log.i(TAG, "onCreate: density***" + density);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_recycler, null);

        initView(view);
        request();
        return view;
    }

    private void initView(View rootView) {
        recyclerRv = (RecyclerView) rootView.findViewById(R.id.recycler_rv);
        GridLayoutManager manager = new GridLayoutManager(this.getActivity(), 2);
        recyclerRv.setLayoutManager(manager);
        recyclerRv.setHasFixedSize(true);
        adapter = new MyRecyclerAdapter();
        recyclerRv.setAdapter(adapter);
        //设置列的跨度
        manager.setSpanSizeLookup(new SectionedSpanSizeLookup(adapter, manager));
    }

    protected View rootView;
    protected String TAG = this.getClass().getSimpleName();
    protected RecyclerView recyclerRv;

    private void request() {
        gson = new Gson();
        new OkHttpClient.Builder()
                .build().newCall(new Request.Builder().url(getUrl()).build())
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
                        BaseFragment.this.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
    }

    protected abstract String getUrl();

    protected Gson gson;

    class MyRecyclerAdapter extends SectionedRecyclerViewAdapter<MyHeaderViewHolder, MyViewHolder, MyFootHeader> {

        int itemSpaceWidth;
        int cbShouldWidth;

        public MyRecyclerAdapter() {
            itemSpaceWidth = dp2px(15);
            cbShouldWidth = (widthPixels - 3 * itemSpaceWidth) / 2;
        }

        public MyRecyclerAdapter(int dpValue) {
            itemSpaceWidth = dp2px(dpValue);
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

            /*holder.itemSelectCb.post(new Runnable() {
                @Override
                public void run() {
                    int width = holder.itemSelectCb.getWidth();
                    Log.i(TAG, "width: ***" + width);
                    int leftPadd = (cbShouldWidth - width) / 2;
                    int left = leftPadd;
                    int top = 10;
                    int right = leftPadd;
                    int bottom = 10;
                    holder.itemSelectCb.setPadding(left,top,right,bottom);
                }
            });

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.itemSelectCb.getLayoutParams();
            if (position % 2 == 0) {
                layoutParams.leftMargin = itemSpaceWidth;
            } else if (position % 2 == 1) {
                layoutParams.leftMargin = itemSpaceWidth / 2;
            }

            holder.itemSelectCb.setLayoutParams(layoutParams);*/

            /*  int cbShouldWidth = (widthPixels - 3 * itemSpaceWidth)/2;
            holder.itemSelectCb.setWidth(cbShouldWidth);*/


            /*//测量Checkbox的宽度
            int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            holder.itemSelectCb.measure(width, height);
            int oldecbWidth = holder.itemSelectCb.getMeasuredWidth();
            Log.i(TAG, "oldecbWidth***" + oldecbWidth);

            int cbShouldWidth = (widthPixels - 3 * itemSpaceWidth)/2;
            Log.i(TAG, "cbShouldWidth***" + cbShouldWidth);

            int padd = (cbShouldWidth - oldecbWidth) / 2;
            int left = padd;
            int top = 10;
            int right = padd;
            int bottom = 10;
            Log.i(TAG, "padd***" + padd);
            holder.itemSelectCb.setPadding(left, top, right, bottom);*/
        }

        @Override
        protected void onBindSectionFooterViewHolder(MyFootHeader holder, int section) {
        }
    }

    public int dp2px(int dpValue) {
        int resultValue = (int) (dpValue * density + 0.5);
        Log.i(TAG, "dp2px: dp转换px***" + resultValue);
        return resultValue;
    }

    public String getCheckedInfo() {
        if ( doctorInfo != null) {
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
    private StringBuilder checkedBuilder = new StringBuilder();
}
