package com.source.sdk.base.tack;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * activity 栈管理
 *
 * @author liuyang
 */
public class ActivityTack {


    /**
     * 维护列表
     */
    public List<Activity> activityList = new ArrayList();

    /**
     * 提前创建
     */
    private static ActivityTack tack = new ActivityTack();
    private Activity currentActivity;

    /**
     * 获取单例
     * @return
     */
    public static ActivityTack getInstanse() {
        return tack;
    }

    /**
     * 私有化禁止创建
     */
    private ActivityTack() {

    }

    /**
     * 添加
     * @param activity
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 设置当前可见Activity
     * @param activity
     */
    public void setCurrentActivity(Activity activity) {
        currentActivity = activity;
    }

    /**
     * 获取当前可见Activity
     * @return
     */
    public Activity getCurrentActivity() {
        return currentActivity;
    }

    /**
     * 删除
     * @param activity
     */
    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 完全退出
     */
    public void exit() {
        while (!activityList.isEmpty()) {
            activityList.get(activityList.size() - 1).finish();
        }
        try {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {

        }
    }

    /**
     * 根据类名删除
     * @param className
     * @return
     */
    public Activity removeAllTask(String className){
        List<Activity> activityListOther = new ArrayList();
        Activity acResult = null;
        for (Activity ac : activityList) {
            if (!ac.getClass().getName().equals(className)) {
                activityListOther.add(ac);
            }else{
                acResult = ac;
            }
        }
        for (Activity ac : activityListOther) {
            ac.finish();
        }
        return acResult;

    }

    /**
     * 处理只保留相应activity的个数
     * @param c  activity名
     * @param count 最大activity个数
     */
    public void handleActivityRemain(Class<?> c, int count) {
        List<Activity> activityListRemain = new ArrayList();
        for (Activity ac : activityList) {
            if (c.isInstance(ac)) {
                activityListRemain.add(ac);
            }
        }
        if (activityListRemain.size() > count) {
            activityListRemain.get(0).finish();
        }
    }

    /**
     * 根据class name获取activity
     * @param name
     * @return
     */
    public Activity getActivityByClassName(String name) {
        for (Activity ac : activityList) {
            if (ac.getClass().getName().indexOf(name) >= 0) {
                return ac;
            }
        }
        return null;
    }

    /**
     * 根据class获取activity
     * @param cs
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Activity getActivityByClass(Class cs) {
        for (Activity ac : activityList) {
            if (ac.getClass().equals(cs)) {
                return ac;
            }
        }
        return null;
    }

    /**
     * 弹出activity
     * @param activity
     */
    public void popActivity(Activity activity) {
        removeActivity(activity);
        activity.finish();
    }


    /**
     * 弹出activity到
     * @param cs
     */
    @SuppressWarnings("rawtypes")
    public void popUntilActivity(Class... cs) {
        List<Activity> list = new ArrayList();
        for (int i = activityList.size() - 1; i >= 0; i--) {
            Activity ac = activityList.get(i);
            boolean isTop = false;
            for (int j = 0; j < cs.length; j++) {
                if (ac.getClass().equals(cs[j])) {
                    isTop = true;
                    break;
                }
            }
            if (!isTop) {
                list.add(ac);
            } else break;
        }
        for (Iterator<Activity> iterator = list.iterator(); iterator.hasNext(); ) {
            Activity activity = iterator.next();
            popActivity(activity);
        }
    }
}
