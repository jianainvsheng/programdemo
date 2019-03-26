package com.source.sdk.banneractivity.present;
import com.source.sdk.banneractivity.model.BannerModel;
import com.source.sdk.base.BaseMvpNormalView;
import com.source.sdk.common.mvp.MvpPresenter;

public interface BannerContact {

    interface BannerView extends BaseMvpNormalView {

        /**
         * 刷新成功
         * @param model
         */
        void onBannerSuccess(BannerModel model);

        /**
         *
         */
        void onBannerEmpty();

        /**
         * program列表刷新失败
         * @param throwable
         */
        void onBannerFail(Throwable throwable);

    }

    interface BannerPre extends MvpPresenter<BannerView> {
        /**
         * 请求
         */
        void onBannerhRequest();

    }

}
