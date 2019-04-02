package peng.qtgm.rv.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import peng.qtgm.rv.R;
import peng.qtgm.rv.bean.GroupBean;

/**
 * @author peng_wang
 * @date 2019/4/1
 */
public class PullAdapter extends BaseItemDraggableAdapter<GroupBean, BaseViewHolder> {

    public PullAdapter(List<GroupBean> data) {
        super(R.layout.adapter_home,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GroupBean item) {
        TextView view = helper.setText(R.id.header_title, item.getHouse()).getView(R.id.header_title);
        view.setPadding(50,50,20,50);
    }
}
