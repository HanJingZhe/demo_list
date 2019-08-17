package com.qtgm.peng.t0805_save.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.qtgm.peng.t0805_save.R;
import com.qtgm.peng.t0805_save.ui.PersonBookActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_tv1)
    TextView mainTv1;
    @BindView(R.id.main_tv2)
    TextView mainTv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.main_tv1, R.id.main_tv2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_tv1:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.main_tv2:
                break;
        }
    }
}
