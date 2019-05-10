package peng.qtgm.rx.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.meituan.android.walle.WalleChannelReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import peng.qtgm.rx.BuildConfig;
import peng.qtgm.rx.R;

public class ImageVersionActivity extends AppCompatActivity {

    @BindView(R.id.img_tv_channel)
    TextView tvChannel;
    @BindView(R.id.img_tv_version)
    TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_version);
        ButterKnife.bind(this);

        getVersionName();
    }

    /**
     * 获取渠道名字 和查看是否为线上 线下版本
     */
    private void getVersionName() {
        String channel = WalleChannelReader.getChannel(this.getApplicationContext()); //获取渠道名字
        tvChannel.setText("本次安装的渠道包为:"+channel);
        if (BuildConfig.DEBUG == true) {
            tvVersion.setText("这是DEGUB版本");
        }else{
            tvVersion.setText("这是RELASE版本");
        }
    }

}
