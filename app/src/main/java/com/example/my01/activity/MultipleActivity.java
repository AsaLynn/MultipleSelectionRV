package com.example.my01.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.my01.MultipleAdapter;
import com.example.my01.R;
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

/**
 * Created by zxn on 2019-1-27 10:17:33.
 */
public class MultipleActivity extends AppCompatActivity {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.rv_skills)
    RecyclerView rvSkills;
    private String mParam1;
    private MultipleAdapter adapter;

    public static void jumpTo(Context context, String param1) {
        Intent intent = new Intent(context, MultipleActivity.class);
        intent.putExtra(ARG_PARAM1, param1);
        context.startActivity(intent);
    }

    public static void jumpTo(Context context) {
        Intent intent = new Intent(context, MultipleActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple);
        ButterKnife.bind(this);
        mParam1 = getIntent().getStringExtra(ARG_PARAM1);

        rvSkills.setHasFixedSize(true);
        rvSkills.setLayoutManager(new GridLayoutManager(this, 5));
        adapter = new MultipleAdapter();
        rvSkills.setAdapter(adapter);
        ItemDivider divider = new ItemDivider.Builder(this)
                .bgColor(R.color.colorAccent)
                .widthDp(5)
                .spanCount(5)
                .createGrid();
        rvSkills.addItemDecoration(divider);

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
}
