package peng.qtgm.rv.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import peng.qtgm.rv.R;
import peng.qtgm.rv.adapter.SrlAdapter;

/**
 * 关于刷新的主页
 */
@SuppressLint("CheckResult")
public class SrlActivity extends AppCompatActivity {

    @BindView(R.id.srl_rv)
    RecyclerView srlRv;
    @BindView(R.id.srl_srl)
    SmartRefreshLayout srlSrl;

    private int page = 0;
    private int page_size = 20;

    private SrlAdapter srlAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_srl);
        ButterKnife.bind(this);
        initRv();
    }

    /*
     * 初始化列表
     */
    private void initRv() {
        srlRv.setLayoutManager(new LinearLayoutManager(this));
        srlAdapter = new SrlAdapter(null);
        srlRv.setAdapter(srlAdapter);
        threadData();


        TextView tv = new TextView(this);
        tv.setText("xixi");
        //设置 Header 为 贝塞尔雷达 样式
        srlSrl.setRefreshHeader(new ClassicsHeader(this));
        //设置 Footer 为 球脉冲 样式
        srlSrl.setRefreshFooter(new ClassicsFooter(this));

        //其他属性
        srlSrl.setEnableAutoLoadMore(true); //滚动到底部自动触发加载更多
        srlSrl.setEnableScrollContentWhenLoaded(true); //是否在加载完成时滚动列表显示新的内容
        srlSrl.setEnableLoadMoreWhenContentNotFull(true); //内容不足一页 是否开启加载更多
        srlSrl.setEnableFooterFollowWhenLoadFinished(true); //全部加载完成后 footer 是否保留
        srlSrl.setEnableOverScrollDrag(false);//是否启用越界拖动（仿苹果效果）1.0.4
        srlSrl.setPrimaryColorsId(R.color.color_ff2c6d);//主题颜色 刷新的


        //刷新和加载的监听
        srlSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                threadData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                srlSrl.resetNoMoreData();
                page = 0;
                threadData();
            }
        });
    }


    /*
     * 模拟数据
     */
    private void threadData() {
        Observable
                .timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io()) //订阅在子线程
                .observeOn(AndroidSchedulers.mainThread())//回调在主线程
                .subscribe(aLong -> {
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < page_size; i++) {
                        list.add("我是第" + (page * page_size + i) + "条数据");
                    }
                    loadData(list);
                });
    }

    /*
     * 装载数据
     */
    private void loadData(List<String> list) {
        if (page == 0) {
            srlAdapter.setNewData(list);
            srlSrl.finishRefresh();
        } else {
            if (page > 2) {
                srlSrl.setNoMoreData(true);
                return;
            }
            srlAdapter.addData(list);
            srlSrl.finishLoadMore();
        }
    }


}
