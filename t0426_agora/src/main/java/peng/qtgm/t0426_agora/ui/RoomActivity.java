package peng.qtgm.t0426_agora.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;

import peng.qtgm.t0426_agora.R;
import peng.qtgm.t0426_agora.utils.AgoraManager;

import static peng.qtgm.t0426_agora.ui.MainActivity.AUDIENCE;

public class RoomActivity extends AppCompatActivity {

    public static final String CHANNELNAME = "channelName"; //房间名
    public static final String CHANNELSTATE = "channelstate";//加入进来的身份
    public static final String USERID = "userid";//加入进来的uid

    private FrameLayout flPreview;
    private FrameLayout flMe;
    private LinearLayout llBullet;
    private EditText etBullet;
    private Button btnSend;


    private String channelName = "common"; //直播间name
    private int channelState = -1;  // 主播 ANCHOR 1/ 观众 AUDIENCE 2
    private int uid = 0; //用户uid


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        //initView
        flPreview = findViewById(R.id.room_fl_preview);//预览容器
        flMe = findViewById(R.id.room_fl_me);//自己视角
        llBullet = findViewById(R.id.room_ll_bullet);
        etBullet = findViewById(R.id.room_et_bullet);
        btnSend = findViewById(R.id.room_btn_send);

        //initData
        channelName = getIntent().getStringExtra(CHANNELNAME); //进入频道name
        channelState = getIntent().getIntExtra(CHANNELSTATE, -1); //进入频道 state 角色
        uid = getIntent().getIntExtra(USERID, 0);

        SurfaceView surfaceView = AgoraManager.getInstance().setClientRole(channelState).joinChannel(channelName).setupLocalVideo(getContext());
        flPreview.removeAllViews();
        flPreview.addView(surfaceView);

        llBullet.setVisibility(channelState == AUDIENCE ? View.VISIBLE : View.GONE);
    }


    // return context
    private Context getContext() {
        return this;
    }

}
