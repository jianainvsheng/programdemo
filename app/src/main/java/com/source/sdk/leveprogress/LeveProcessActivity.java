package com.source.sdk.leveprogress;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.source.sdk.R;
import com.source.sdk.annotaion.IActivity;
import com.source.sdk.base.holder.BaseSubscriberActivity;
import com.source.sdk.common.holder.annotation.IHolder;
import com.source.sdk.common.holder.annotation.internal.IHolderInfo;
import com.source.sdk.common.holder.eventmanger.internal.event.BaseEvent;
import com.source.sdk.leveprogress.holder.LeveProcessHolder;
import com.source.sdk.leveprogress.present.LevelContact;
import com.source.sdk.leveprogress.present.LevelPresenter;

@IHolder(holders = @IHolderInfo(

        resId = R.id.levelprocess_activity,holderClass = LeveProcessHolder.class
))
@IActivity(value = "/source/LeveProcessActivity")
public class LeveProcessActivity extends BaseSubscriberActivity<LevelContact.LevelView,LevelPresenter> implements LevelContact.LevelView{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().onLevelRequest(true);
     }

    @NonNull
    @Override
    public LevelPresenter createPresenter() {
        return new LevelPresenter();
    }

    @Override
    public int onCreateLayoutResId() {
        return R.layout.activity_levepocess;
    }

    @Override
    public void reload(View view) {

    }

    @Override
    public void onMessageEvent(BaseEvent baseEvent) {

    }

    @Override
    public void onLevelSuccess(Float process) {

        BaseEvent.builder(this)
                .setData(process)
                .sendEvent(this,LeveProcessHolder.class);
    }

    @Override
    public void onLevelEmpty() {

    }

    @Override
    public void onLevelFail(Throwable throwable) {

    }
}
