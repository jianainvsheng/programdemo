package com.source.sdk.leveprogress.present;
import com.source.sdk.base.BaseMvpNormalView;
import com.source.sdk.common.mvp.MvpPresenter;

public interface LevelContact {

    interface LevelView extends BaseMvpNormalView {

        /**
         * 刷新成功
         * @param
         */
        void onLevelSuccess(Float process);

        /**
         *
         */
        void onLevelEmpty();

        /**
         * program列表刷新失败
         * @param throwable
         */
        void onLevelFail(Throwable throwable);

    }

    interface LevelPre extends MvpPresenter<LevelView> {
        /**
         * 请求
         */
        void onLevelRequest(boolean isShowDialog);

    }

}
