package com.source.sdk.application;

import android.app.Application;

import com.source.sdk.prouter.PRouter;

/**
 * Created by yangjian on 2019/3/29.
 */

public class SourceApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PRouter.getInstance().init();
    }
}
