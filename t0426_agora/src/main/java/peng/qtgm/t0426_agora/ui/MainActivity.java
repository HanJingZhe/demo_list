package peng.qtgm.t0426_agora.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ninetripods.aopermission.permissionlib.annotation.NeedPermission;

import peng.qtgm.t0426_agora.R;
import peng.qtgm.t0426_agora.utils.AgoraManager;
import peng.qtgm.t0426_agora.utils.DialogInputUtil;
import peng.qtgm.t0426_agora.utils.OnDialogClickListener;

import static peng.qtgm.t0426_agora.ui.RoomActivity.CHANNELNAME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.main_btn);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn:
                inputCode();
                break;
        }
    }

    /**
     * 输入频道名字
     */
    @NeedPermission(value = {Manifest.permission.CAMERA})
    private void inputCode() {
        new DialogInputUtil.InputBuilder(this)
                .setTitle("进入直播")
                .setDes("请输入进入直播的代码")
                .setmClickListener(new OnDialogClickListener() {
                    @Override
                    public void onCommitClick(String etContent) {
                        AgoraManager.getInstance().init(getContext(), getString(R.string.agora_app_id));
                        Intent intent = new Intent(getContext(),RoomActivity.class);
                        intent.putExtra(CHANNELNAME,etContent);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancalClick(View v) {
                    }
                }).show();
    }

    private Context getContext(){
        return this;
    }
}
