package com.qtgm.peng.t0819_enum.constant;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author peng_wang
 * @date 2019/8/19
 */
public class FileType {

    public static final int TYPE_MUSIC = 0;
    public static final int TYPE_PHOTO = 1;
    public static final int TYPE_TEXT = 2;

    public final int fileType;
    //Retention 是元注解，简单地讲就是系统提供的，用于定义注解的“注解”
    @Retention(RetentionPolicy.SOURCE)
    //这里指定int的取值只能是以下范围
    @IntDef({TYPE_MUSIC, TYPE_PHOTO, TYPE_TEXT})
    @interface FileTypeDef {
    }
    public FileType(@FileTypeDef int fileType) {
        this.fileType = fileType;
    }
}
