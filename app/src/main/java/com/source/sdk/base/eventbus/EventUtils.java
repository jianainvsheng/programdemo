package com.source.sdk.base.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * 事件总线类
 * 【onEvent】
 * Created by yangbo-ds3
 */
public class EventUtils {

    /**
     * 默认事件总线类
     */
    private static final EventBus mBus = EventBus.getDefault();

    /**
     *  注册
     * @param subscriber
     */
    public static EventBus register(Object subscriber, boolean need){
        if(need){
            mBus.register(subscriber);
        }
        return mBus;
    }

    /**
     *  注册
     * @param subscriber
     */
    public static EventBus register(Object subscriber){
        return register(subscriber,true);
    }

    /**
     *  取消注册
     * @param subscriber
     */
    public static void unregister(Object subscriber, boolean need){
        if(need){
            mBus.unregister(subscriber);
        }
    }

    /**
     *  取消注册
     * @param subscriber
     */
    public static void unregister(Object subscriber){
        unregister(subscriber,true);
    }

    /**
     *  post事件
     * @param event
     */
    public static void post(Object event){
        mBus.post(event);
    }

}
