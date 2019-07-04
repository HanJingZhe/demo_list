package peng.qtgm.rv.ui;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import peng.qtgm.rv.R;
import peng.qtgm.rv.adapter.GroupAdapter;
import peng.qtgm.rv.bean.AllShowBean;
import peng.qtgm.rv.bean.GroupBean;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mainRv;
    private GroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initGroupData();
        /**
         * 一个rv实现分组列表  缺点 header只能放一个 String
         */
        mainRv = findViewById(R.id.main_rv);
        mainRv.setLayoutManager(new GridLayoutManager(this,4));
        adapter = new GroupAdapter(initGroupData());
        mainRv.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d("王鹏", "adapter.getData():" + adapter.getData());
            }
        });
    }

    public String getJson(String fileName,Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private List<AllShowBean> initGroupData(){
        String json = getJson("test.json", this);
        Type listType = new TypeToken<List<GroupBean>>() {
        }.getType();
        List<GroupBean> list = new Gson().fromJson(json, listType );

        List<AllShowBean> allList = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++){
            allList.add(new AllShowBean(true,list.get(i).getHouse()));
            for(int j = 0 ; j < list.get(i).getMemberList().size() ; j++){
                allList.add(new AllShowBean(list.get(i).getMemberList().get(j)));
            }
        }
        return allList;
    }

    /*private List<HomeBean> initData() {
        List<HomeBean> list = new ArrayList<>();
        for(int i = 0 ; i < 3 ; i++){
            list.add(new HomeBean(true, "我是家庭" + i));
            for(int j = 0 ; j < 5 ; j ++){
                list.add(new HomeBean(new MemberBean("小白"+j,j)));
            }
        }
        return list;
    }*/

}
