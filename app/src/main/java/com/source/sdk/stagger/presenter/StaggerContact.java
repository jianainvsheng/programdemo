package com.source.sdk.stagger.presenter;

import com.source.sdk.base.BaseMvpNormalView;
import com.source.sdk.common.mvp.MvpPresenter;
import com.source.sdk.stagger.model.StaggerListModel;
import com.source.sdk.stagger.model.request.StaggerRequest;

public interface StaggerContact {

    interface StaggerView extends BaseMvpNormalView {

        void onStaggerListRefreshSuccess(StaggerListModel model);

        void onStaggerListRefreshFail(Throwable throwable);

        void onStaggerListRefreshEmpty();

        void onTaleListRefreshFinish(StaggerListModel model);



        void onStaggerListLoadSuccess(StaggerListModel model);

        void onStaggerListLoadFail(Throwable throwable);

        void onStaggerListLoadEmpty();

        void onStaggerListLoadFinish(StaggerListModel model);

    }

    interface StaggerPre extends MvpPresenter<StaggerView> {


        void onRefreshStaggerListRequest(StaggerRequest request, boolean isShowDialog);

        void onLoadStaggerListRequest(StaggerRequest request);

    }
}
