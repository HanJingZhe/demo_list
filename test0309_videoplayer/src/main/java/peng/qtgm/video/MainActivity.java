package peng.qtgm.video;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninetripods.aopermission.permissionlib.annotation.NeedPermission;
import com.zhihu.matisse.Matisse;

import peng.qtgm.video.compress.VideoUtil;
import peng.qtgm.video.utils.PictureUtil;

public class MainActivity extends AppCompatActivity {

    public static final int VIDEO_PICK_CODE = 10001;
    private TextView tvShow;
    private ImageView ivShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvShow = findViewById(R.id.tv_show);
        ivShow = findViewById(R.id.iv_show);

        findViewById(R.id.btn_pick_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                needChooseImage();
            }
        });
    }


    @NeedPermission(value = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA})
    public void needChooseImage() {
        PictureUtil.openPictures(this, VIDEO_PICK_CODE, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIDEO_PICK_CODE && resultCode == RESULT_OK) {
            String locPath = Matisse.obtainPathResult(data).get(0);
            VideoUtil.compressVideoResouce(this, locPath, new VideoUtil.CompressListener() {
                @Override
                public void compressSuccess(String path) {

                }

                @Override
                public void compressProgress(float progress) {

                }

                @Override
                public void compressFailure(String msg) {

                }
            });
        }
    }
}
