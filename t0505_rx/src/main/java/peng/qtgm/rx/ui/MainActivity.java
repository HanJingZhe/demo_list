package peng.qtgm.rx.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import peng.qtgm.rx.R;

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
            case R.id.main_btn_1:
                startActivity(new Intent(this,RxCreateActivity.class));
                break;
            case R.id.main_btn_2:
                startActivity(new Intent(this,RxZipActivity.class));
                break;
            case R.id.main_btn_3:
                startActivity(new Intent(this,RxWithActivity.class));
                break;
            case R.id.main_btn_4:
                startActivity(new Intent(this,FunctionActivity.class));
                break;
            case R.id.main_btn_5:
                startActivity(new Intent(this,RxCreateActivity.class));
                break;
            case R.id.main_btn_6:
                startActivity(new Intent(this,RxCreateActivity.class));
                break;
        }
    }

}
