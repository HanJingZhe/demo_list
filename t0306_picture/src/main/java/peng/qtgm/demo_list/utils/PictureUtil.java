package peng.qtgm.demo_list.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import peng.qtgm.demo_list.R;

/**
 * @author peng_wang
 * @date 2019/3/9
 * @DES: 调用图片选择和裁剪功能, 需要传入Activity
 */
public class PictureUtil {

    private static final String MyQLWDirectory = Environment.getExternalStorageDirectory() + File.separator + "test_file";

    public static String getMyPetRootDirectory() {
        return MyQLWDirectory;
    }

    public static boolean mkdirMyPetRootDirectory() {
        boolean isSdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);// 判断sdcard是否存在
        boolean b = false;
        if (isSdCardExist) {
            File MyPetRoot = new File(getMyPetRootDirectory());
            if (MyPetRoot.exists()) {
                b = true;
            } else {
                Log.d("王鹏", "mkdirMyPetRootDirectory: 开始创建");
                b = MyPetRoot.mkdir();
            }
        }
        return b;
    }

    /**
     * 调用知乎UI选择图片
     */
    public static void openPictures(Activity activity, int requestCode, int selectNum) {
        Matisse.from(activity)
                //选择视频和图片
                .choose(MimeType.ofAll())
                //是否只显示选择的类型的缩略图，就不会把所有图片视频都放在一起，而是需要什么展示什么
                .showSingleMediaType(true)
                //这两行要连用 是否在选择图片中展示照相 和适配安卓7.0 FileProvider
                .capture(true)
                .captureStrategy(new CaptureStrategy(true, "peng.qtgm.demo_list.ImageFlieProvider"))
                //有序选择图片 123456...
                .countable(true)
                //最大选择数量为9
                .maxSelectable(selectNum)
                //选择方向
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                //界面中缩略图的质量
                .thumbnailScale(0.8f)
                //蓝色主题
                .theme(R.style.matisse_ui)
                //Glide加载方式
                .imageEngine(new GlideEngine())
                //请求码
                .forResult(requestCode);
    }

    /**
     * 调用系统UI选择图片
     */
    public static void openAlbum(Activity activity, int requestCode) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT"); //文件管理UI
        //Intent intent = new Intent(Intent.ACTION_PICK); //系统相册UI
        intent.setType("MUSIC/*");
        activity.startActivityForResult(intent, requestCode);//打开系统相册
    }

    /**
     * 调用系统UI进行裁剪(方形裁剪)
     */
    public static void cropImage(Activity activity, Uri uri, int requestCode) {
        if (!mkdirMyPetRootDirectory()) {
            Log.d("王鹏", "创建文件夹失败!");
            return;
        }
        File cropFile = new File(MyQLWDirectory, "crop.jpg");
        if (cropFile.exists()) {
            cropFile.delete();
            Log.i("王鹏", "delete");
        }
        Uri cropUri = Uri.fromFile(cropFile);
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                activity.grantUriPermission("com.android.camera", uri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            //适配华为手机默认圆形
            if (Build.MANUFACTURER.equals("HUAWEI")) {
                intent.putExtra("aspectX", 9998);
                intent.putExtra("aspectY", 9999);
            } else {
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
            }
            intent.setDataAndType(uri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("outputX", 300);
            intent.putExtra("outputY", 300);
            intent.putExtra("return-data", false);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
            intent.putExtra("noFaceDetection", false);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            activity.startActivityForResult(intent, requestCode);
        } catch (ActivityNotFoundException anfe) {
            Log.d("图片裁剪", "你的设备不支持裁剪行为!");
        }

    }

    private static File getOutputMediaFile(Activity activity) {
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + activity.getApplicationContext().getPackageName()
                + "/Files");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        File mediaFile;
        String mImageName = "avatar.png";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    public static File create(Activity activity) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = null;
        try {
            //imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);
            imageFile = File.createTempFile("crop_head", ".jpg", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageFile;
    }


    /**
     * 删除文件
     */
    public static boolean deleteFile(String delFile) {
        File file = new File(delFile);
        if (!file.exists()) {
            return false;
        } else {
            if (file.isFile())
                return deleteSingleFile(delFile);
            else
                return deleteDirectory(delFile);
        }
    }

    /**
     * 删除单个文件
     */
    private static boolean deleteSingleFile(String filePath$Name) {
        File file = new File(filePath$Name);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     */
    private static boolean deleteDirectory(String filePath) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!filePath.endsWith(File.separator))
            filePath = filePath + File.separator;
        File dirFile = new File(filePath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (File file : files) {
            // 删除子文件
            if (file.isFile()) {
                flag = deleteSingleFile(file.getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (file.isDirectory()) {
                flag = deleteDirectory(file
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

}
