package peng.qtgm.t0426_agora.utils;

import android.content.Context;
import android.util.Log;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import peng.qtgm.t0426_agora.R;

import static peng.qtgm.t0426_agora.app.MyApplication.WP;

/**
 * @author peng_wang
 * @date 2019/4/26
 */
public class AgoraManager {


    private static AgoraManager instance = null;
    private RtcEngine mRtcEngine;
    private int uid = 0;

    /*
     * 构造函数
     */
    private AgoraManager() {
        if (instance == null) {
            synchronized (AgoraManager.class) {
                if (instance == null) {
                    instance = new AgoraManager();
                }
            }
        }
    }

    /*
     * 获取实例
     */
    public static AgoraManager getInstance() {
        return instance;
    }


    /*
     * 初始化SDK
     */
    public void initEngine(Context context,int uid) {
        this.uid = uid;
        try {
            mRtcEngine = RtcEngine.create(context, context.getString(R.string.agora_app_id), mRtcEventHandler);
            //同一频道只能设置一种频道模式。如果需要切换频道模式，请先调用 destroy 方法销毁后重新创建一个 Engine 实例
            mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);//设置频道模式
        } catch (Exception e) {
            Log.e(WP, Log.getStackTraceString(e));
            throw new RuntimeException("NEED TO check rtc sdk init fatal error\n" + Log.getStackTraceString(e));
        }
    }

    /*
     * 加入频道
     * 如果已在频道中，用户必须调用 leaveChannel 方法退出当前频道，才能进入下一个频道。
     * 传入能标识频道的频道 ID。输入相同频道 ID 的用户会进入同一个频道。
     * 频道内每个用户的 UID 必须是唯一的。如果将 UID 设为 0，系统将自动分配一个 UID。
     */
    public AgoraManager joinChannel(String channelName){
        mRtcEngine.joinChannel(null,channelName,null,uid);
        return this;
    }

    /**
     * 设置角色
     */
    public AgoraManager setClientRole(){
        mRtcEngine.setClientRole(Constants.CLIENT_ROLE_BROADCASTER);//主播
        //mRtcEngine.setClientRole(Constants.CLIENT_ROLE_AUDIENCE);//观众
        return this;
    }


















    /*
     * 声网SDK的回调
     */
    private IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {
        @Override
        public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
            super.onJoinChannelSuccess(channel, uid, elapsed);
        }

        @Override
        public void onLeaveChannel(RtcStats stats) {
            super.onLeaveChannel(stats);
        }
    };

}
