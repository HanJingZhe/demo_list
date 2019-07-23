package com.qtgm.peng.era;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qtgm.peng.era.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class TestActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test4);

        RecyclerView commentList = findViewById(R.id.comment_list);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i + 1 + "");
        }
        MyAdapter adapter = new MyAdapter(initData());
        commentList.setLayoutManager(new LinearLayoutManager(this));
        commentList.setAdapter(adapter);
    }

    private List<String> initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

}
