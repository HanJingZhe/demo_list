package peng.qtgm.rv.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import peng.qtgm.rv.R;
import peng.qtgm.rv.adapter.FlowAdapter;
import peng.qtgm.rv.utils.FlowLayoutManager;
import peng.qtgm.rv.utils.FlowLayoutManager2;
import peng.qtgm.rv.utils.OverLayCardLayoutManager;

public class FlowActivity extends AppCompatActivity {


    private RecyclerView flowRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        flowRv = findViewById(R.id.flow_rv);
        flowRv.setLayoutManager(new LinearLayoutManager(this));
        //flowRv.setLayoutManager(new FlowLayoutManager()); //流布局
        //flowRv.setLayoutManager(new FlowLayoutManager2());//流布局
        flowRv.setLayoutManager(new OverLayCardLayoutManager()); //卡片 无效
        flowRv.setAdapter(new FlowAdapter(initData()));



    }

    private List<String> initData() {
        return new ArrayList<>(Arrays.asList("你好", "你好你好你好", "火影忍者", "火影忍者中文网",
                "鸣人", "卡卡西", "雏田", "android studio", "我是超级赛亚人", "外你", "我爱你", "phone", "android", "java", "php"));

    }
}
