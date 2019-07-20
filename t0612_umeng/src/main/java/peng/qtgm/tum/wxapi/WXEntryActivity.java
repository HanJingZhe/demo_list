package peng.qtgm.tum.wxapi;

import android.os.Bundle;

import com.umeng.socialize.weixin.view.WXCallbackActivity;

import peng.qtgm.tum.R;

public class WXEntryActivity extends WXCallbackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
    }
}
