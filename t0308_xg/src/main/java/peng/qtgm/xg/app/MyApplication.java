package peng.qtgm.xg.app;

import android.app.Application;
import android.util.Log;

import com.tencent.android.tpush.XGIOperateCallback;

import peng.qtgm.xg.utils.XGUtil;

/**
 * @author peng_wang
 * @date 2019/3/8
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        XGUtil.registerPush(this);
    }
}
