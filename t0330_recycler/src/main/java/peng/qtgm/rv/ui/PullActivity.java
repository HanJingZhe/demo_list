package peng.qtgm.rv.ui;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import peng.qtgm.rv.R;
import peng.qtgm.rv.adapter.PullAdapter;
import peng.qtgm.rv.bean.AllShowBean;
import peng.qtgm.rv.bean.GroupBean;

public class PullActivity extends AppCompatActivity {

    private RecyclerView pullRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);

        pullRv = findViewById(R.id.pull_rv);
        pullRv.setLayoutManager(new LinearLayoutManager(this));
        PullAdapter adapter = new PullAdapter(initGroupData());
        pullRv.setAdapter(adapter);


        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(pullRv);

        // 开启拖拽
        adapter.enableDragItem(itemTouchHelper, R.id.header_title, true);
        adapter.setOnItemDragListener(onItemDragListener);

        // 开启滑动删除
        adapter.enableSwipeItem();
        adapter.setOnItemSwipeListener(onItemSwipeListener);

        //一行代码开启动画 默认CUSTOM动画
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

    }

    OnItemDragListener onItemDragListener = new OnItemDragListener() {
        @Override
        public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos){}
        @Override
        public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {}
        @Override
        public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {}
    };

    OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
        @Override
        public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {}
        @Override
        public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {}
        @Override
        public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {}

        @Override
        public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

        }
    };





    private List<GroupBean> initGroupData(){
        String json = getJson("test.json", this);
        Type listType = new TypeToken<List<GroupBean>>() {
        }.getType();
        List<GroupBean> list = new Gson().fromJson(json, listType );
        return list;
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





}
