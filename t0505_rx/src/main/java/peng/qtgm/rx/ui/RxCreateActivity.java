package peng.qtgm.rx.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import peng.qtgm.rx.R;

import static peng.qtgm.rx.ui.MainActivity.WP;

@SuppressLint("CheckResult")
/**
 * 关于创建Rx的操作符
 */
public class RxCreateActivity extends AppCompatActivity {

    @BindView(R.id.create_btn_7)
    Button btnInterval;
    @BindView(R.id.create_btn_8)
    Button btnIntervalRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_create);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.create_btn_1, R.id.create_btn_3, R.id.create_btn_4, R.id.create_btn_5, R.id.create_btn_6, R.id.create_btn_7, R.id.create_btn_8, R.id.create_btn_9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.create_btn_1:
                createRx();
                break;
            case R.id.create_btn_3:
                just();
                break;
            case R.id.create_btn_4:
                fromIterable();
                break;
            case R.id.create_btn_5:
                timer();
                break;
            case R.id.create_btn_6:
                fromArray();
                break;
            case R.id.create_btn_7:
                interval();
                break;
            case R.id.create_btn_8:
                intervalRange();
                break;
            case R.id.create_btn_9:
                range();
                break;
        }
    }

    /**
     *  range()  操作符
     *  作用发送指定范围的序列，可指定范围.作用类似intervalRange，但不同的是range是无延迟发送
     */
    private void range() {
        Observable
                .range(2, 6)
                .subscribe(integer -> {
                    Log.e(WP, String.valueOf(integer));//从2开始输出  包含起始
                });
    }

    /**
     * intervalRange() 操作符
     * 作用和interval相同，但可以指定发送数据的数量
     */
    private void intervalRange() {
        /**
         *  参数1： 起始发送值
         *  参数2：发送数量
         *  参数3：首次发送延迟事件
         *  参数4：每次发送事件间隔
         *  参数5：时间单位
         *
         */
        Observable
                .intervalRange(1, 3, 1, 1, TimeUnit.SECONDS,Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> btnIntervalRange.setText("intervalRange() 操作符"+aLong));

    }

    /**
     * interval() 定时器
     * 这个相当于定时器，用它可以取代CountDownTimer。它会按照设定的间隔时间，每次发送一个事件，发送的事件序列：默认从0开始，无限递增的整数序列
     * *
     * * 以下代码输出：   0 ----(5秒后)-----1-----(5秒后)------2---------(5秒后)--------3-------(5秒后)-----.......
     */
    private void interval() {
        Observable
                .interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(a -> btnInterval.setText("interval() 定时器" + a));
    }

    /**
     * fromArray() 操作符
     * 对一个数组集合进行观察，把数组一次性发给观察者，只会执行一次观察者的onNext，最后默认执行onComplete方法
     */
    private void fromArray() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        String[] ss = {"a", "b", "c"};

        //数组会遍历
        Observable.fromArray(ss).subscribe(s -> Log.e(WP, s));
        //集合一次返回
        Observable.fromArray(list).subscribe(strings -> Log.e(WP, strings.toString()));
    }

    /**
     * timer()操作符
     * 延迟指定时间发送一个0数值(Long类型)
     * * timer操作符主要运行在一个新线程中，也可以自定义线程调度器(第三个参数)
     */
    private void timer() {
        Log.e(WP, "开始执行");
        Observable
                .timer(3, TimeUnit.SECONDS)
                .subscribe(aLong -> Log.e(WP, "执行完毕" + aLong));
    }

    /**
     * fromIterable()操作符
     * 此操作符的作用是将传入的数组集合按脚标依次发送出去
     * *
     * * 以下代码会依次把 0-9的字符串发送出去。执行10此观察者的onNext方法，最后默认执行onComplete方法
     */
    private void fromIterable() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(String.valueOf(i));
        }
        Observable
                .fromIterable(list)
                .subscribe(s -> Log.e(WP, s));
    }

    /**
     * just() 操作符
     * 此操作符的作用是将传入的数据依次发送出去.最多可以传10个参数
     * *
     * * 以下代码会依次把 1-10的字符串发送出去。执行10此观察者的onNext方法，最后默认执行onComplete方法
     */
    private void just() {
        Observable
                .just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
                .subscribe(s -> Log.e("王鹏", s));

    }

    /**
     * Create() 操作符
     * Observable 被观察者
     * *
     * *  ObservableOnSubscribe  观察者与被观察者的桥接(事件发射器)
     * *
     * *  ObServer 观察者
     * *
     * *   被观察者  -->  观察者与被观察者的桥接  --> 观察者
     * *
     * *   被观察者.create(观察者与被观察者的桥接).subscribe(观察者)
     */
    private void createRx() {
        Observable
                .create((ObservableOnSubscribe<String>) emitter -> {
                    for (int i = 0; i < 5; i++) {
                        emitter.onNext(String.valueOf(i));
                        Log.e("王鹏thread", "线程:" + Thread.currentThread().getName());
                    }
                    emitter.onComplete();
                })
                .subscribeOn(Schedulers.io()) //在子线程中悄悄订阅
                .observeOn(AndroidSchedulers.mainThread()) // 观察到的结果在主线程
                .subscribe(s -> Log.e("王鹏", s + Thread.currentThread().getName()));
    }
}
