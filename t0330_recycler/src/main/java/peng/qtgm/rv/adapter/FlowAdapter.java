package peng.qtgm.rv.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import peng.qtgm.rv.R;

/**
 * @author peng_wang
 * @date 2019/4/1
 */
public class FlowAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public FlowAdapter(@Nullable List<String> data) {
        super(R.layout.adapter_body_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        //helper.setText(R.id.flow_name, item);
    }
}
