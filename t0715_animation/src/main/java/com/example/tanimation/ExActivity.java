package com.example.tanimation;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExActivity extends AppCompatActivity {

    @BindView(R.id.ex_btn_1)
    Button exBtn1;
    @BindView(R.id.ex_btn_2)
    Button exBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex);
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
    }


    @OnClick({R.id.ex_btn_1, R.id.ex_btn_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ex_btn_1:
                ex();
                break;
            case R.id.ex_btn_2:
                break;
        }
    }

    private void ex() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = ViewAnimationUtils.createCircularReveal(exBtn1, 0, 0, 50, 50);
            animator.setDuration(2000);
            animator.start();
        }
    }
}
