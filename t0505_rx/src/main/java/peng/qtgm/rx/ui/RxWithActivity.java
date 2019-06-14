package peng.qtgm.rx.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import peng.qtgm.rx.R;

import static peng.qtgm.rx.ui.MainActivity.WP;

@SuppressLint("CheckResult")
/**
 * 关于Rx 合并操作符
 */
public class RxWithActivity extends AppCompatActivity {

    @BindView(R.id.with_btn_1)
    Button withBtn1;
    @BindView(R.id.with_btn_2)
    Button withBtn2;
    @BindView(R.id.with_btn_3)
    Button withBtn3;
    @BindView(R.id.with_btn_4)
    Button withBtn4;
    @BindView(R.id.with_btn_5)
    Button withBtn5;
    @BindView(R.id.with_btn_6)
    Button withBtn6;
    @BindView(R.id.with_btn_7)
    Button withBtn7;
    @BindView(R.id.with_btn_8)
    Button withBtn8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_with);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.with_btn_1, R.id.with_btn_2, R.id.with_btn_3, R.id.with_btn_4, R.id.with_btn_5, R.id.with_btn_6, R.id.with_btn_7, R.id.with_btn_8})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.with_btn_1:
                mergeAndconcat();
                break;
            case R.id.with_btn_2:
                mergeDelay();
                break;
            case R.id.with_btn_3:
                zip();
                break;
            case R.id.with_btn_4:
                combineLatest();
                break;
            case R.id.with_btn_5:
                reduce();
                break;
            case R.id.with_btn_6:
                collect();
                break;
            case R.id.with_btn_7:
                startWith();
                break;
            case R.id.with_btn_8:
                count();
                break;
        }
    }

    /**
     * count() 操作符
     * 统计被观察者发送事件数量
     */
    private void count() {
        Observable
                .just(1, 2, 3, 4)
                .count()
                .subscribe(aLong -> Log.e(WP, "发送事件的数量 ： " + aLong));
    }

    /**
     * startWith/startWithArray 操作符
     * 在一个被观察者发送时间前，追加发送一些数据/一个新的被观察者
     */
    private void startWith() {
        Observable.just(7, 8, 9)
                .startWith(6)   //在发送序列去追加单个数据
                .startWithArray(4, 5)  //在发送序列去追加多个数据
                .startWith(Observable.just(1, 2, 3))  //在发送序列去追加单个被观察者
                .subscribe(integer -> Log.e(WP, integer + ""));
    }

    /**
     * collect()操作符
     * 作用是把 Observable(被观察者)发送的事件收集到一个数据结构中
     */
    private void collect() {
        Observable.just(1, 2, 3, 4, 5)
                .collect((Callable<List<Integer>>) () -> new ArrayList<>(),
                        (integers, integer) -> integers.add(integer))
                .subscribe(list -> Log.e(WP, list.toString()));
    }

    /**
     * reduce () 操作符
     * * 把被观察者需要发送的数据按照指定规则聚合成一个数据发送
     * * 聚合的规则需要我们编写，内部流程是前两个数据按照我们的规则合并后，再与后面的数据按规则合并，
     * 依次类推。这样说有点抽象，看下面的例子。
     */
    private void reduce() {
        Observable
                .just(1, 2, 3, 4, 5)
                .reduce((integer, integer2) -> integer + integer2)
                .subscribe(integer -> Log.e(WP + "reduce", "最终计算的结果是 :  " + integer));
    }

    /**
     * combineLatest () 操作符
     * <p>
     * 当两个Observable 中的任何一个发送了数据，将先发送了数据的Observable的最新（最后）一个数据和另一个Observable发送的每个数据结合，
     * 最终基于该结合的结果发送数据
     * * 与zip()的区别： zip()是按个数合并，即1对1合并；而combineLatest()是基于时间合并，，即在同一时间点上合并
     */
    private void combineLatest() {
        Observable<String> o1 = Observable.just("1", "2", "3", "4");
        Observable<Long> o2 = Observable.intervalRange(1, 4, 2, 1, TimeUnit.SECONDS);
        Observable
                .combineLatest(o1, o2, (s, l) -> "合并:" + s + l)
                .subscribe(s -> Log.e(WP, s));
    }

    /**
     * zip() 操作符
     * * 把多个Observable合并后，并且把这些Observable的数据进行转换再发射出去。
     * 转换之后的数据数目由最短数据长度的那个Observable决定。发射完最终会自动调用观察者的onComplete方法()
     * * 如以下代码： 数据长度为4的observable1和数据长度为3的observable2进行合并转换后，观察者只接收到3个数据
     */
    private void zip() {
        Observable<String> o1 = Observable.just("1", "2", "3", "4");
        Observable<String> o2 = Observable.just("a", "b", "c", "d", "e");
        Observable.zip(o1, o2, (s, s2) -> s + s2).subscribe(s -> Log.e(WP, s + ""));
    }

    /**
     * concatDelayError()/mergeDelayError() 操作符
     * 这两个操作符的作用是： 使用concat()和merge()操作符时，若其中一个被观察者发送onError事件，则会马上终止其它被观察者继续发送事件。所以呐，这时使用concatError()/
     * * mergeDelayError()事件可以使onError事件推迟到其它被观察者发送事件结束后在再触发
     */
    private void mergeDelay() {
        Observable.concatArrayDelayError(Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onError(new NullPointerException());
            emitter.onNext(3);
            emitter.onNext(4);
        }), Observable.just(5, 6))
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
                        Log.e(WP, "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e(WP, "onComplete");
                    }
                });
    }

    /**
     * merget() conCat(); 操作符
     * * merge()操作符是把多个Observable合并成一个进行发射。merge可能会让合并到Observable的数据顺序发生错乱(组合被观察者数量<=4个)(并行无序)
     * * mergeArray()操作符和merge作用一样，但不同的是组合被观察者数量>4个)(并行无序)
     * *
     * * concat()操作符也是把多个Observable合并成一个进行发射。但concat则保证合并的每个Observable的事件按顺序发射出去。(组合被观察者数量<=4个)(串行有序)
     * * concatArray()操作符和concat作用一样，但不同的是组合被观察者数量>4个)(串行有序)
     */
    private void mergeAndconcat() {
        Observable<String> observable1 = Observable.just("A", "B", "C", "D", "E", "!", "1", "2", "3");
        Observable<String> observable2 = Observable.just("嘻嘻", "嘿嘿", "哈哈");

        Observable
                .merge(observable1, observable2).delay(1, TimeUnit.SECONDS)
                .subscribe(s -> Log.e(WP, s));
    }
}
