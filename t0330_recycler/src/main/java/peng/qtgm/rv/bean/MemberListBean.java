package peng.qtgm.rv.bean;

/**
 * @author peng_wang
 * @date 2019/4/1
 */
public class MemberListBean {
    /**
     * type : 福利
     * url : https://ws1.sinaimg.cn/large/0065oQSqgy1fxno2dvxusj30sf10nqcm.jpg
     * used : true
     * who : lijinshanmx
     */

    private String type;
    private String url;
    private boolean used;
    private String who;

    @Override
    public String toString() {
        return "MemberListBean{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}

