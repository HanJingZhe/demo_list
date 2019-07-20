package peng.qtgm.rv.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import peng.qtgm.rv.R;

public class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MyAdapter(@Nullable List<String> data) {
        super(R.layout.adapter_dance_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.dance_tv_name, item);
    }

}
