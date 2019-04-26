package peng.qtgm.rv.bean;

import java.util.List;

/**
 * @author peng_wang
 * @date 2019/4/1
 */
public class GroupBean  {
    /**
     * house : 小白
     * memberList : [{"type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqgy1fxno2dvxusj30sf10nqcm.jpg","used":true,"who":"lijinshanmx"},{"type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg","used":true,"who":"lijinshanmx"},{"type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqgy1fwyf0wr8hhj30ie0nhq6p.jpg","used":true,"who":"lijinshanmx"},{"type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqgy1fwgzx8n1syj30sg15h7ew.jpg","used":true,"who":"lijinshanmx"}]
     */

    private String house;
    private List<MemberListBean> memberList;

    @Override
    public String toString() {
        return "GroupBean{" +
                "house='" + house + '\'' +
                ", memberList=" + memberList +
                '}';
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public List<MemberListBean> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<MemberListBean> memberList) {
        this.memberList = memberList;
    }

}
