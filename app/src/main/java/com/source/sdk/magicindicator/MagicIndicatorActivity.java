package com.source.sdk.magicindicator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.source.sdk.R;
import com.source.sdk.annotaion.IActivity;
import com.source.sdk.base.BaseMvpNormalView;
import com.source.sdk.base.holder.BaseSubscriberActivity;
import com.source.sdk.common.holder.annotation.IHolder;
import com.source.sdk.common.holder.annotation.internal.IHolderInfo;
import com.source.sdk.common.holder.eventmanger.internal.event.BaseEvent;
import com.source.sdk.common.mvp.MvpBasePresenter;
import com.source.sdk.magicindicator.holder.MagicHolder;

/**
 * Created by yangjian on 2019/3/18.
 */
@IHolder(holders = @IHolderInfo(

        resId = R.id.activity_magic_indicator,holderClass = MagicHolder.class
))
@IActivity(value = "/source/MagicIndicatorActivity")
public class MagicIndicatorActivity extends BaseSubscriberActivity<BaseMvpNormalView,MvpBasePresenter<BaseMvpNormalView>> implements BaseMvpNormalView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public MvpBasePresenter<BaseMvpNormalView> createPresenter() {
        return new MvpBasePresenter<>();
    }


    @Override
    public int onCreateLayoutResId() {
        return R.layout.activity_fragment_container_example_layout;
    }

    @Override
    public void reload(View view) {

    }

    @Override
    public void onMessageEvent(BaseEvent baseEvent) {

    }
}
