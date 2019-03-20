package peng.qtgm.demo_list;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.iceteck.silicompressorr.VideoCompress;
import com.ninetripods.aopermission.permissionlib.annotation.NeedPermission;
import com.ninetripods.aopermission.permissionlib.annotation.PermissionDenied;
import com.ninetripods.aopermission.permissionlib.bean.DenyBean;
import com.zhihu.matisse.Matisse;

import java.io.File;

import peng.qtgm.demo_list.utils.PictureUtil;

public class MainActivity extends AppCompatActivity {

    private Button btnChoose;
    private ImageView ivShow;

    private static final int IMAGE_ZHIHU_CODE = 10001; // 选择图片的请求码  ---知乎ui
    private static final int IMAGE_PICK_CODE = 10002; // 选择图片的请求码  ---系统ui
    private static final int CROP_IMAGE_CODE = 10003; // 选择图片的请求码  ---系统ui

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChoose = findViewById(R.id.btn_choose);
        ivShow = findViewById(R.id.iv_show);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                needChooseImage();
            }
        });
    }


    @NeedPermission(value = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA})
    public void needChooseImage() {
        //PictureUtil.openAlbum(this,IMAGE_PICK_CODE);
        PictureUtil.openPictures(this, IMAGE_ZHIHU_CODE, 1);
    }

    @PermissionDenied
    public void denyPermission(DenyBean bean) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_ZHIHU_CODE && resultCode == RESULT_OK) {  //知乎返回的图片
            //PictureUtil.cropImage(this, Matisse.obtainResult(data).get(0), CROP_IMAGE_CODE);
            video(data);
        } else if (requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK) { //系统图库返回的图片
            PictureUtil.cropImage(this, data.getData(), CROP_IMAGE_CODE);
        } else if (requestCode == CROP_IMAGE_CODE && resultCode == RESULT_OK) { //调用裁剪
            Bundle extras = data.getExtras();
            Bitmap selectedBitmap = extras.getParcelable("data");
            if (extras != null) {//裁剪结果
                ivShow.setImageBitmap(selectedBitmap);
            }
        }
    }

    private void video(Intent data) {
        String s = Matisse.obtainPathResult(data).get(0);
        compressVideoResouce(this,s);

    }


    public void compressVideoResouce(Context mContext, String filepath) {
        if (TextUtils.isEmpty(filepath)) {
            Toast.makeText(mContext, "请先选择转码文件！", Toast.LENGTH_SHORT).show();
            return;
        }
        File file = PictureUtil.create(this);
        String path = file.getPath();
        VideoCompress.compressVideoLow(filepath, path, new VideoCompress.CompressListener() {
            @Override
            public void onStart() {
                Log.d("王鹏", "onStart: "+"压缩开始");
            }

            @Override
            public void onSuccess() {
                Log.d("王鹏", "onStart: "+"压缩完成");
            }

            @Override
            public void onFail() {
                Log.d("王鹏", "onStart: "+"压缩失败");
            }

            @Override
            public void onProgress(float percent) {
                Log.d("王鹏", "onStart: "+"压缩中:"+percent);
            }
        });
    }


}
