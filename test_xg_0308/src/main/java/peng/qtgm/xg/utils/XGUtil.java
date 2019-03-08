package peng.qtgm.xg.utils;

import android.content.Context;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushManager;

/**
 * @author peng_wang
 * @date 2019/3/8
 */
public class XGUtil {

    public static final String GUEST = "guest";

    public static final String MEMST = "memst";

    /**
     * 注册
     */
    public static void registerPush(Context context){
        XGPushManager.registerPush(context);
    }



}
