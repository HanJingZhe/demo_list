package peng.qtgm.t0426_agora.utils;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceView;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoEncoderConfiguration;
import peng.qtgm.t0426_agora.R;

import static peng.qtgm.t0426_agora.app.MyApplication.WP;

/**
 * @author peng_wang
 * @date 2019/4/26
 */
public class AgoraManager {

    private static AgoraManager mAgoraManage;
    private SparseArray<SurfaceView> mSurfaceViews;
    private RtcEngine mRtcEngine;
    private int uid = 0;

    /*
     * 构造
     */
    public AgoraManager() {
        mSurfaceViews = new SparseArray<>();
    }

    /*
     * 获取实例
     */
    public static AgoraManager getInstance() {
        if (mAgoraManage == null) {
            synchronized (AgoraManager.class) {
                if (mAgoraManage == null) {
                    mAgoraManage = new AgoraManager();
                }
            }
        }
        return mAgoraManage;
    }


    /*
     * agora 的回调接口
     */
    private IRtcEngineEventHandler mIRtcEngineEventHandler = new IRtcEngineEventHandler() {

        /**
         * 当获取用户uid的远程视频的回调
         */
        @Override
        public void onFirstRemoteVideoDecoded(int uid, int width, int height, int elapsed) {
            if(mOnPartyListener != null) mOnPartyListener.onGetRemoteVideo(uid);
        }

        /**
         * 加入频道成功的回调
         */
        @Override
        public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
            if(mOnPartyListener != null) mOnPartyListener.onJoinChannelSuccess(channel,uid);
        }

        /**
         * 退出频道
         */
        @Override
        public void onLeaveChannel(RtcStats stats) {
            if(mOnPartyListener != null) mOnPartyListener.onLeaveChannelSuccess();
        }

        /**
         * 用户uid离线时的回调
         */
        @Override
        public void onUserOffline(int uid, int reason) {
            if(mOnPartyListener != null) mOnPartyListener.onUserOffline(uid);
        }
    };

    /*
     * 初始化
     */
    public void init(Context context) {
        try {
            mRtcEngine = RtcEngine.create(context, context.getString(R.string.agora_app_id), mIRtcEngineEventHandler);
            //开启视频功能
            mRtcEngine.enableVideo();
            //视频配置，设置为360P
            mRtcEngine.setVideoProfile(Constants.VIDEO_PROFILE_360P, false);
            //mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);//设置为直播模式
            mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_COMMUNICATION);//设置为通信模式默认
            setVideoEncoderConfiguration();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     * 设置本地视频，即前置摄像头预览
     */
    public AgoraManager setupLocalVideo(Context context) {
        SurfaceView surfaceView = RtcEngine.CreateRendererView(context);
        mSurfaceViews.put(uid, surfaceView);
        mRtcEngine.setupLocalVideo(new VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_HIDDEN, uid));
        return this;//返回AgoraManager以作链式调用
    }

    /*
     * setupRemoteVideo
     * 设置远程视频
     */
    public AgoraManager setupRemoteVideo(int uid,Context context){
        SurfaceView surfaceView = RtcEngine.CreateRendererView(context);
        mSurfaceViews.put(uid, surfaceView);
        mRtcEngine.setupRemoteVideo(new VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_ADAPTIVE, uid));
        return this;
    }

    /*
     * 加入频道
     * channel : 频道ID 相同即一个频道
     */
    public AgoraManager joinChannel(String channel) {
        mRtcEngine.joinChannel(null, channel, null, 0);
        return this;
    }

    /*
     * 开启预览
     */
    public void startPreview() {
        mRtcEngine.startPreview();
    }

    /*
     * 停止预览
     */
    public void stopPreview() {
        mRtcEngine.stopPreview();
    }


    /*
     * 退出当前频道
     */
    public void leaveChannel() {
        mRtcEngine.leaveChannel();
    }

    /**
     * 设置角色 主播/观众
     * role: 1/主播  2/观众
     */
    public AgoraManager setRole(int role) {
        mRtcEngine.setClientRole(role == 1 ? Constants.CLIENT_ROLE_BROADCASTER : Constants.CLIENT_ROLE_AUDIENCE); //主播
        return this;
    }

    /*
     * 设置视频的编码属性
     */
    public AgoraManager setVideoEncoderConfiguration() {
        mRtcEngine.setVideoEncoderConfiguration(
                new VideoEncoderConfiguration(
                        new VideoEncoderConfiguration.VideoDimensions(360, 640),
                        VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15, VideoEncoderConfiguration.STANDARD_BITRATE,
                        VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT));
        return this;
    }

    public SurfaceView getSurfaceView(int uid) {
        return mSurfaceViews.get(uid);
    }

    /*
     * 返回本地预览
     */
    public SurfaceView getLocalSurfaceView() {
        return mSurfaceViews.get(uid);
    }

    private OnPartyListener mOnPartyListener;

    public interface OnPartyListener {
        void onJoinChannelSuccess(String channel, int uid);
        void onGetRemoteVideo(int uid);
        void onLeaveChannelSuccess();
        void onUserOffline(int uid);
    }

    public void setmOnPartyListener(OnPartyListener listener) {
        mOnPartyListener = listener;
    }


}
