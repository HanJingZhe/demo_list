package peng.qtgm.rv.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @author peng_wang
 * @date 2019/4/1
 */
public class AllShowBean extends SectionEntity<MemberListBean> {

    public AllShowBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public AllShowBean(MemberListBean memberListBean) {
        super(memberListBean);
    }

}
