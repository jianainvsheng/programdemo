package com.source.sdk.banneractivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.source.sdk.R;
import com.source.sdk.banneractivity.holder.BannerHolder;
import com.source.sdk.banneractivity.model.BannerModel;
import com.source.sdk.banneractivity.present.BannerContact;
import com.source.sdk.banneractivity.present.BannerPresenter;
import com.source.sdk.base.holder.BaseSubscriberActivity;
import com.source.sdk.common.holder.annotation.IHolder;
import com.source.sdk.common.holder.annotation.internal.IHolderInfo;
import com.source.sdk.common.holder.eventmanger.internal.event.BaseEvent;

/**
 * Created by yangjian on 2019/3/15.
 */
@IHolder(holders = @IHolderInfo(
        holderClass = BannerHolder.class,resId = R.id.activity_banner
))
public class BannerActivity extends BaseSubscriberActivity<BannerContact.BannerView,BannerPresenter> implements BannerContact.BannerView{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSystemBarBg(R.color.color_00000000);
        getPresenter().onBannerhRequest();
    }

    @Override
    public int onCreateLayoutResId() {
        return R.layout.activity_banner_layout;
    }

    @NonNull
    @Override
    public BannerPresenter createPresenter() {
        return new BannerPresenter();
    }

    @Override
    public void reload(View view) {

    }

    @Override
    public void onMessageEvent(BaseEvent baseEvent) {

    }

    @Override
    public void onBannerSuccess(BannerModel model) {

        BaseEvent.builder(this)
                .setData(model)
                .sendEvent(this,BannerHolder.class);
    }

    @Override
    public void onBannerEmpty() {

    }

    @Override
    public void onBannerFail(Throwable throwable) {

    }
}
