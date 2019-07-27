package peng.qtgm.rv.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.header.StoreHouseHeader;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import butterknife.BindView;
import butterknife.ButterKnife;
import peng.qtgm.rv.R;
import peng.qtgm.rv.utils.DataModel;

public class SrlStyleActivity extends AppCompatActivity {

    @BindView(R.id.style_rv)
    RecyclerView styleRv;
    @BindView(R.id.style_srl)
    SmartRefreshLayout styleSrl;

    private DataModel.MyStringAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_srl_style);
        ButterKnife.bind(this);
        //initSrl();
        initRv();
    }

    private void initSrl() {

        //styleSrl.setRefreshHeader(new ClassicsHeader(this));//设置 Header 为 贝塞尔雷达 样式
        styleSrl.setRefreshHeader(new WaterDropHeader(this)); //方IOS
        styleSrl.setRefreshHeader(new StoreHouseHeader(this));//StoreHouse
        //设置 Footer 为 球脉冲 样式
        styleSrl.setRefreshFooter(new ClassicsFooter(this));

        //其他属性
        styleSrl.setEnableAutoLoadMore(true); //滚动到底部自动触发加载更多
        styleSrl.setEnableScrollContentWhenLoaded(true); //是否在加载完成时滚动列表显示新的内容
        styleSrl.setEnableLoadMoreWhenContentNotFull(true); //内容不足一页 是否开启加载更多
        styleSrl.setEnableFooterFollowWhenLoadFinished(false); //全部加载完成后 footer 是否保留
        styleSrl.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4

    }

    private void initRv() {
       /* styleSrl.setEnableRefresh(false);
        styleSrl.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4*/
        adapter = new DataModel.MyStringAdapter(DataModel.initStringData(5));
        styleRv.setLayoutManager(new LinearLayoutManager(this));
        styleRv.setAdapter(adapter);
    }

}
