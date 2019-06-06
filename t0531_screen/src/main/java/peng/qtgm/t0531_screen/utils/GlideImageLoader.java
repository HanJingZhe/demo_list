package peng.qtgm.t0531_screen.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import peng.qtgm.t0531_screen.R;

/**
 * @author peng_wang
 * @date 2018/12/18
 */
public class GlideImageLoader {

    private static RequestOptions options = new RequestOptions()
            .placeholder(R.mipmap.naruto112)
            .error(R.mipmap.naruto112);

    public static void loadImg(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url)
                .apply(options.centerCrop())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }


    public static void mTestNone(Context context, String url, ImageView iv) {
        Glide.with(context).load(url)
                .apply(options)
                .into(iv);
    }

    public static void mTestcenterCrop(Context context, String url, ImageView iv) {
        Glide.with(context).load(url)
                .apply(options)
                .centerCrop()
                .into(iv);
    }

    public static void mTestcenterInside(Context context, String url, ImageView iv) {
        Glide.with(context).load(url)
                .apply(options)
                .centerInside()
                .into(iv);
    }

    public static void mTestfitCenter(Context context, String url, ImageView iv) {
        Glide.with(context).load(url)
                .apply(options)
                .fitCenter()
                .into(iv);
    }

    public static void mTestcircleCrop(Context context, String url, ImageView iv) {
        Glide.with(context).load(url)
                .apply(options)
                .circleCrop()
                .into(iv);
    }

    public static void mTestRound(Context context, String url, ImageView imageView, int radius) {
        Glide.with(context).load(url)
                .apply(options.bitmapTransform(new RoundedCorners(radius)))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }


    public static void loadImgCenterInside(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url)
                .apply(options.centerInside())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    public static void loadImgNona(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    public static void loadImgFitCenter(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(new RequestOptions().fitCenter())
                .into(imageView);
    }

    public static void loadRoundImg(Context context, String url, ImageView imageView, int radius) {
        Glide.with(context).load(url)
                .apply(options.bitmapTransform(new RoundedCorners(radius)))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    public static void loadImgFile(Context context, File file, ImageView imageView) {
        Glide.with(context).load(file)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    public static void loadImgBit(Context context, Bitmap bitmap, ImageView imageView, int radius) {
        Glide.with(context).load(bitmap)
                .apply(options.bitmapTransform(new RoundedCorners(radius)))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }



}
