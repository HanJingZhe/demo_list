package com.qtgm.peng.t0826_tab;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_tab_layout)
    TabLayout mainTabLayout;
    @BindView(R.id.main_view_pager)
    ViewPager mainViewPager;
    private List<String> listTitle = Arrays.asList("鸣人", "卡卡西");
    private List<Fragment> listFragment = Arrays.asList(new MyFragment("鸣人"), new MyFragment("卡卡西"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        MyAdapter myTabAdapter = new MyAdapter(getSupportFragmentManager(), listFragment, listTitle);
        mainTabLayout.setupWithViewPager(mainViewPager);
        mainViewPager.setAdapter(myTabAdapter);

    }
}
