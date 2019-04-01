package peng.qtgm.rv.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import peng.qtgm.rv.R;
import peng.qtgm.rv.bean.AllShowBean;
import peng.qtgm.rv.bean.MemberListBean;

/**
 * @author peng_wang
 * @date 2019/3/30
 */
public class GroupAdapter extends BaseSectionQuickAdapter<AllShowBean, BaseViewHolder> {

    public GroupAdapter(List<AllShowBean> data) {
        super(R.layout.adapter_child, R.layout.adapter_home, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, AllShowBean item) {
        helper.setText(R.id.header_title, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllShowBean item) {
        MemberListBean t = item.t;
        helper.setText(R.id.child_name, t.getWho());
        ImageView childAge = helper.getView(R.id.child_age);
        Glide.with(mContext).load(t.getUrl()).into(childAge);
    }

}
