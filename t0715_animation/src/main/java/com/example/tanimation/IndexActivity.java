package com.example.tanimation;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author peng_wang
 * @des 首页
 */
public class IndexActivity extends AppCompatActivity {

    @BindView(R.id.index_tv_1)
    TextView indexTv1;
    @BindView(R.id.index_tv_2)
    TextView indexTv2;
    @BindView(R.id.index_tv_3)
    TextView indexTv3;
    @BindView(R.id.index_tv_4)
    TextView indexTv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
        setupWindowAnimations();
    }

    private void setupWindowAnimations() {
        /*Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setExitTransition(slide);*/

        /**
         * Fade fade = new Fade();
         *     fade.setDuration(1000);
         *     getWindow().setEnterTransition(fade);
         */

        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);
        Log.e("王鹏","执行了桩长动画");
    }

    @OnClick({R.id.index_tv_1, R.id.index_tv_2, R.id.index_tv_3,R.id.index_tv_4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.index_tv_1:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.index_tv_2:
                startActivity(new Intent(this, AttrActivity.class));
                break;
            case R.id.index_tv_3:
                startActivity(new Intent(this, ButtonActivity.class));
                overridePendingTransition(R.anim.view_in,R.anim.view_out);
                break;
            case R.id.index_tv_4:
                startActivity(new Intent(this, ExActivity.class));
                break;
        }
    }
}
