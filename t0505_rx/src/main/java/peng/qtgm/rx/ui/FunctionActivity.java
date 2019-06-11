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
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Function;
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
                onErrorReturn();
                break;
            case R.id.func_btn_4:
                onExceptionResumeNext();
                break;
            case R.id.func_btn_5:
                retry();
                break;
            case R.id.func_btn_6:
                retryUntil();
                break;
            case R.id.func_btn_7:
                retryWhen();
                break;
            case R.id.func_btn_8:
                repeat();
                break;
            case R.id.func_btn_9:
                repeatWhen();
                break;
            case R.id.func_btn_10:
                debounce();
                break;
        }
    }

    /**
     * ===============debounce() 操作符==============
     * <p>
     * 一定的时间内没有操作就会发送事件（只会发送最后一次操作的事件）。
     * <p>
     * 以下的例子： 发送5个事件，每个事件间隔1秒。但是debounce限定了2秒内没有任何操作才会真正发送事件。所以只有最后一次满足条件，只能接收到事件 5
     */
    public static void debounce() {
        Observable.intervalRange(1, 5, 0, 1, TimeUnit.SECONDS)
                .debounce(2, TimeUnit.SECONDS)
                .subscribe(aLong -> Log.e(WP + "debounce", String.valueOf(aLong)));
    }


    /**
     * ===============repeatWhen() 操作符==============
     * <p>
     * 将原始 Observable 停止发送事件的标识（Complete（） / Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable）
     * ，以此决定是否重新订阅 & 发送原来的 Observable
     */
    public static void repeatWhen() {

        Observable
                .just(1, 2, 4)
                .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                        return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Object o) throws Exception {

                                Log.e(WP, o.toString() + "你好");
                                //若新被观察者（Observable）返回1个Complete()/  Error()事件，则不重新订阅 & 发送原来的 Observable
                                //Observable.empty() = 发送Complete事件，但不会回调观察者的onComplete（）
                                return Observable.empty();

                                // return Observable.error(new Throwable("不再重新订阅事件"));
                                // 返回Error事件 = 回调onError（）事件，并接收传过去的错误信息。

                                // 情况2：若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                                // return Observable.just(1);
                                // 仅仅是作为1个触发重新订阅被观察者的通知，发送的是什么数据并不重要，只要不是Complete（） /  Error（）事件
                            }
                        });
                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(WP, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(WP, "onNext:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(WP, "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e(WP, "onComplete");
            }
        });
    }

    /**
     * repeat() 操作符
     * repeat操作符的作用是重复发射 observable的数据序列，可以使无限次也可以是指定次数.不传时为重复无限次
     */
    private void repeat() {
        Observable
                .just(1, 2, 3)
                .repeat(3)
                .subscribe(i -> Log.e(WP, i + ""));
    }

    /**
     * ===================retryWhen() 操作符============================
     * <p>
     * 遇到错误时，将发生的错误传递给一个新的被观察者(Observable)，
     * 并决定是否需要重新订阅原始被观察者(Observable) &  发送事件
     */
    public static void retryWhen() {
        Observable
                .create((ObservableOnSubscribe<Integer>) emitter -> {
                    emitter.onNext(1);
                    emitter.onNext(2);
                    emitter.onError(new Throwable("发送了错误"));
                    emitter.onNext(3);
                })
                //遇到Error时会回调
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Throwable throwable) throws Exception {
                                //1、若返回的Observable发送的事件 = Error ，则原始的Observable则不重新发送事件。该异常信息可在观察者的onError中获得
                                //return Observable.error(throwable);
                                //2、若返回的Observable发送的事件= Next事件(和next的内容无关)，则原始的Observable重新发送事件(若持续遇到错误，则持续发送)
                                return Observable.just(5); //仅仅是作为一个触发重新订阅原被观察者的通知，什么数据并不重要，只有不是onComplete/onError事件
                            }
                        });
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(WP + "retryWhen", String.valueOf(integer));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(WP + "retryWhen", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(WP + "retryWhen", "onComplete");
                    }
                });
    }

    /**
     * ===================retryUntil() 操作符============================
     * <p>
     * 发送事件遇到错误，指定规则是否重新发送。retry(Predicate predicate)。
     * return true ： 不重新发送请求，并且调用观察者的onError()方法结束
     * return false ： 重新发送数据(若持续遇到错误，就持续重新发送)
     */
    public static void retryUntil() {

        Observable
                .create((ObservableOnSubscribe<Integer>) emitter -> {
                    emitter.onNext(1);
                    emitter.onNext(2);
                    emitter.onError(new Throwable("发生错误了"));
                    emitter.onNext(3);
                })
                .retryUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() throws Exception {
                        //return true ： 不重新发送请求，并且调用观察者的onError()方法结束
                        // return false ： 重新发送数据(若持续遇到错误，就持续重新发送)
                        return false;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(WP + "retryUntil", String.valueOf(integer));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e(WP + "retryUntil", "onComplete");
                    }
                });
    }

    /**
     * ====================retry() 操作符 ======================
     * <p>
     * 作用是：出现错误时，让被观察者重新发送数据
     * 注：若发送错误，则一直重新发送
     * <p>
     * 有几个重载方法：
     * retry() ： 出现错误时，让被观察者重新发送数据。若错误一直发生，则一直重新发送
     * retry(long time)：与retry不同的书，若错误一直发生，被观察者则一直重新发送数据，但这持续重新发送有次数限制
     * retry(Predicate predicate) ：  出现错误时，根据指定逻辑(可以捕获到发生的错误)决定是否让被观察者重新发送数据
     * retry(new BiPredicate<Integer, Throwable>)：出现错误时，根据指定逻辑(可以捕获重发的次数和发生的错误)决定是否让被观察者重新发送数据
     * retry（long time,Predicate predicate） ： 出现错误时，根据指定逻辑(可以捕获到发生的错误)决定是否让被观察者重新发送数据。并且有持续重发的次数限制
     */
    public static void retry() {
        Observable
                .create((ObservableOnSubscribe<Integer>) emitter -> {
                    emitter.onNext(1);
                    emitter.onNext(2);
                    emitter.onError(new Throwable("发生错误了"));
                    emitter.onNext(3);
                })
                .retry(new BiPredicate<Integer, Throwable>() {
                    @Override
                    public boolean test(Integer integer, Throwable throwable) throws Exception {
                        // interger 为重试次数 ，throwable 为捕获到的异常
                        Log.e(WP + "retry", throwable.getMessage());
                        Log.e(WP + "integer", "重试次数： " + integer);
                        //return true ： 重新发送请求(若持续遇到错误，就持续重新发送)
                        //return false ：    不重新发送数据 并且调用观察者的onError()方法结束
                        if (integer > 2)
                            return false;
                        return true;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(WP + "retry", String.valueOf(integer));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(WP, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e(WP, "onComplete");
                    }
                });
    }

    /**
     * ====================onExceptionResumeNext()/onErrorResumeNext() 操作符 ======================
     * <p>
     * 遇到错误时发送一个新的Observable 。并且正常终止.注意原Observable后面的事件不会再发送
     * 如果捕获Exception的话使用onExceptionResumeNext() ，捕获错误的用onErrorResumeNext()
     */
    public static void onExceptionResumeNext() {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onError(new NullPointerException("NullPointerException"));
            emitter.onNext(3);
        }).onExceptionResumeNext(new Observable<Integer>() {
            @Override
            protected void subscribeActual(Observer<? super Integer> observer) {
                observer.onNext(4);
                observer.onNext(5);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.e(WP, String.valueOf(integer));
            }

            @Override
            public void onError(Throwable e) {
                Log.e(WP, e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e(WP, "onComplete");
            }
        });
    }

    /**
     * ====================onErrorReturn() 操作符 ======================
     * <p>
     * 可以捕获错误。遇到错误时，发送一个特殊事件，并且正常终止.注意后面的事件不会再发送
     */
    private void onErrorReturn() {
        Observable
                .create((ObservableOnSubscribe<Integer>) emitter -> {
                    emitter.onNext(1);
                    emitter.onNext(2);
                    emitter.onError(new Throwable("Throwable"));
                    emitter.onNext(3);

                })
                .onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) throws Exception {
                        Log.e(WP, "发生了错误：  " + throwable.getMessage());
                        return 404;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(WP, String.valueOf(integer));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(WP, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(WP, "onComplete");
                    }
                });
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
                .doOnEach(notification -> Log.e(WP, "doOnEach" + notification.getValue()))
                .doOnNext(i -> Log.e(WP, "doOnNext:" + i))
                .doAfterNext(i -> Log.e(WP, "doAfterNext:" + i))
                .doOnComplete(() -> Log.e(WP, "doOnComplete"))
                .doOnError(e -> Log.e(WP, "doOnError:" + e.getMessage()))
                .doOnTerminate(() -> Log.e(WP, "doOnTerminate"))
                .doAfterTerminate(() -> Log.e(WP, "doAfterTerminate"))
                .doOnSubscribe(disposable -> Log.e(WP, "doOnSubScribe"))
                .doFinally(() -> Log.e(WP, "doFinally"))
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
