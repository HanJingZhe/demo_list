package com.qtgm.peng.t0805_save.model;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")
public class DataModel {

    private static final int COUNT = 50;
    private static final long DELAY = 500;

    public static List<String> initStringData() {
        return initStringData(COUNT);
    }

    public static List<String> initStringData(int sum) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < sum; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    public static void initStringDataByDelay(Callback callback) {
        initStringDataByDelay(DELAY, callback, COUNT);
    }

    public static void initStringDataByDelay(Callback callback, int count) {
        initStringDataByDelay(DELAY, callback, count);
    }

    public static void initStringDataByDelay(long delay, Callback callback, int count) {
        Observable.timer(delay, TimeUnit.MICROSECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(l -> callback.dataSuccess(initStringData(count)));
    }


    public interface Callback {
        void dataSuccess(List<String> data);
    }

}


