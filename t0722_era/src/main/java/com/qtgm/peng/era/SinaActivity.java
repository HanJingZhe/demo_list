package com.qtgm.peng.era;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SinaActivity extends AppCompatActivity {

    @BindView(R.id.main_rv)
    RecyclerView mainRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sina);
        ButterKnife.bind(this);

        initRv();
    }

    private void initRv() {
        DataModel.MyStringAdapter mAdapter = new DataModel.MyStringAdapter(DataModel.initStringData());
        mainRv.setLayoutManager(new LinearLayoutManager(this));
        mainRv.setAdapter(mAdapter);
    }
}
