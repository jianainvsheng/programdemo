package com.source.sdk.base.eventbus;

public class Event<T> {
    /**
     * 登录成功后
     */
    public  static final int  LOGINCODE = 1000;
    /**
     * 获取用户信息
     */
    public static final int USERINFOCODE = 1001;
    /**
     * 退出登录
     */
    public static final int LOGINOUT = 1002;

    /**
     * 微信授权成功
     */
    public static final int WEIXINSHOUQ = 1003;
    /**
     * 刷新关注列表
     */
    public static final int REFRESTFOCUSLIST = 1004;

    /**
     * 视频上传
     */
    public static final int UPLOADVIDEO = 1005;
    /**
     * 支付成功
     */
    public static final int PAYSUCCESS = 1006;
    /**
     * 支付失败
     */
    public static final int PAYFAIL = 1007;
    /**
     * 支付取消
     */
    public static final int PAYCANNEL = 1008;

    /**
     * 刷新订单
     */
    public static final int REFRESH_ORDRELIST = 1009;
    private int code;
    private T data;

    public Event(int code) {
        this.code = code;
    }

    public Event(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
