package com.source.sdk.beziesearch;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.source.sdk.R;
import com.source.sdk.base.BaseMvpNormalView;
import com.source.sdk.base.holder.BaseSubscriberActivity;
import com.source.sdk.beziesearch.holder.BezieSearchHolder;
import com.source.sdk.common.holder.annotation.IHolder;
import com.source.sdk.common.holder.annotation.internal.IHolderInfo;
import com.source.sdk.common.holder.eventmanger.internal.event.BaseEvent;
import com.source.sdk.common.mvp.MvpBasePresenter;

@IHolder(holders = @IHolderInfo(

        resId = R.id.activity_beziesearch,holderClass = BezieSearchHolder.class
))
public class BezieSearchActivity extends BaseSubscriberActivity<BaseMvpNormalView,MvpBasePresenter<BaseMvpNormalView>> implements BaseMvpNormalView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSystemBarBg(R.color.color_00000000);
     }

    @NonNull
    @Override
    public MvpBasePresenter<BaseMvpNormalView> createPresenter() {
        return new MvpBasePresenter<>();
    }


    @Override
    public int onCreateLayoutResId() {
        return R.layout.activity_beziesearch;
    }

    @Override
    public void reload(View view) {

    }

    @Override
    public void onMessageEvent(BaseEvent baseEvent) {

    }
}
