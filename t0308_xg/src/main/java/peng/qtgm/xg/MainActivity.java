package peng.qtgm.xg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tencent.android.tpush.XGLocalMessage;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.XGPushRegisterResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                push();
            }
        });
    }

    private void push() {

        /**
         *  XGPushManager         Push服务推送
         *  XGPushConfig          Push服务配置项接口
         *  XGPushBaseReceiver    接收消息和结果反馈的receiver，需要开发者自己在AndroidManifest.xml静态注册
         */
        //新建本地通知
        XGLocalMessage localMsg = new XGLocalMessage();
        localMsg.setType(1);
        localMsg.setTitle("HELLO WORLD");
        localMsg.setContent("本地推送的内容");
        localMsg.setDate("20140930");//设置日期
        //设置触发的时间
        localMsg.setHour("19");
        localMsg.setMin("31");

        //设置动作类型：1打开activity或app本身，2打开浏览器，3打开Intent ，4通过包名打开应用
        localMsg.setAction_type(1);
        localMsg.setActivity("peng.qtgm.xg.AssignActivity");
        localMsg.setUrl("http://www.baidu.com");

        // 设置下载应用URL
        //localMsg.setPackageDownloadUrl("http://softfile.3g.qq.com:8080/msoft/179/1105/10753/MobileQQ1.0(Android)_Build0198.apk");

        XGPushManager.addLocalNotification(this,localMsg);

    }


}
