package peng.qtgm.t0426_agora.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ninetripods.aopermission.permissionlib.annotation.NeedPermission;

import peng.qtgm.t0426_agora.R;
import peng.qtgm.t0426_agora.utils.DialogInputUtil;
import peng.qtgm.t0426_agora.utils.OnDialogClickListener;

import static peng.qtgm.t0426_agora.ui.RoomActivity.CHANNELNAME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private static final int PERMISSION_REQ_ID_RECORD_AUDIO = 0;
    private static final int PERMISSION_REQ_ID_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.main_btn);
        btn.setOnClickListener(this);


        //检查录音权限，如果没有，则申请
        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO, PERMISSION_REQ_ID_RECORD_AUDIO)) {
            //如果有录音权限，则再检查摄像头权限
            checkSelfPermission(Manifest.permission.CAMERA, PERMISSION_REQ_ID_CAMERA);
        }
    }

    public boolean checkSelfPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            //如果没有权限，则申请
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            return false;
        }
        return true;
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
    @NeedPermission(value = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode = 100)
    private void inputCode() {
        new DialogInputUtil.InputBuilder(this)
                .setTitle("进入直播")
                .setDes("请输入进入直播的代码")
                .setmClickListener(new OnDialogClickListener() {
                    @Override
                    public void onCommitClick(String etContent) {
                        Intent intent = new Intent(getContext(), RoomActivity.class);
                        intent.putExtra(CHANNELNAME, etContent);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancalClick(View v) {
                    }
                }).show();
    }

    private Context getContext() {
        return this;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {

        switch (requestCode) {
            case PERMISSION_REQ_ID_RECORD_AUDIO: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkSelfPermission(Manifest.permission.CAMERA, PERMISSION_REQ_ID_CAMERA);
                } else {
                    Toast.makeText(this, "权限被拒绝，无法开pa", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            }
            case PERMISSION_REQ_ID_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "权限被拒绝，无法开pa", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            }
        }
    }

}
