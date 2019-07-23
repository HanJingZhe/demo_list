package com.qtgm.peng.era.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qtgm.peng.era.R;

import java.util.List;

public class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MyAdapter(@Nullable List<String> data) {
        super(R.layout.adapter_myadapter_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.myadapter_tv_name, item);
    }
}
