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
import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import peng.qtgm.rx.R;

import static peng.qtgm.rx.ui.MainActivity.WP;

@SuppressLint("CheckResult")
/**
 *关于Rx 功能操作符
 */
public class FunctionActivity extends AppCompatActivity {

    @BindView(R.id.func_btn_1)
    Button funcBtn1;
    @BindView(R.id.func_btn_2)
    Button funcBtn2;
    @BindView(R.id.func_btn_3)
    Button funcBtn3;
    @BindView(R.id.func_btn_4)
    Button funcBtn4;
    @BindView(R.id.func_btn_5)
    Button funcBtn5;
    @BindView(R.id.func_btn_6)
    Button funcBtn6;
    @BindView(R.id.func_btn_7)
    Button funcBtn7;
    @BindView(R.id.func_btn_8)
    Button funcBtn8;
    @BindView(R.id.func_btn_9)
    Button funcBtn9;
    @BindView(R.id.func_btn_10)
    Button funcBtn10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.func_btn_1, R.id.func_btn_2, R.id.func_btn_3, R.id.func_btn_4, R.id.func_btn_5, R.id.func_btn_6, R.id.func_btn_7, R.id.func_btn_8, R.id.func_btn_9, R.id.func_btn_10})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.func_btn_1:
                delay();
                break;
            case R.id.func_btn_2:
                dos();
                break;
            case R.id.func_btn_3:
                break;
            case R.id.func_btn_4:
                break;
            case R.id.func_btn_5:
                break;
            case R.id.func_btn_6:
                break;
            case R.id.func_btn_7:
                break;
            case R.id.func_btn_8:
                break;
            case R.id.func_btn_9:
                break;
            case R.id.func_btn_10:
                break;
        }
    }

    /**
     * ========================do 系列操作符 =======================================
     * <p>
     * 在事件发送&接收的整个周期过程中进行操作。
     * 如发送事件前的操作，发送事件后的回调请求
     * do系列操作符包含以下：
     * <p>
     * doOnEach() :当Observable每发送一次事件就会调用一次(包含onNext()，onError()，onComplete())
     * doOnNext(): 执行 onNext()前调用
     * doAfterNext()： 执行onNext()后调用
     * doOnComplete()：执行onComplete()前调用
     * doOnError():执行 onError()前调用
     * doOnTerminate(): 执行终止(无论正常发送完毕/异常终止)
     * doFinally(): 最后执行
     * doOnSubscribe() ：观察者订阅是调用
     * doOnUnScbscribe()： 观察者取消订阅时调用
     */
    public static void dos() {
        Observable
                .create((ObservableOnSubscribe<Integer>) emitter -> {
                    emitter.onNext(1);
                    emitter.onNext(2);
                    emitter.onNext(3);
                    emitter.onError(new NullPointerException());
                })
                .doOnEach(notification -> Log.e(WP,"doOnEach"+notification.getValue()))
                .doOnNext(i -> Log.e(WP,"doOnNext:"+i))
                .doAfterNext(i -> Log.e(WP,"doAfterNext:"+i))
                .doOnComplete(() -> Log.e(WP,"doOnComplete"))
                .doOnError(e -> Log.e(WP,"doOnError:"+e.getMessage()))
                .doOnTerminate(() -> Log.e(WP,"doOnTerminate"))
                .doAfterTerminate(() -> Log.e(WP,"doAfterTerminate"))
                .doOnSubscribe(disposable -> Log.e(WP,"doOnSubScribe"))
                .doFinally(() -> Log.e(WP,"doFinally"))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(WP, "onSubscribeonSubscribeonSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(WP, "收到的数据收到的数据收到的数据：  " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(WP, "onErroronErroronError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e(WP, "onCompleteonCompleteonComplete");
                    }
                });

    }


    /**
     * ==================delay 操作符=======================================
     * <p>
     * 延迟发送事件
     * delay有多个重载方法：
     * delay(long delay,TimeUnit unit) :指定延迟时间。 参数一：时间 ； 参数二：时间单位
     * delay(long delay, TimeUnit unit, Scheduler scheduler)参数一：时间 ； 参数二：时间单位；参数三： 线程调度器
     * delay(long delay, TimeUnit unit, boolean delayError) 参数一：时间 ； 参数二：时间单位；参数三： 是否错误延迟
     * delay(long delay, TimeUnit unit, Scheduler scheduler, boolean delayError)
     * 参数一：时间 ； 参数二：时间单位；参数三： 线程调度器; 参数四：是否错误延迟(若中间发生错误，是否如常执行，执行完在执行onError())
     */
    public void delay() {
        Observable
                .just(1, 2)
                .delay(2, TimeUnit.SECONDS)  //延时完成 全部发送
                .subscribe(i -> Log.e(WP, "delay: " + i));
    }

}
