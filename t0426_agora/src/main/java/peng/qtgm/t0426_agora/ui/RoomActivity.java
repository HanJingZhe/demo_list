package peng.qtgm.t0426_agora.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import peng.qtgm.t0426_agora.R;
import peng.qtgm.t0426_agora.utils.AgoraManager;
import peng.qtgm.t0426_agora.utils.DialogInputUtil;
import peng.qtgm.t0426_agora.utils.OnDialogClickListener;

public class RoomActivity extends AppCompatActivity {

    public static final String CHANNELNAME = "channelName"; //房间名
    public static final String CHANNELSTATE = "channelstate";//加入进来的身份

    private FrameLayout flPreview;
    private FrameLayout flMe;

    private String channelName = "common"; //直播间name
    private int channelState = -1;  // 主播/观众
    private int uid = 0; //用户uid

    private DialogInputUtil.InputBuilder inputBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        flPreview = findViewById(R.id.room_fl_preview);//预览容器
        flMe = findViewById(R.id.room_fl_me);//自己视角

        channelName = getIntent().getStringExtra(CHANNELNAME); //进入频道name
        channelState = getIntent().getIntExtra(CHANNELSTATE,-1); //进入频道state 角色
        showInputDialog(); //提示用户输入本次直播的uid
    }

    //show dialog
    private void showInputDialog() {
        inputBuilder = new DialogInputUtil.InputBuilder(this)
                .setTitle("进入直播")
                .setDes("请输入你的用户名(数字int类型)")
                .setmClickListener(mClickListener);
        inputBuilder.show();
    }

    //dialog click
    private OnDialogClickListener mClickListener = new OnDialogClickListener() {
        @Override
        public void onCommitClick(String etContent) {
            try {
                uid = Integer.parseInt(etContent);
                AgoraManager.getInstance().setRole(channelState);//设置角色 主播/观众
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "用户名类型输入有误", Toast.LENGTH_LONG).show();
                inputBuilder.show();
            }
        }
    };

    //room   listener
    AgoraManager.OnPartyListener onPartyListener = new AgoraManager.OnPartyListener() {
        @Override
        public void onJoinChannelSuccess(String channel, int uid) {//加入成功

        }

        @Override
        public void onGetRemoteVideo(final int uid) {//获取远端成功
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //注意SurfaceView要创建在主线程
                    AgoraManager.getInstance().setupRemoteVideo(uid, getContext());
                    flPreview.removeAllViews();
                    flPreview.addView(AgoraManager.getInstance().getSurfaceView(uid));
                }
            });
        }

        @Override
        public void onLeaveChannelSuccess() {//离开成功

        }

        @Override
        public void onUserOffline(int uid) {

        }
    };

    // return context
    private Context getContext() {
        return this;
    }

    //add contain
    @Override
    protected void onResume() {
        super.onResume();
        //先清空容器
        flPreview.removeAllViews();
        AgoraManager.getInstance().setupLocalVideo(this).startPreview();
        flPreview.addView(AgoraManager.getInstance().getLocalSurfaceView());
    }

    //stop   preview
    @Override
    protected void onPause() {
        super.onPause();
        AgoraManager.getInstance().stopPreview();
    }
}
