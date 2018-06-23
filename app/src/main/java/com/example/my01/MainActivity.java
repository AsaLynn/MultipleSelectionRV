package com.example.my01;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected TabLayout tablayoutTl;
    protected ViewPager viewpagerVp;
    protected FloatingActionButton fab;
    private String TAG = this.getClass().getSimpleName();

    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("分类选择");
        toolbar.setTitleTextColor(getResources().getColor(R.color.c_606060));
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initView();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            BaseFragment fragment = (BaseFragment) fragments.get(tablayoutTl.getSelectedTabPosition());
            Toast.makeText(this, "勾选了:" + fragment.getCheckedInfo(), Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        tablayoutTl = (TabLayout) findViewById(R.id.tablayout_tl);
        fragments = new ArrayList<>();
        fragments.add(new Fragment0());
        fragments.add(new Fragment1());
        viewpagerVp = (ViewPager) findViewById(R.id.viewpager_vp);
        tablayoutTl.setupWithViewPager(viewpagerVp);


        viewpagerVp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    String[] tabs = {"科室", "兴趣"};

    class MyPagerAdapter extends FragmentPagerAdapter {


        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
