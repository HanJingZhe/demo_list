package peng.qtgm.tum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_tv1)
    TextView mainTv1;
    @BindView(R.id.main_tv2)
    TextView mainTv2;
    @BindView(R.id.main_tv3)
    TextView mainTv3;
    @BindView(R.id.main_tv4)
    TextView mainTv4;
    private UMShareAPI umShareAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        umShareAPI = UMShareAPI.get(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    @OnClick({R.id.main_tv1, R.id.main_tv2,R.id.main_tv3,R.id.main_tv4})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_tv1:
                new ShareAction(MainActivity.this).withText("hello").setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                        .setCallback(shareListener).open();
                break;
            case R.id.main_tv2:
                /*new ShareAction(MainActivity.this)
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withText("hello")//分享内容
                        .setCallback(shareListener)//回调监听器
                        .share();*/
                UMImage image = new UMImage(MainActivity.this, R.mipmap.ic_hot);//资源文件
                new ShareAction(MainActivity.this).setPlatform(SHARE_MEDIA.WEIXIN).withText("hello").withMedia(image).setCallback(shareListener).share();
                break;
            case R.id.main_tv3:
                umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.WEIXIN, authListener);
                break;
            case R.id.main_tv4:
                umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, authListener);
                break;
        }

    }
    /**
     * 登录回调
     */
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();

            Log.e("王鹏","uid"+data.get("uid"));
            Log.e("王鹏","name"+data.get("name"));
            Log.e("王鹏","gender"+data.get("gender"));
            Log.e("王鹏","iconurl"+data.get("iconurl"));

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(MainActivity.this, "失败：" + t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
    /**
     * 分享回调
     */
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };
}
