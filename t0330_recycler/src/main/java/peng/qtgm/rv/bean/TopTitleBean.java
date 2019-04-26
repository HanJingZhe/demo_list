package peng.qtgm.rv.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author peng_wang
 * @date 2019/4/22
 */
public class TopTitleBean implements MultiItemEntity {

    public static final int TYPE_HEAD = 1;
    public static final int TYPE_DATA = 2;

    private String name;
    private int image_res;
    private int type;

    public TopTitleBean(String name, int image_res, int type) {
        this.name = name;
        this.image_res = image_res;
        this.type = type;
    }

    public static int getTypeHead() {
        return TYPE_HEAD;
    }

    public static int getTypeData() {
        return TYPE_DATA;
    }

    public int getImage_res() {
        return image_res;
    }

    public void setImage_res(int image_res) {
        this.image_res = image_res;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
