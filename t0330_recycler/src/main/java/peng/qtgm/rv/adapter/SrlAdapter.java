package peng.qtgm.rv.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import peng.qtgm.rv.R;

/**
 * @author peng_wang
 * @date 2019/7/4
 */
public class SrlAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SrlAdapter(@Nullable List<String> data) {
        super(R.layout.adapter_srl_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.srl_item_tv_name, item);
    }
}
