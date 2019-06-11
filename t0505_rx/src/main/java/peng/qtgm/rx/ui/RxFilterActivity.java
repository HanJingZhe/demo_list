package peng.qtgm.rx.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import peng.qtgm.rx.R;

import static peng.qtgm.rx.ui.MainActivity.WP;

@SuppressLint("CheckResult")
/**
 *关于Rx 过滤操作符
 */
public class RxFilterActivity extends AppCompatActivity {

    @BindView(R.id.filter_btn_1)
    Button filterBtn1;
    @BindView(R.id.filter_btn_2)
    Button filterBtn2;
    @BindView(R.id.filter_btn_3)
    Button filterBtn3;
    @BindView(R.id.filter_btn_4)
    Button filterBtn4;
    @BindView(R.id.filter_btn_5)
    Button filterBtn5;
    @BindView(R.id.filter_btn_6)
    Button filterBtn6;
    @BindView(R.id.filter_btn_7)
    Button filterBtn7;
    @BindView(R.id.filter_btn_8)
    Button filterBtn8;
    @BindView(R.id.filter_btn_9)
    Button filterBtn9;
    @BindView(R.id.filter_btn_10)
    Button filterBtn10;
    @BindView(R.id.filter_btn_11)
    Button filterBtn11;
    @BindView(R.id.filter_btn_12)
    Button filterBtn12;
    @BindView(R.id.filter_btn_13)
    Button filterBtn13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_filter);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.filter_btn_1, R.id.filter_btn_2, R.id.filter_btn_3, R.id.filter_btn_4, R.id.filter_btn_5, R.id.filter_btn_6, R.id.filter_btn_7, R.id.filter_btn_8, R.id.filter_btn_9, R.id.filter_btn_10, R.id.filter_btn_11, R.id.filter_btn_12, R.id.filter_btn_13})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.filter_btn_1:
                filter();
                break;
            case R.id.filter_btn_2:
                distinct();
                break;
            case R.id.filter_btn_3:
                distinctUntilChanged();
                break;
            case R.id.filter_btn_4:
                skip();
                break;
            case R.id.filter_btn_5:
                take();
                break;
            case R.id.filter_btn_6:
                takeLast();
                break;
            case R.id.filter_btn_7:
                elementAt();
                break;
            case R.id.filter_btn_8:
                elementAtOrError();
                break;
            case R.id.filter_btn_9:
                ignoreElements();
                break;
            case R.id.filter_btn_10:
                ofType();
                break;
            case R.id.filter_btn_11:
                throttleFirst();
                break;
            case R.id.filter_btn_12:
                sample();
                break;
            case R.id.filter_btn_13:
                firstElement();
                break;
        }
    }

    /**
     * ========================firstElement()/lastElement()==========================
     *
     * 选取第一个元素/最后一个元素
     */
    public static void firstElement() {
        Observable.just(1, 2, 3)
                .firstElement()
                .subscribe(integer -> Log.e(WP + "firstElement", String.valueOf(integer)));
    }

    /**
     * ===================sample()=================================
     *
     * 在某段时间内，只发送该段时间内最新(最后)1次事件
     * 与throttleLast类似
     */
    public static void sample() {
        Observable.interval(300, TimeUnit.MILLISECONDS) //每个0.3秒发送一个事件
                .sample(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e(WP + "sample", String.valueOf(aLong));
                    }
                });
    }

    /**
     * ========================throttleFirst()/throttleLast() 操作符 ======================================
     * <p>
     * throttleFirst() 在某段时间内，只发送该段事件第一次事件
     * throttleLast()  在某段时间内，只发送该段事件最后一次事件
     */
    public static void throttleFirst() {
        Observable.interval(300, TimeUnit.MILLISECONDS) //每个0.3秒发送一个事件
                .throttleFirst(1, TimeUnit.SECONDS) //只接收每秒内发送的第一个数据
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e(WP + "throttleFirst", String.valueOf(aLong));
                    }
                });
    }

    /**
     * ========================ofType() 操作符 ======================================
     * <p>
     * 通过数据的类型过滤数据，只发送指定类型数据。
     * 以下代码观察者只接收到： 1,2
     */
    public static void ofType() {
        Observable.just("哈哈", 1, "嘻嘻", 2, 3.5)
                .ofType(Integer.class)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(WP + "ofType", String.valueOf(integer));
                    }
                });
    }

    /**
     * ========================ignoreElements() 操作符 ======================================
     * 不管发射的数据.只希望在它完成时和遇到错误时收到通知
     */
    public static void ignoreElements() {
        Observable
                .range(0, 10)
                .ignoreElements()
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(WP + "ignoreEles", "完成了");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(WP + "ignoreEles", "出错了");
                    }
                });
    }

    /**
     * ==============elementAtOrError()===================================================
     * <p>
     * 在elementAtError()的基础上，当出现越界情况(当获取位置的索引>事件序列的长度)，即抛出异常
     */
    public static void elementAtOrError() {
        Observable.range(1, 5)
                .elementAtOrError(4)
                .subscribe(integer -> Log.e(WP + "elementAtOrErr", String.valueOf(integer)));
    }

    /**
     * ========================elementAt() 操作符 ======================================
     * <p>
     * 只发射第n项数据.
     * 一个参数和两个参数时：
     * elementAt(第n项)
     * elementAt(第n项，第N项不存在时默认值)
     * <p>
     * n为负数时,报IndexOUtOfBoundExection。为正数但超过发射数据长度不会报异常会使用默认值代替
     */
    public static void elementAt() {
        Observable.range(1, 5)
                .elementAt(3, 10)
                .subscribe(integer -> Log.e(WP + "elementAt", String.valueOf(integer)));
    }

    /**
     * tekeLast() 操作符
     * 只能接收被观察者发送的最后几个事件
     */
    private void takeLast() {
        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .takeLast(3)
                .subscribe(i -> Log.e(WP, i + ""));
    }

    /**
     * ====================== take 操作符===========================
     * 只能接收两个事件
     */
    public static void take() {
        Observable
                .just(1, 2, 3, 4, 5, 6, 7)
                .take(2)
                .subscribe(i -> Log.e(WP, i + ""));
    }

    /**
     * ========================skip，skipLast======================================
     * <p>
     * skip 操作符是把Observable发射的数据过滤点掉前n项。而take操作符只能接收前n项。
     * 另外还有skipLast和takeLast则是从后往前进行过滤.接收完会调用onComplete()方法
     */
    public static void skip() {
        Observable
                .just(1, 2, 3, 4, 5, 6, 7)
                .skip(2)  //过滤前2项
                .skipLast(3) //过滤后3项
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(WP + "skip", "根据顺序过滤" + String.valueOf(integer));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e(WP + "skip", "onComplete");
                    }
                });
