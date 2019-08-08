package com.example.tanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author peng_wang
 * @des 此项目用于操作动画效果 view动画
 * 补间动画
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_0)
    TextView tv0;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.tv_5)
    TextView tv5;
    @BindView(R.id.tv_6)
    TextView tv6;
    @BindView(R.id.main_ll)
    LinearLayout mainLl;

    private Animation alphaAnim;
    private Animation setAnim;
    private Animation translateAnim;
    private Animation scaleAnim;
    private Animation rotateAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //set exam
        setExam();
        //渐变
        alpha();
        //translate 位移
        translateT();
        //scale动画
        scaleT();
        //rotate动画
        rotateT();

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.layout_anim);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(0.2f);
        controller.setOrder(LayoutAnimationController.ORDER_REVERSE);
        mainLl.setLayoutAnimation(controller);

        /**
         * LayoutAnimation 适用于所有的 ViewGroup ，
         * 自然也包含 ListView、RecyclerView 等控件。
         * 上面说过 LayoutAnimation 提供的是进场动画 效果，
         * 所以只在 ViewGroup 第一次加载子 View 时显示一次，
         * 所以列表控件的 item 加载动画我们一般不使用它，
         * 我们会使用 列表 专门的 Item 加载动画，
         * 比如 recyclerView.setItemAnimator() 等方式。
         */
    }


    //旋转动画
    private void rotateT() {
        rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_t);
    }

    //缩放动画
    private void scaleT() {
        scaleAnim = AnimationUtils.loadAnimation(this, R.anim.scale_t);
    }

    //位移动画
    private void translateT() {
        translateAnim = AnimationUtils.loadAnimation(this, R.anim.translate_t);
        //translateAnim.setFillAfter(true);
        translateAnim.setRepeatCount(Integer.MAX_VALUE);
    }

    //渐变动画
    private void alpha() {
        alphaAnim = AnimationUtils.loadAnimation(this, R.anim.alpha_t);
        alphaAnim.setFillAfter(true);//设置动画结束后保留结束状态
    }

    //动画集
    private void setExam() {
        setAnim = AnimationUtils.loadAnimation(this, R.anim.set_exam);
        setAnim.setFillAfter(true);

        setAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                tv5.setText("我开始执行");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tv5.setText("我开始结束");
                tv5.startAnimation(scaleAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @OnClick({R.id.tv_1, R.id.tv_2, R.id.tv_3, R.id.tv_4, R.id.tv_5, R.id.tv_6, R.id.tv_0})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_1:
                tv1.startAnimation(alphaAnim);
                break;
            case R.id.tv_2:
                tv2.startAnimation(translateAnim);
                break;
            case R.id.tv_3:
                tv3.startAnimation(scaleAnim);
                break;
            case R.id.tv_4:
                tv4.startAnimation(rotateAnim);
                break;
            case R.id.tv_5:
                tv5.startAnimation(setAnim);
                break;
            case R.id.tv_6:
                createAnim();
                break;
            case R.id.tv_0:
                toAttr();//查看属性动画
                break;
        }
    }

    /**
     * 去往属性动画
     */
    private void toAttr() {
        AnimationSet set = new AnimationSet(true);
        Animation rotate = new RotateAnimation(0f, 1080f, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        Animation alpha = new AlphaAnimation(1.0f, 0.0f);
        Animation translate = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0, TranslateAnimation.RELATIVE_TO_PARENT, 0,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.75f, TranslateAnimation.RELATIVE_TO_PARENT, 0.75f);
        set.addAnimation(rotate);
        set.addAnimation(alpha);
        set.addAnimation(translate);
        set.setDuration(1500);
        set.setInterpolator(new DecelerateInterpolator()); //设置插值器 又快变慢
        tv0.startAnimation(set);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(MainActivity.this, TestSurfaceActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    private void createAnim() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float scale = metrics.density;

        Animation animation = new TranslateAnimation(0, 200 * scale, 0, 200 * scale);
        animation.setDuration(1500);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setStartOffset(1000); //延时开始执行
        animation.setRepeatCount(Integer.MAX_VALUE);
        tv6.startAnimation(animation);


        //动画集
        AnimationSet set = new AnimationSet(true);
        set.setRepeatMode(Animation.REVERSE);
        set.setRepeatCount(2);
        set.setDuration(3000);
        set.setStartOffset(1000); //延时执行
        //旋转  prvoXType 相对的参照物的类型 自身或父容器
        Animation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        //平移
        Animation translate = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, 1);

        //渐变
        Animation alpha = new AlphaAnimation(1.0f, 0.2f);

        //缩放
        Animation scaleAnim = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);

        set.addAnimation(rotate);
        set.addAnimation(translate);
        set.addAnimation(alpha);
        set.addAnimation(scaleAnim);

        tv6.startAnimation(set);

    }


}
