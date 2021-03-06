package peng.qtgm.video.compress;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.qiniu.pili.droid.shortvideo.PLShortVideoTranscoder;
import com.qiniu.pili.droid.shortvideo.PLVideoSaveListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static com.qiniu.pili.droid.shortvideo.PLErrorCode.ERROR_LOW_MEMORY;
import static com.qiniu.pili.droid.shortvideo.PLErrorCode.ERROR_NO_VIDEO_TRACK;
import static com.qiniu.pili.droid.shortvideo.PLErrorCode.ERROR_SRC_DST_SAME_FILE_PATH;
import static com.qiniu.pili.droid.shortvideo.PLErrorCode.ERROR_UNAUTHORIZED;

public class VideoUtil {

    private static final String TAG = VideoUtil.class.getSimpleName();

    private MediaMetadataRetriever mMetadataRetriever;
    private long fileLength = 0;//毫秒

    public VideoUtil(String path) {
        if (TextUtils.isEmpty(path)) {
//            throw new RuntimeException("path must be not null !");
            Log.e(TAG, "路径为空");
            return;
        }


        if (path.startsWith("http")) {
            mMetadataRetriever = new MediaMetadataRetriever();
            mMetadataRetriever.setDataSource(path, new HashMap());
        } else {
            File file = new File(path);
            if (!file.exists()) {
//                throw new RuntimeException("path file   not exists !");
                Log.e(TAG, "视频文件不存在");
                return;
            }
            mMetadataRetriever = new MediaMetadataRetriever();
            mMetadataRetriever.setDataSource(path);
        }
        String len = getVideoLength();
        fileLength = TextUtils.isEmpty(len) ? 0 : Long.valueOf(len);
    }

    public int getVideoWidth() {
        String w = mMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
        int width = -1;
        if (!TextUtils.isEmpty(w)) {
            width = Integer.valueOf(w);
        }
        return width;
    }

    public int getVideoHeight() {
        String h = mMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);
        int height = -1;
        if (!TextUtils.isEmpty(h)) {
            height = Integer.valueOf(h);
        }
        return height;
    }

    /**
     * 获取视频的典型的一帧图片，不耗时
     */
    public Bitmap extractFrame() {
        return mMetadataRetriever.getFrameAtTime();
    }

    /**
     * 获取视频某一帧,不一定是关键帧
     */
    public Bitmap extractFrame(long timeMs) {
        //第一个参数是传入时间，只能是us(微秒)
        //OPTION_CLOSEST ,在给定的时间，检索最近一个帧,这个帧不一定是关键帧。
        //OPTION_CLOSEST_SYNC   在给定的时间，检索最近一个同步与数据源相关联的的帧（关键帧）
        //OPTION_NEXT_SYNC 在给定时间之后检索一个同步与数据源相关联的关键帧。
        //OPTION_PREVIOUS_SYNC  顾名思义，同上
//        Bitmap bitmap = mMetadataRetriever.getFrameAtTime(timeMs * 1000, MediaMetadataRetriever.OPTION_CLOSEST);
        Bitmap bitmap = null;
        for (long i = timeMs; i < fileLength; i += 1000) {
            bitmap = mMetadataRetriever.getFrameAtTime(i * 1000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
            if (bitmap != null) {
                break;
            }
        }
        return bitmap;
    }


    /***
     * 获取视频的长度时间
     */
    public String getVideoLength() {
        return mMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
    }

    /**
     * 获取视频旋转角度
     *
     * @return
     */
    public int getVideoDegree() {
        int degree = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            String degreeStr = mMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION);
            if (!TextUtils.isEmpty(degreeStr)) {
                degree = Integer.valueOf(degreeStr);
            }
        }
        return degree;
    }

    public void release() {
        if (mMetadataRetriever != null) {
            mMetadataRetriever.release();
        }
    }


    public static File create(Context activity) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "VIDEO_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_MOVIES);
        File videoFile = null;
        try {
            videoFile = File.createTempFile(imageFileName, ".mp4", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videoFile;
    }

    /*************************************************************************/

    /**
     * 压缩视频
     */
    public static PLShortVideoTranscoder compressVideoResouce(Context mContext, String filepath, final CompressListener compressListener) {

        //PLShortVideoTranscoder初始化，参数:第一个context，第二个要压缩文件的路径，第三个视频压缩后输出的路径
        PLShortVideoTranscoder mShortVideoTranscoder = new PLShortVideoTranscoder(mContext, filepath, create(mContext).getPath());
        MediaMetadataRetriever retr = new MediaMetadataRetriever();
        retr.setDataSource(filepath);
        String height = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT); // 视频高度
        String width = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH); // 视频宽度
        String rotation = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION); // 视频旋转方向
        int bitrateLevel = 6;//我这里选择的2500*1000压缩，这里可以自己选择合适的压缩比例


        //开始转码   Program type already present: org.aspectj.internal.lang.annotation.ajcDeclareAnnotation
        mShortVideoTranscoder.transcode(Integer.parseInt(width), Integer.parseInt(height), getEncodingBitrateLevel(bitrateLevel), false, new PLVideoSaveListener() {
            @Override
            public void onSaveVideoSuccess(String s) {
                Log.d("王鹏", "onSaveVideoSuccess: 压缩成功:"+s);
                compressListener.compressSuccess(s);
            }

            @Override
            public void onSaveVideoFailed(final int errorCode) {
                Log.d("王鹏", "onSaveVideoFailed: 压缩错误码:"+errorCode);
            }

            @Override
            public void onSaveVideoCanceled() {
                Log.d("王鹏", "压缩取消了");
            }

            @Override
            public void onProgressUpdate(float percentage) {
                Log.d("王鹏", "onProgressUpdate: 压缩中:" + percentage);
                compressListener.compressProgress(percentage);
            }
        });
        return mShortVideoTranscoder;
    }

    /**
     * 设置压缩质量
     */
    private static int getEncodingBitrateLevel(int position) {
        return ENCODING_BITRATE_LEVEL_ARRAY[position];
    }

    /**
     * 选的越高文件质量越大，质量越好
     */
    public static final int[] ENCODING_BITRATE_LEVEL_ARRAY = {
            500 * 1000,
            800 * 1000,
            1000 * 1000,
            1200 * 1000,
            1600 * 1000,
            2000 * 1000,
            2500 * 1000,
            4000 * 1000,
            8000 * 1000,
    };

    public interface CompressListener {
        void compressSuccess(String path);
        void compressProgress(float progress);
        void compressFailure(String msg);
    }

}
