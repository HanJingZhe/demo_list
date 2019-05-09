package peng.qtgm.rx;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.meituan.android.walle.WalleChannelReader;
import com.ninetripods.aopermission.permissionlib.annotation.NeedPermission;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_tv_channel)
    TextView tvChannel;
    @BindView(R.id.main_tv_version)
    TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        String channel = WalleChannelReader.getChannel(this.getApplicationContext());
        tvChannel.setText("本次安装的渠道包为:"+channel);
        if (BuildConfig.DEBUG == true) {
            tvVersion.setText("这是DEGUB版本");
        }else{
            tvVersion.setText("这是RELASE版本");
        }
    }


}
