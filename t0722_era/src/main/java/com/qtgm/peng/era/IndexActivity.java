package com.qtgm.peng.era;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.qtgm.peng.era.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndexActivity extends AppCompatActivity {

    @BindView(R.id.index_rv)
    RecyclerView indexRv;
    @BindView(R.id.index_toolbar)
    Toolbar toolbar;
    @BindView(R.id.index_naviga)
    NavigationView indexNaviga;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);

        //toolbar.setTitle("只包含toolBar  的布局");
        toolbar.setOnClickListener(v -> startActivity(new Intent(this,ToolActivity.class)));

        myAdapter = new MyAdapter(initData());
        indexRv.setLayoutManager(new LinearLayoutManager(this));
        indexRv.setAdapter(myAdapter);

        TextView tv = new TextView(this);
        tv.setText("只包含toolBar  的布局 ");
        tv.setMaxWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(20f);
        tv.setTextColor(Color.BLUE);
        myAdapter.setHeaderView(tv);

        myAdapter.setOnItemClickListener((adapter, view, position) ->
                startActivity(new Intent(IndexActivity.this, MainActivity.class)));

        initNativi();
    }

    private void initNativi() {
        indexNaviga.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    /*case R.id.nav_item1:
                        break;
                    case R.id.nav_item2:
                        break;
                    case R.id.nav_set:
                        break;
                    case R.id.menu_share:
                        break;
                    case R.id.nav_about:
                        break;*/
                }
                //隐藏NavigationView
                //mDrawerLayout.openDrawer(GravityCompat.START);
                //mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private List<String> initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }
}
