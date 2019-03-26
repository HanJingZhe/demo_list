package peng.qtgm.demo_list;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ninetripods.aopermission.permissionlib.annotation.NeedPermission;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import peng.qtgm.demo_list.utils.PictureUtil;

public class MainActivity extends AppCompatActivity {

    private Button btnChoose;
    private ImageView ivShow;

    private static final int IMAGE_ZHIHU_CODE = 10001; // 选择图片的请求码  ---知乎ui
    private static final int IMAGE_PICK_CODE = 10002; // 选择图片的请求码  ---系统ui
    private static final int IMAGE_CROP_CODE = 20001;

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
        PictureUtil.openPictures(this, IMAGE_ZHIHU_CODE, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_ZHIHU_CODE && resultCode == RESULT_OK) {
            List<String> images = Matisse.obtainPathResult(data);
            List<Uri> imageUris = Matisse.obtainResult(data);
            Glide.with(this).load(images.get(0)).into(ivShow);
            PictureUtil.cropImage(this, imageUris.get(0), IMAGE_CROP_CODE);
        } else if (requestCode == IMAGE_CROP_CODE && resultCode == RESULT_OK) {
            /*if (imageUri != null) {
                Bitmap bitmap = decodeUriAsBitmap(imageUri);
                ivShow.setImageBitmap(bitmap);
            }*/
            File cropFile = new File(PictureUtil.getMyPetRootDirectory(), "crop.jpg");
            Uri cropUri = Uri.fromFile(cropFile);
            //ivShow.setImageURI(cropUri);
            //Glide.with(this).load(cropFile).into(ivShow);
            ivShow.setImageURI(cropUri);
        }
    }


    private Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    public void deleteImage(View v) {
        PictureUtil.deleteFile(PictureUtil.getMyPetRootDirectory());
    }

}
