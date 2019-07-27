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
 * test 智能判定的刷新边界
 */
public class SmartActivity extends AppCompatActivity {

    @BindView(R.id.smart_tv_ad)
    TextView smartTvAd;
    @BindView(R.id.smart_rv)
    RecyclerView smartRv;
    @BindView(R.id.smart_srl)
    SmartRefreshLayout smartSrl;

    private int page = 0;
    private int page_size = 20;

    private SrlAdapter srlAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart);
        ButterKnife.bind(this);

        initRv();
    }

    private void initRv() {
        smartRv.setLayoutManager(new LinearLayoutManager(this));
        srlAdapter = new SrlAdapter(null);
        smartRv.setAdapter(srlAdapter);
        threadData();

        //刷新和加载的监听
        smartSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                threadData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smartSrl.resetNoMoreData();
                page = 0;
                threadData();
            }
        });
    }

    /*
     * 模拟数据
     */
    @SuppressLint("CheckResult")
    private void threadData() {
        Observable
                .timer(500, TimeUnit.MILLISECONDS)
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
            smartSrl.finishRefresh();
        } else {
            if (page > 2) {
                smartSrl.setNoMoreData(true);
                return;
            }
            srlAdapter.addData(list);
            smartSrl.finishLoadMore();
        }
    }

}
