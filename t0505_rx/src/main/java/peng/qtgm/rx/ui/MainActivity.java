package peng.qtgm.rx.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import peng.qtgm.rx.R;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "王鹏";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.main_btn_1, R.id.main_btn_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_btn_1:
                fromIterable();
                break;
            case R.id.main_btn_2:
                timer();
                break;
        }
    }

    private void timer() {
        Log.i(TAG,"kaishi");
        Flowable.timer(3,TimeUnit.SECONDS,AndroidSchedulers.mainThread())
                .subscribe(aLong -> Log.i(TAG,":::::"+aLong+"线程:"+Thread.currentThread().getName()));
    }

    private void fromIterable() {

    }
}
