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
import peng.qtgm.rx.R;

import static peng.qtgm.rx.ui.MainActivity.WP;

@SuppressLint("CheckResult")
/**
 * 关于Rx 转换操作符
 */
public class RxZipActivity extends AppCompatActivity {

    @BindView(R.id.zip_btn_1)
    Button zipBtn1;
    @BindView(R.id.zip_btn_3)
    Button zipBtn3;
    @BindView(R.id.zip_btn_4)
    Button zipBtn4;
    @BindView(R.id.zip_btn_5)
    Button zipBtn5;
    @BindView(R.id.zip_btn_6)
    Button zipBtn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_zip);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.zip_btn_1, R.id.zip_btn_3, R.id.zip_btn_4, R.id.zip_btn_5, R.id.zip_btn_6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zip_btn_1:
                map();
                break;
            case R.id.zip_btn_3:
                flatMap();
                break;
            case R.id.zip_btn_4:
                concatMap();
                break;
            case R.id.zip_btn_5:
                buffer();
                break;
            case R.id.zip_btn_6:
                groupBy();
                break;
        }
    }

    /**
     * groupBy()操作符
     */
    private void groupBy() {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("a");
        list.add("a");
        list.add("c");

        Observable
                .fromIterable(list)
                .groupBy(s -> {
                    if(s.equals("a")){
                        return 1;
                    }else if(s.equals("b")){
                        return 2;
                    }else{
                        return 3;
                    }
                })
                .subscribe(groupObservable -> {
                    Integer key = groupObservable.getKey();
                    switch (key){
                        case 1:
                            groupObservable.subscribe(s -> Log.e(WP,"我是1组:"+s));
                            break;
                        case 2:
                            groupObservable.subscribe(s -> Log.e(WP,"我是2组:"+s));
                            break;
                        case 3:
                            groupObservable.subscribe(s -> Log.e(WP,"我是3组:"+s));
                            break;
                    }
                });

    }

    /**
     * buffer() 操作符
     * 把发射数据按照一定间隔分成若干段。按每段的数据转换成新的Observable，这个Observable把一段数据一次性发射出去。
     * * 可以简单地理解为把一组数据分成若干小组发射出去，而不是单个单个地发射出去
     */
    private void buffer() {
        Observable
                .just(1, 2, 3, 4,5)
                .buffer(2)
                .subscribe(integers -> Log.e(WP, integers.toString()));
    }

    /**
     * concatMap()操作符
     * 与上面的flatMap作用基本一样，与flatMap唯一不同的是concat能保证Observer接收到Observable集合发送事件的顺序
     */
    private void concatMap() {
        Observable
                .just(1, 2, 3, 4, 5)
                .concatMap(i -> {
                    List<String> list = new ArrayList<>();
                    for (int a = 0; a < 3; a++) {
                        list.add("aa" + i);
                    }
                    return Observable.fromIterable(list).delay(100, TimeUnit.MILLISECONDS);
                })
                .subscribe(i -> Log.e(WP, "flatMap:" + i));
    }

    /**
     * flatMap()操作符
     * flatMap操作符， 将Observable每一次发射的事件都转换成一个Observable，也就是说把Observable的发射事件集合转换成Observable集合。
     * * 然后观察者Observer最终观察的是Observable集合。但是观察者不能保证接收到这Observable集合发送事件的顺序。
     * *
     * * 是不是很抽象？ 先来看看这一个流程：  观察者.create(事件发射器).flatMap(转换器).subscribe(观察者)
     * *
     * * 再来看看例子 ： 下面的代码，一开始Observable通过发射器的onNext发送了0-9这10个事件发送出去，正常来说Observer接收到就是 0 - 9 这10个数据
     * * 然而中间经过了flatMap的转换。这 10个事件都分别在Funcation转换器上新的Observable。而最终观察者接收的就是这10个新的Observable的发送事件。
     */
    private void flatMap() {
        Observable
                .just(1, 2, 3, 4, 5)
                .flatMap(i -> {
                    List<String> list = new ArrayList<>();
                    for (int a = 0; a < 3; a++) {
                        list.add("aa" + i);
                    }
                    return Observable.fromIterable(list).delay(100, TimeUnit.MILLISECONDS);
                })
                .subscribe(i -> Log.e(WP, "flatMap:" + i));
    }

    /**
     * map()操作符
     * * map操作符，可以说是的被观察者转换器。 通过指定一个Funcation对象，
     * 将被观察者(Observable)转换成新的被观察者(Observable)对象并发射，
     * 观察者会收到新的被观察者并处理
     * *
     * *
     * * 本来发射的数据是 数字1，然后观察者接收到的是 “ 这是新的观察数据===： 1”
     * *
     * * 流程：  被观察者.create(事件发射器).map(转换器).subscribe(观察者)
     */
    private void map() {
        Observable
                .create((ObservableOnSubscribe<Integer>) e -> {
                    for (int i = 0; i < 10; i++) {
                        e.onNext(i);
                    }
                })
                .map(i -> "通过map()转换" + i)
                .subscribe(s -> Log.e(WP, s));
    }
}
