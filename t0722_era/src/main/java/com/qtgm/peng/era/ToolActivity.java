package com.qtgm.peng.era;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToolActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);

        toolBar.inflateMenu(R.menu.drawer_menu);

    }

    public void toSina(View v){
        startActivity(new Intent(this,SinaActivity.class));
    }

}
