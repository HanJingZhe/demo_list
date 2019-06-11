package peng.qtgm.rx.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import peng.qtgm.rx.R;

@SuppressLint("CheckResult")
public class MainActivity extends AppCompatActivity {

    public static final String WP = "王鹏";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.main_btn_1, R.id.main_btn_2, R.id.main_btn_3, R.id.main_btn_4, R.id.main_btn_5, R.id.main_btn_6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_btn_1: //创建
                startActivity(new Intent(this, RxCreateActivity.class));
                break;
            case R.id.main_btn_2://转换操作符
                startActivity(new Intent(this, RxZipActivity.class));
                break;
            case R.id.main_btn_3://合并操作符
                startActivity(new Intent(this, RxWithActivity.class));
                break;
            case R.id.main_btn_4://功能操作符
                startActivity(new Intent(this, FunctionActivity.class));
                break;
            case R.id.main_btn_5://过滤操作符
                startActivity(new Intent(this, RxFilterActivity.class));
                break;
            case R.id.main_btn_6:
                condition();
                break;
        }
    }

    /**
     * 条件
     */
    private void condition() {
        Observable
                .range(0,10)  // 0 3 : 0,1,2
                .collect(() -> new ArrayList<Integer>(),(list, i) -> list.add(i))
                .subscribe(list -> Log.e(WP,list.toString()));
    }

}
