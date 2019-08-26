package com.qtgm.peng.t0805_save.bean;

/**
 * @author peng_wang
 * @date 2019/8/21
 */
public class EventBase<T> {

    private int code;
    private T t;

    public EventBase(int code, T t) {
        this.code = code;
        this.t = t;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
