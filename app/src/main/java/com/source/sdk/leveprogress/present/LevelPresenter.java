package com.source.sdk.leveprogress.present;
import android.os.Handler;

import com.source.sdk.common.mvp.MvpBasePresenter;

public class LevelPresenter extends MvpBasePresenter<LevelContact.LevelView> implements LevelContact.LevelPre {

    @Override
    public void onLevelRequest(boolean isShowDialog) {

        if(isShowDialog){

            getView().showLoadingDialog();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(!isViewAttached()){

                    return;
                }

                getView().hideLoadingDialog();
                getView().onLevelSuccess(0.5f);
            }
        }, 1000);
    }
}
