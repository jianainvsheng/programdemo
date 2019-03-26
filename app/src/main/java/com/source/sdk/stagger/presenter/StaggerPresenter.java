package com.source.sdk.stagger.presenter;

import android.os.Handler;

import com.source.sdk.R;
import com.source.sdk.common.mvp.MvpBasePresenter;
import com.source.sdk.stagger.model.StaggerListItemModel;
import com.source.sdk.stagger.model.StaggerListModel;
import com.source.sdk.stagger.model.request.StaggerRequest;

import java.util.ArrayList;

public class StaggerPresenter extends MvpBasePresenter<StaggerContact.StaggerView> implements StaggerContact.StaggerPre {

    private int[] resId = {

            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3
    };

    @Override
    public void onRefreshStaggerListRequest(final StaggerRequest request, boolean isShowDialog) {

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
                StaggerListModel model = new StaggerListModel();
                model.list = new ArrayList<>();
                for(int i = 0 ; i < 10 ; i++){
                    StaggerListItemModel itemModel = new StaggerListItemModel();
                    itemModel.resId = resId[i % 3];
                    model.list.add(itemModel);
                }

                if(request.curentPagger == request.maxPagger){

                    getView().onTaleListRefreshFinish(model);
                }else {
                    getView().onStaggerListRefreshSuccess(model);
                }
            }
        }, 1000);

    }

    @Override
    public void onLoadStaggerListRequest(final StaggerRequest request) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(!isViewAttached()){
                    return;
                }
                StaggerListModel model = new StaggerListModel();
                model.list = new ArrayList<>();

                for(int i = 0 ; i < 15 ; i++){
                    StaggerListItemModel itemModel = new StaggerListItemModel();
                    itemModel.resId = resId[i % 3];
                    model.list.add(itemModel);
                }

                if(request.curentPagger == request.maxPagger){

                    getView().onStaggerListLoadFinish(model);
                }else {
                    getView().onStaggerListLoadSuccess(model);
                }
            }
        }, 1000);
    }
}
