package com.qtgm.peng.t0819_enum.constant;

/**
 * @author peng_wang
 * @date 2019/8/19
 */
public enum GoclassStateEnum {

    HOLD(1), //待上课
    JUST(4), //正在上课
    FINISH(5);//上课结束

    private int state;

    GoclassStateEnum(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

}
