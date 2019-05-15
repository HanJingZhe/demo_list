package peng.qtgm.t0426_agora.utils;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.Toast;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoEncoderConfiguration;
import peng.qtgm.t0426_agora.R;
import peng.qtgm.t0426_agora.app.MyApplication;

import static peng.qtgm.t0426_agora.app.MyApplication.WP;
import static peng.qtgm.t0426_agora.ui.MainActivity.ANCHOR;

/**
 * @author peng_wang
 * @date 2019/4/26
 */
public class AgoraManager {

    private static AgoraManager instance;
    private RtcEngine mRtcEngine;
    private int uid = 0;

    /*
     * 构造函数
     */
    private AgoraManager() {

    }

    /*
     * 获取实例
     */
    public static AgoraManager getInstance() {
        if (instance == null) {
            synchronized (AgoraManager.class) {
                if (instance == null) {
                    instance = new AgoraManager();
                }
            }
        }
        return instance;
    }


    /*
     * 初始化SDK
     */
    public AgoraManager initEngine(Context context, int uid) {
        this.uid = uid;
        try {
            mRtcEngine = RtcEngine.create(context, context.getString(R.string.agora_app_id), mRtcEventHandler);
            //同一频道只能设置一种频道模式。如果需要切换频道模式，请先调用 destroy 方法销毁后重新创建一个 Engine 实例
            mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);//设置频道模式
            //mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_COMMUNICATION);
        } catch (Exception e) {
            Log.e(WP, Log.getStackTraceString(e));
            throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
        }
        return this;
    }

    /*
     * 加入频道
     * 如果已在频道中，用户必须调用 leaveChannel 方法退出当前频道，才能进入下一个频道。
     * 传入能标识频道的频道 ID。输入相同频道 ID 的用户会进入同一个频道。
     * 频道内每个用户的 UID 必须是唯一的。如果将 UID 设为 0，系统将自动分配一个 UID。
     */
    public AgoraManager joinChannel(String channelName) {
        mRtcEngine.joinChannel(null, channelName, null, uid);
        return this;
    }

    /*
     * 设置角色
     */
    public AgoraManager setClientRole(int state) {
        mRtcEngine.setClientRole(state == ANCHOR ? Constants.CLIENT_ROLE_BROADCASTER : Constants.CLIENT_ROLE_AUDIENCE);//主播
        return this;
    }

    /*
     * 设置视频编码属性
     */
    public void setConfiguration() {
        VideoEncoderConfiguration.ORIENTATION_MODE
                orientationMode =
                VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT;
        VideoEncoderConfiguration.VideoDimensions dimensions = new VideoEncoderConfiguration.VideoDimensions(360, 640);
        VideoEncoderConfiguration videoEncoderConfiguration = new VideoEncoderConfiguration(dimensions, null, 1, orientationMode);
        mRtcEngine.setVideoEncoderConfiguration(videoEncoderConfiguration);
    }

    /*
     * 设置本地视频视图
     */
    public SurfaceView setupLocalVideo(Context context) {
        SurfaceView surfaceView = RtcEngine.CreateRendererView(context);
        surfaceView.setZOrderMediaOverlay(true);
        mRtcEngine.setupLocalVideo(new VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_ADAPTIVE, uid));
        return surfaceView;
    }

    /*
     * 设置远端视频视图
     */
    private SurfaceView setupRemoteVideo(Context context) {
        SurfaceView surfaceView = RtcEngine.CreateRendererView(context);
        mRtcEngine.setupRemoteVideo(new VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_ADAPTIVE, uid));
        return surfaceView;
    }

    /*
     * 离开频道  如果立即销毁则无法触发回调
     */
    private void leaveChannel() {
        mRtcEngine.leaveChannel();
    }


    /*
     * 声网SDK的回调
     */
    private IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {

        /**
         * 加入成功
         */
        @Override
        public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
            super.onJoinChannelSuccess(channel, uid, elapsed);
            Toast.makeText(MyApplication.getmContext(),"加入%s频道成功"+channel,Toast.LENGTH_LONG).show();
        }

        /**
         * 离开成功
         */
        @Override
        public void onLeaveChannel(RtcStats stats) {
            super.onLeaveChannel(stats);
            Toast.makeText(MyApplication.getmContext(),"离开频道成功",Toast.LENGTH_LONG).show();
        }

    };

}
