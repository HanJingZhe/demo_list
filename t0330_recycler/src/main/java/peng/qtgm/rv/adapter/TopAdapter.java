package peng.qtgm.rv.adapter;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oushangfeng.pinnedsectionitemdecoration.utils.FullSpanUtil;

import java.util.List;

import peng.qtgm.rv.R;
import peng.qtgm.rv.bean.TopTitleBean;

/**
 * @author peng_wang
 * @date 2019/4/22
 */
public class TopAdapter extends BaseMultiItemQuickAdapter<TopTitleBean, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public TopAdapter(List<TopTitleBean> data) {
        super(data);
        addItemType(TopTitleBean.TYPE_DATA, R.layout.adapter_top_data_item);
        addItemType(TopTitleBean.TYPE_HEAD, R.layout.adapter_top_head_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, TopTitleBean item) {
        switch (helper.getItemViewType()) {
            case TopTitleBean.TYPE_DATA:
                helper.setImageResource(R.id.top_iv,item.getImage_res());
                break;
            case TopTitleBean.TYPE_HEAD:
                helper.setText(R.id.top_tv,item.getName());
                break;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        FullSpanUtil.onAttachedToRecyclerView(recyclerView, this, TopTitleBean.TYPE_HEAD);
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        FullSpanUtil.onViewAttachedToWindow(holder, this, TopTitleBean.TYPE_HEAD);
    }
}
