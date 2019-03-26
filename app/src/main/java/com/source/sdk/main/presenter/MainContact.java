package com.source.sdk.main.presenter;

import com.source.sdk.base.BaseMvpNormalView;
import com.source.sdk.common.mvp.MvpPresenter;
import com.source.sdk.main.modle.MainListModel;
import com.source.sdk.main.modle.request.MainRequest;

public interface MainContact {

    interface MainView extends BaseMvpNormalView {

        /**
         * 刷新成功
         * @param model
         */
        void onMainRefrshSuccess(MainListModel model);

        /**
         *
         */
        void onMainRefrshEmpty();

        /**
         * program列表刷新失败
         * @param throwable
         */
        void onMainRefrshFail(Throwable throwable);

    }

    interface MainPre extends MvpPresenter<MainView> {
        /**
         * 请求
         */
        void onMainRefurshRequest(MainRequest request, boolean isShowDialog);

    }

}
