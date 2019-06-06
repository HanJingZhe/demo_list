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
                break;
            case R.id.with_btn_4:
                break;
            case R.id.with_btn_5:
                break;
            case R.id.with_btn_6:
                break;
            case R.id.with_btn_7:
                break;
            case R.id.with_btn_8:
                break;
        }
    }

    /**
     * concatDelayError()/mergeDelayError() 操作符
     */
    private void mergeDelay() {

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
        Observable<String> observable1 = Observable.just("A", "B", "C","D","E","!","1","2","3");
        Observable<String> observable2 = Observable.just("嘻嘻", "嘿嘿", "哈哈");

        Observable
                .merge(observable1, observable2).delay(1, TimeUnit.SECONDS)
                .subscribe(s -> Log.e(WP, s));
    }
}
