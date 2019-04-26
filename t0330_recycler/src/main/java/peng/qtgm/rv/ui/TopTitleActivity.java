package peng.qtgm.rv.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration;

import java.util.ArrayList;
import java.util.List;

import peng.qtgm.rv.R;
import peng.qtgm.rv.adapter.TopAdapter;
import peng.qtgm.rv.bean.TopTitleBean;

/**
 *  rv 实现标题吸附
 *  依赖  PinnedSectionItemDecoration + BaseRecyclerViewAdapterHelper
 *  思路: 采用baseadapter的多布局方案  承载数据的bean类 实现MultiItemEntity 并定义int HEAD 和 DATA
 *  在list中添加标识
 *  适配器 继承 BaseMultiItemQuickAdapter 在构造方法中 利用此方法进行添加不同的布局 addItemType(TopTitleBean.TYPE_DATA, R.layout.adapter_top_data_item);
 *  这样在convert 中利用helper.getItemViewType() 来区分不同的布局 进行不同布局的赋值
 *  最后使用添加分割线的方式 实现悬浮吸附
 *
 */
public class TopTitleActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private Button topBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_title);
        mRecyclerView = findViewById(R.id.top_rv);
        topBtn = findViewById(R.id.top_btn);
        topBtn.setOnClickListener(this);

        TopAdapter adapter = new TopAdapter(initData());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*mRecyclerView.addItemDecoration(
                // 设置粘性标签对应的类型
                new PinnedHeaderItemDecoration.Builder(BaseHeaderAdapter.TYPE_HEADER)
                        // 设置分隔线资源ID
                        .setDividerId(R.drawable.divider)
                        // 开启绘制分隔线，默认关闭
                        .enableDivider(true)
                        // 通过传入包括标签和其内部的子控件的ID设置其对应的点击事件
                        //.setClickIds(R.id.iv_more)
                        // 是否关闭标签点击事件，默认开启
                        .disableHeaderClick(false)
                        // 设置标签和其内部的子控件的监听，若设置点击监听不为null，但是disableHeaderClick(true)的话，还是不会响应点击事件
                        .setHeaderClickListener(clickAdapter)
                        .create());*/

        mRecyclerView.addItemDecoration(new PinnedHeaderItemDecoration.Builder(TopTitleBean.TYPE_HEAD).create());
        mRecyclerView.setAdapter(adapter);
    }

    private List<TopTitleBean> initData() {
        List<TopTitleBean> list = new ArrayList<>();

        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto0, TopTitleBean.TYPE_HEAD));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto0, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto0, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto0, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto0, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto0, TopTitleBean.TYPE_DATA));



        list.add(new TopTitleBean("鸣人", R.mipmap.naruto0, TopTitleBean.TYPE_HEAD));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto1, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto1, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto1, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto1, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto1, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto1, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto1, TopTitleBean.TYPE_DATA));

        list.add(new TopTitleBean("卡卡西", R.mipmap.naruto0, TopTitleBean.TYPE_HEAD));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto3, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto3, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto3, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto3, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto3, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto3, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto3, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto3, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto3, TopTitleBean.TYPE_DATA));


        list.add(new TopTitleBean("宇智波鼬", R.mipmap.naruto0, TopTitleBean.TYPE_HEAD));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto4, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto4, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto4, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto4, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto4, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto4, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto4, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto4, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto4, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto4, TopTitleBean.TYPE_DATA));



        list.add(new TopTitleBean("迪达拉", R.mipmap.naruto0, TopTitleBean.TYPE_HEAD));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto5, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto5, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto5, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto5, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto5, TopTitleBean.TYPE_DATA));




        list.add(new TopTitleBean("小樱", R.mipmap.naruto0, TopTitleBean.TYPE_HEAD));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto6, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto6, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto6, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto6, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto6, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto6, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto6, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto6, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto6, TopTitleBean.TYPE_DATA));



        list.add(new TopTitleBean("雏田", R.mipmap.naruto0, TopTitleBean.TYPE_HEAD));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto7, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto7, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto7, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto7, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto7, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto7, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto7, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto7, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto7, TopTitleBean.TYPE_DATA));



        list.add(new TopTitleBean("写轮眼", R.mipmap.naruto0, TopTitleBean.TYPE_HEAD));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto8, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto8, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto8, TopTitleBean.TYPE_DATA));
        list.add(new TopTitleBean("火影忍者", R.mipmap.naruto8, TopTitleBean.TYPE_DATA));




        return list;
    }


    @Override
    public void onClick(View v) {
        try {
            Uri uri = Uri.parse("market://details?id="+ "com.tencent.mm");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "您没有安装应用市场", Toast.LENGTH_SHORT).show();
        }

        String mAddress = "market://details?id=" + getPackageName();
        Intent marketIntent = new Intent("android.intent.action.VIEW");
        marketIntent.setData(Uri.parse(mAddress ));
        startActivity(marketIntent);

    }
}
