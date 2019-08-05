package com.qtgm.peng.t0801_system;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qtgm.peng.t0801_system.utils.CPU2;
import com.qtgm.peng.t0801_system.utils.CPUUtil;
import com.qtgm.peng.t0801_system.utils.SystemMemoryCupNetUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_tv_current_memory)
    TextView mainTvCurrentMemory;
    @BindView(R.id.main_tv_power)
    TextView mainTvPower;
    @BindView(R.id.main_tv_total_memory)
    TextView mainTvTotalMemory;
    @BindView(R.id.main_tv_cup_max)
    TextView mainTvCupMax;
    @BindView(R.id.main_tv_cup_min)
    TextView mainTvCupMin;
    @BindView(R.id.main_tv_cup_current)
    TextView mainTvCupCurrent;
    @BindView(R.id.main_tv_cup_name)
    TextView mainTvCupName;
    @BindView(R.id.main_tv_net_type)
    TextView mainTvNetType;
    @BindView(R.id.main_btn_get_current_memory)
    Button mainBtnGetCurrentMemory;
    @BindView(R.id.main_btn_get_current_cup)
    Button mainBtnGetCurrentCup;
    @BindView(R.id.main_tv_power_ing)
    TextView mainTvPowerIng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.bind(this);

        //当前可用内存
        String availMemory = SystemMemoryCupNetUtil.getAvailMemory(this);
        mainTvCurrentMemory.setText("当前可用内存:" + availMemory);

        //当前总内存
        String totalMemory = SystemMemoryCupNetUtil.getTotalMemory(this);
        mainTvTotalMemory.setText("当前总内存:" + totalMemory);

        //获取CPU最大频率
        String maxCpuFreq = SystemMemoryCupNetUtil.getMaxCpuFreq();
        mainTvCupMax.setText("获取CPU最大频率:" + maxCpuFreq);

        //获取CPU最小频率
        String minCpuFreq = SystemMemoryCupNetUtil.getMinCpuFreq();
        mainTvCupMin.setText("获取CPU最小频率:" + minCpuFreq);

        //实时获取CPU当前频率
        String currCpuFreq = SystemMemoryCupNetUtil.getCurCpuFreq();
        mainTvCupCurrent.setText("实时获取CPU当前频率:" + currCpuFreq);

        //获取CPU名字
        String cpuName = SystemMemoryCupNetUtil.getCpuName();
        mainTvCupName.setText("获取CPU名字:" + cpuName);

        //获取设备是否正在充电
        boolean charging = SystemMemoryCupNetUtil.isCharging(this);
        mainTvPowerIng.setText("是否正在充电" + charging);

        //获取电量
        int batteryCount = SystemMemoryCupNetUtil.getBatteryCount(this);
        mainTvPower.setText("当前电量:" + batteryCount);

        //获取网络
        String networkTypeName = SystemMemoryCupNetUtil.getNetworkTypeName(this);
        mainTvNetType.setText("当前网络类型" + networkTypeName);

        //cpu的使用率
        //float cpuRate = CPUUtil.getCpuRate();
        //Log.e("王鹏", "cpu的使用率:" + cpuRate);

        //c2
        String cpuRateDesc_all = CPU2.getCPURateDesc_All();
        Log.e("王鹏", "CPU第二种:" + cpuRateDesc_all);
    }

    @OnClick({R.id.main_btn_get_current_memory, R.id.main_btn_get_current_cup, R.id.main_btn_get_use_cup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_get_current_memory:
                String availMemory = SystemMemoryCupNetUtil.getAvailMemory(this);
                mainBtnGetCurrentMemory.setText("当前可用内存:" + availMemory);
                break;
            case R.id.main_btn_get_current_cup:
                String currCpuFreq = SystemMemoryCupNetUtil.getCurCpuFreq();
                mainBtnGetCurrentCup.setText("实时获取CPU当前频率:" + currCpuFreq);
                break;
            case R.id.main_btn_get_use_cup:
                //CPU 使用率
                cpu();
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void cpu() {
        /*Observable.create(new ObservableOnSubscribe<Float>() {
            @Override
            public void subscribe(ObservableEmitter<Float> emitter) throws Exception {
                float cpuRate = CPUUtil.getCpuRate();
                Log.e("王鹏", "cpu的使用率:" + cpuRate);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Float>() {
                    @Override
                    public void accept(Float aFloat) throws Exception {
                        Log.e("王鹏", "rx CUP shiyonglu " + aFloat);
                    }
                });*/


        //cpu的使用率
        String cpuRate = CPUUtil.getCpuRate();
        Log.e("王鹏", "cpu的使用率:" + cpuRate);
/*
        //c2
        String cpuRateDesc_all = CPU2.getCPURateDesc_All();
        Log.e("王鹏","CPU第二种:"+cpuRateDesc_all);

        int numCores = CPU2.getNumCores();
        Log.e("王鹏","CPU核心数"+numCores);*/

    }
}
