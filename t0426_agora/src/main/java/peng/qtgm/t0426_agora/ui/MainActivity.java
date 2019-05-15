package peng.qtgm.t0426_agora.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ninetripods.aopermission.permissionlib.annotation.NeedPermission;

import butterknife.ButterKnife;
import peng.qtgm.t0426_agora.R;
import peng.qtgm.t0426_agora.utils.AgoraManager;
import peng.qtgm.t0426_agora.utils.DialogInputUtil;

import static peng.qtgm.t0426_agora.ui.RoomActivity.CHANNELNAME;
import static peng.qtgm.t0426_agora.ui.RoomActivity.CHANNELSTATE;
import static peng.qtgm.t0426_agora.ui.RoomActivity.USERID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int ANCHOR = 1;
    public static final int AUDIENCE = 2;

    private Button btnOpen;
    private Button btnJoin;

    private int uid;

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

        showInputDialog();
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
                .setmClickListener(etContent -> {
                    Intent intent = new Intent(getContext(), RoomActivity.class);
                    intent.putExtra(CHANNELNAME, etContent);
                    intent.putExtra(CHANNELSTATE, state);
                    intent.putExtra(USERID,uid);
                    startActivity(intent);
                }).show();
    }

    //show dialog
    private void showInputDialog() {
        new DialogInputUtil.InputBuilder(this)
                .setTitle("进入直播")
                .setmClickListener(etContent -> inputUid(etContent))
                .setDes("请输入你的用户名(数字int类型)")
                .show();
    }

    //dialog click
    private void inputUid(String etContent) {
        try {
            uid = Integer.parseInt(etContent);
            //initAgoraSDK
            AgoraManager.getInstance().initEngine(getContext(),uid);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "用户名类型输入有误", Toast.LENGTH_LONG).show();
            showInputDialog();
        }
    }


}
