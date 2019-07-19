package com.example.tanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author peng_wang
 * @des 按钮的波纹动画
 */
public class ButtonActivity extends AppCompatActivity {

    @BindView(R.id.btn_btn_1)
    Button btnBtn1;
    @BindView(R.id.btn_btn_2)
    TextView btnBtn2;
    @BindView(R.id.btn_btn_3)
    Button btnBtn3;
    @BindView(R.id.btn_btn_4)
    Button btnBtn4;
    @BindView(R.id.btn_btn_5)
    Button btnBtn5;
    @BindView(R.id.btn_btn_6)
    Button btnBtn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_btn_1, R.id.btn_btn_2, R.id.btn_btn_3, R.id.btn_btn_4, R.id.btn_btn_5, R.id.btn_btn_6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_btn_1:
                break;
            case R.id.btn_btn_2:
                break;
            case R.id.btn_btn_3:
                break;
            case R.id.btn_btn_4:
                break;
            case R.id.btn_btn_5:
                break;
            case R.id.btn_btn_6:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(R.anim.view_in,R.anim.view_out);
    }
}
