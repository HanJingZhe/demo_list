package peng.qtgm.widget.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import peng.qtgm.widget.R;
import peng.qtgm.widget.adapter.TabAdapter;

public class TablayoutActivity extends AppCompatActivity {

    @BindView(R.id.tab_tablayout)
    TabLayout tabTablayout;
    @BindView(R.id.tab_view_pager)
    ViewPager tabViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        ButterKnife.bind(this);

        tabTablayout.setupWithViewPager(tabViewPager);

        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        tabViewPager.setAdapter(tabAdapter);

        tabTablayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
