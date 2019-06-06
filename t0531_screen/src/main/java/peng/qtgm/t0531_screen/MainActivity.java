package peng.qtgm.t0531_screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import peng.qtgm.t0531_screen.utils.GlideImageLoader;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_iv_1)
    ImageView mainIv1;
    @BindView(R.id.main_iv_2)
    ImageView mainIv2;
    @BindView(R.id.main_iv_3)
    ImageView mainIv3;
    @BindView(R.id.main_iv_4)
    ImageView mainIv4;
    @BindView(R.id.main_iv_5)
    ImageView mainIv5;
    @BindView(R.id.main_iv_6)
    ImageView mainIv6;
    @BindView(R.id.main_iv_7)
    ImageView mainIv7;
    @BindView(R.id.main_iv_8)
    ImageView mainIv8;
    @BindView(R.id.main_iv_9)
    ImageView mainIv9;

    private String imagePath = "http://image.qulianwu.com/demo/image/1559198148320203000ybttm60u.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // xml center
        GlideImageLoader.mTestNone(this,imagePath,mainIv1);

        // test centerCrop
        GlideImageLoader.mTestcenterCrop(this,imagePath,mainIv2);

        // test centerInside
        GlideImageLoader.mTestcenterInside(this,imagePath,mainIv3);

        // test centerInside
        GlideImageLoader.mTestfitCenter(this,imagePath,mainIv4);

        // test circleCrop
        GlideImageLoader.mTestcircleCrop(this,imagePath,mainIv5);

        // test circleCrop
        GlideImageLoader.mTestNone(this,imagePath,mainIv6);

        // test circleCrop
        GlideImageLoader.mTestNone(this,imagePath,mainIv7);

        // test circleCrop
        GlideImageLoader.mTestNone(this,imagePath,mainIv8);

        // test circleCrop
        GlideImageLoader.mTestNone(this,imagePath,mainIv9);

    }
}
