package peng.qtgm.t0426_agora.app;

import android.app.Application;
import android.content.Context;

/**
 * @author peng_wang
 * @date 2019/4/26
 */
public class MyApplication extends Application {

    public static final String WP = "王鹏";
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getmContext() {
        return mContext;
    }
}
