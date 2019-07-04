package peng.qtgm.rv.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import peng.qtgm.rv.R;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.index_tv_srl, R.id.index_tv_flow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.index_tv_srl:
                startActivity(new Intent(this,SrlActivity.class));
                break;
            case R.id.index_tv_flow:
                startActivity(new Intent(this,FlowActivity.class));
                break;
        }
    }
}
