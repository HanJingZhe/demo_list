package com.qtgm.peng.era;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.qtgm.peng.era.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * appBar + CollapsingToolbarLayout
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_app_bar)
    AppBarLayout mainAppBar;
    @BindView(R.id.main_rv)
    RecyclerView mainRv;
    @BindView(R.id.main_float_btn)
    FloatingActionButton mainFloatBtn;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.main_cl)
    CoordinatorLayout mainCl;
    @BindView(R.id.main_toolbar)
    Toolbar mainToolBar;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        status();

        initRv();
        Log.e("王鹏", "状态栏高度:" + getStatusBarHeight());
        Log.e("王鹏", "导航栏高度:" + getNavigationBarHeight());

        mainToolBar.setTitle("我是TITLE");

    }

    private void status() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(MainActivity.this, 0);
    }

    private void initRv() {
        myAdapter = new MyAdapter(initData());
        mainRv.setLayoutManager(new LinearLayoutManager(this));
        mainRv.setAdapter(myAdapter);
    }

    private List<String> initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    @OnClick({R.id.main_app_bar, R.id.main_rv, R.id.main_float_btn, R.id.fab, R.id.main_cl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_app_bar:
                break;
            case R.id.main_rv:
                break;
            case R.id.main_float_btn:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setActionTextColor(getResources().getColor(R.color.color_ffff00))
                        .show();
                break;
            case R.id.fab:
                startActivity(new Intent(this,CustomActivity.class));
                break;
            case R.id.main_cl:
                break;
        }
    }


    //返回值就是状态栏的高度,得到的值单位px
    public float getStatusBarHeight() {
        float result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimension(resourceId);
        }
        return result;
    }

    //返回值就是导航栏的高度,得到的值单位px
    public float getNavigationBarHeight() {
        float result = 0;
        int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimension(resourceId);
        }
        return result;
    }

}
