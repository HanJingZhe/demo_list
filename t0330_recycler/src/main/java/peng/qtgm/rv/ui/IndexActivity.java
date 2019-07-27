package peng.qtgm.rv.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import peng.qtgm.rv.R;
import peng.qtgm.rv.dialog.TabDialog;

/**
 * 引导页
 */
public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.index_tv_srl, R.id.index_tv_flow, R.id.index_tv_dot, R.id.index_tv_main, R.id.index_tv_pull, R.id.index_tv_title,
            R.id.index_tv_smart, R.id.index_tv_srl_style})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.index_tv_dot:
                new TabDialog(this).show();
                break;
            case R.id.index_tv_srl:
                startActivity(new Intent(this, SrlActivity.class));
                break;
            case R.id.index_tv_srl_style:
                startActivity(new Intent(this,SrlStyleActivity.class));
                break;
            case R.id.index_tv_smart:
                startActivity(new Intent(this, SmartActivity.class));
                break;
            case R.id.index_tv_flow:
                startActivity(new Intent(this, FlowActivity.class));
                break;
            case R.id.index_tv_main:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.index_tv_pull:
                startActivity(new Intent(this, PullActivity.class));
                break;
            case R.id.index_tv_title:
                startActivity(new Intent(this, TopTitleActivity.class));
                break;
        }
    }

}