//-------------------------------------------------------------------------------------------------
        Observable.intervalRange(1, 5, 0, 1, TimeUnit.SECONDS)
                .skip(1, TimeUnit.SECONDS)   //过滤地1s发送的数据
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.e(WP + "skip", "根据事件过滤" + String.valueOf(aLong));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e(WP + "skip", "onComplete");
                    }
                });


    }

    /**
     * ========================distinctUntilChanged 操作符 ======================================
     * <p>
     * 过滤掉连续重复的事件
     */
    public static void distinctUntilChanged() {
        Observable.just(1, 2, 3, 1, 2, 3, 3, 4, 4)
                .distinctUntilChanged()
                .subscribe(i -> Log.e(WP, i + ""));
    }

    /**
     * ========================distinct 操作符 ======================================
     * <p>
     * 简单地说就是去重。发射的数据包含重复的，会将重复的筛选掉。也就是，它只允许还没有被发射过的数据通过，被观察者接收。发射完数据会自动调用onComplete()方法
     * y以下代码：观察者只会接收到 ： 1,2,3,5 四个数值
     */
    public static void distinct() {
        Observable
                .just(1, 2, 3, 2, 3, 5)
                .distinct()
                .subscribe(i -> Log.e(WP, "" + i));
    }

    /**
     * ========================filter() 操作符 ======================================
     * <p>
     * 对被观察者发送的事件做过滤操作。只有符合筛选条件的事件才会被观察者所接收。
     * return true : 继续发送
     * return false ： 不发送
     */
    public static void filter() {
        Observable
                .create((ObservableOnSubscribe<Integer>) emitter -> {
                    for (int i = 0; i < 5; i++) {
                        emitter.onNext(i);
                    }
                })
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        //return true : 继续发送
                        //return false ： 不发送
                        return integer != 2; // 本例子过滤了 等于2的情况
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(WP + "filter", String.valueOf(integer));
                    }
                });
    }

}
