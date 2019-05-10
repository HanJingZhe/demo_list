package peng.qtgm.t0426_agora.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ninetripods.aopermission.permissionlib.annotation.NeedPermission;

import butterknife.ButterKnife;
import peng.qtgm.t0426_agora.R;
import peng.qtgm.t0426_agora.utils.DialogInputUtil;
import peng.qtgm.t0426_agora.utils.OnDialogClickListener;

import static peng.qtgm.t0426_agora.ui.RoomActivity.CHANNELNAME;
import static peng.qtgm.t0426_agora.ui.RoomActivity.CHANNELSTATE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnOpen;
    private Button btnJoin;

    private static final int ANCHOR = 1;
    private static final int AUDIENCE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        requestPermission();
        btnOpen = findViewById(R.id.main_btn_open);
        btnJoin = findViewById(R.id.main_btn_join);
        btnOpen.setOnClickListener(this);
        btnJoin.setOnClickListener(this);
    }

    @NeedPermission(value = {Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode = 100)
    private void requestPermission() {//申请权限

    }

    private Context getContext() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_open:
                startOnlineVideo(ANCHOR);
                break;
            case R.id.main_btn_join:
                startOnlineVideo(AUDIENCE);
                break;
        }
    }

    /**
     * 开启/加入 直播
     */
    private void startOnlineVideo(final int state) {
        new DialogInputUtil.InputBuilder(getContext())
                .setTitle(state == 1 ? "开启直播" : "加入直播")
                .setDes(state == 1 ? "请输入您要开启的直播房号" : "请输入您要加入的直播房号")
                .setmClickListener(new OnDialogClickListener() {
                    @Override
                    public void onCommitClick(String etContent) {
                        Intent intent = new Intent(getContext(), RoomActivity.class);
                        intent.putExtra(CHANNELNAME, etContent);
                        intent.putExtra(CHANNELSTATE, state);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancalClick(View v) {

                    }
                }).show();
    }



}
