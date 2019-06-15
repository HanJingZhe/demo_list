package peng.qtgm.widget.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import peng.qtgm.widget.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_btn_tablayout)
    Button mainBtnTablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.main_btn_tablayout)
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.main_btn_tablayout:
                startActivity(new Intent(this,TablayoutActivity.class));
                break;
        }
    }


}
