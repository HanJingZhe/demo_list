package com.example.tanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author peng_wang
 * @des 属性动画
 */
public class AttrActivity extends AppCompatActivity {

    @BindView(R.id.attr_tv_0)
    TextView attrTv0;
    @BindView(R.id.attr_tv_1)
    TextView attrTv1;
    @BindView(R.id.attr_tv_2)
    TextView attrTv2;
    @BindView(R.id.attr_tv_3)
    TextView attrTv3;
    @BindView(R.id.attr_tv_4)
    TextView attrTv4;
    @BindView(R.id.attr_tv_5)
    TextView attrTv5;

    private ObjectAnimator alpha;
    private ObjectAnimator translate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attr);
        ButterKnife.bind(this);

        alpha = ObjectAnimator.ofFloat(attrTv0, "alpha", 1.0f, 0.2f,1.0f);
        alpha.setDuration(3000);

        translate = ObjectAnimator.ofFloat(attrTv2,"translationX",200f);
        translate.setDuration(1000);

    }

    @OnClick({R.id.attr_tv_0, R.id.attr_tv_1, R.id.attr_tv_2, R.id.attr_tv_3, R.id.attr_tv_4,R.id.attr_tv_5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.attr_tv_0:
                alpha();
                break;
            case R.id.attr_tv_1:
                scale();
                break;
            case R.id.attr_tv_2:
                translate();
                break;
            case R.id.attr_tv_3:
                rotate();
                break;
            case R.id.attr_tv_4:
                set();
                break;
            case R.id.attr_tv_5:
                api();
                break;
        }
    }

    private void api() {
        //attrTv5.animate().alpha(0.2f).setDuration(1500);

        //  // 组合动画:将按钮变成透明状态再移动到(500,500)处
        //attrTv5.animate().alpha(0.2f).x(500).y(500).setDuration(1500);

        //attrTv5.animate().rotation(360f).setDuration(1500);
        //连续调用 根据自身当前状态
        //attrTv5.animate().rotationBy(100).setDuration(1500);
        //attrTv5.animate().rotationX(90).setDuration(500);
        //attrTv5.animate().rotationY(60).setDuration(500);
        //attrTv5.animate().rotationYBy(60).setDuration(500);

        //attrTv5.animate().translationX(50).setDuration(500);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            attrTv5.animate().zBy(10).setDuration(500);
        }*/
        //attrTv5.animate().scaleX(1.5f).setDuration(500);

        attrTv5.animate().scaleX(2.0f).scaleY(2.0f).setDuration(500);
    }

    private void set() {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(alpha,translate);
        set.setDuration(3000);
        set.start();

        /**
         * AnimatorSet.play(Animator anim)   ：播放当前动画
         * AnimatorSet.after(long delay)   ：将现有动画延迟x毫秒后执行
         * AnimatorSet.with(Animator anim)   ：将现有动画和传入的动画同时执行
         * AnimatorSet.after(Animator anim)   ：将现有动画插入到传入的动画之后执行
         * AnimatorSet.before(Animator anim) ：  将现有动画插入到传入的动画之前执行
         */

        /**
         * ObjectAnimator a1 = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0f);
         * ObjectAnimator a2 = ObjectAnimator.ofFloat(view, "translationY", 0f, viewWidth);
         * ......
         * AnimatorSet animSet = new AnimatorSet();
         * animSet.setDuration(5000);
         * animSet.setInterpolator(new LinearInterpolator());
         * //animSet.playTogether(a1, a2, ...); //两个动画同时执行
         * animSet.play(a1).after(a2); //先后执行
         * ......//其他组合方式
         * animSet.start();
         */

        /**
         * anim.addListener(new AnimatorListener() {
         *           @Override
         *           public void onAnimationStart(Animation animation) {
         *               //动画开始时执行
         *           }
         *
         *            @Override
         *           public void onAnimationRepeat(Animation animation) {
         *               //动画重复时执行
         *           }
         *
         *          @Override
         *           public void onAnimationCancel()(Animation animation) {
         *               //动画取消时执行
         *           }
         *
         *           @Override
         *           public void onAnimationEnd(Animation animation) {
         *               //动画结束时执行
         *           }
         *       });
         *
         * // 特别注意：每次监听必须4个方法都重写。
         */

        //采用动画适配器（AnimatorListenerAdapter），解决 实现接口繁琐 的问题
        /*set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                translate.start();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });*/
    }

    private void rotate() {
        /*ObjectAnimator rotation = ObjectAnimator.ofFloat(attrTv3,"rotation",360f);
        rotation.setDuration(1500);
        rotation.start();*/

        ObjectAnimator rotationX = ObjectAnimator.ofFloat(attrTv3,"rotationX",360f);
        rotationX.setDuration(1500);
        rotationX.start();

        ObjectAnimator rotationY = ObjectAnimator.ofFloat(attrTv3,"rotationY",360f);
        rotationY.setDuration(1500);
        rotationY.start();
    }

    private void translate() {

        translate.start();

        ObjectAnimator translateY = ObjectAnimator.ofFloat(attrTv2,"translationY",200f);
        translateY.setDuration(1000);
        translateY.start();
    }

    private void scale() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(attrTv1,"scaleX",2.0f);
        scaleX.setDuration(1000);
        scaleX.start();

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(attrTv1,"scaleY",2.0f);
        scaleY.setDuration(1000);
        scaleY.start();
    }

    private void alpha() {
        alpha.start();
    }
}
