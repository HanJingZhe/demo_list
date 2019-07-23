package com.qtgm.peng.era;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qtgm.peng.era.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomActivity extends AppCompatActivity {

    @BindView(R.id.custom_rv)
    RecyclerView customRv;
    @BindView(R.id.custom_app_bar)
    AppBarLayout customAppBar;
    @BindView(R.id.fa_btn)
    FloatingActionButton faBtn;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        ButterKnife.bind(this);


        myAdapter = new MyAdapter(initData());
        customRv.setLayoutManager(new LinearLayoutManager(this));
        customRv.setAdapter(myAdapter);
    }

    private List<String> initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }


    @OnClick(R.id.fa_btn)
    public void onClick() {
        startActivity(new Intent(this,TestActivity4.class));
    }
}
