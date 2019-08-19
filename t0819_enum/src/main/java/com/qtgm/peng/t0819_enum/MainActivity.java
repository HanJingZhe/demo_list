package com.qtgm.peng.t0819_enum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.qtgm.peng.t0819_enum.constant.GoclassStateEnum;
import com.qtgm.peng.t0819_enum.constant.Constants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View tvTest = findViewById(R.id.main_tv_test);
        tvTest.setOnClickListener(v -> doPrint(3));

    }

    private void doPrint(int state) {
        for (GoclassStateEnum goclass : GoclassStateEnum.values()) {
            Log.e("王鹏", String.valueOf(goclass.getState()));
        }

        switch (state) {
            case Constants.GoclassState.HOLD:
                Log.e("王鹏","我是 HOLD");
                break;
            case Constants.GoclassState.JUST:
                Log.e("王鹏","我是 JUST");
                break;
            case Constants.GoclassState.FINISH:
                Log.e("王鹏","我是 FINISH");
                break;
        }


    }


}
