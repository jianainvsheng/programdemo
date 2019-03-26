package com.source.sdk.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.source.sdk.R;
import com.source.sdk.base.holder.BaseSubscriberActivity;
import com.source.sdk.common.holder.annotation.IHolder;
import com.source.sdk.common.holder.annotation.internal.IHolderInfo;
import com.source.sdk.common.holder.eventmanger.internal.event.BaseEvent;
import com.source.sdk.main.holder.MainHolder;
import com.source.sdk.main.modle.MainListModel;
import com.source.sdk.main.modle.request.MainRequest;
import com.source.sdk.main.presenter.MainContact;
import com.source.sdk.main.presenter.MainPresenter;

@IHolder(
        holders = @IHolderInfo(holderClass = MainHolder.class,resId = R.id.main)
)
public class MainActivity extends BaseSubscriberActivity<MainContact.MainView,MainPresenter> implements MainContact.MainView {

    private MainRequest mRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequest = new MainRequest();
        getPresenter().onMainRefurshRequest(mRequest,true);
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public int onCreateLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void reload(View view) {

    }

    @Override
    public void onMessageEvent(BaseEvent baseEvent) {

        if(baseEvent.getFromClass() == MainHolder.class){

            if(baseEvent.getEventType() == MainHolder.EVENT_REFRESH_DATA){

                mRequest.curentPager = 1;
                getPresenter().onMainRefurshRequest(mRequest,true);
            }
        }
    }

    @Override
    public void onMainRefrshSuccess(MainListModel model) {

        BaseEvent.builder(this)
                .setData(model)
                .setEventType(MainHolder.EVENT_REFRESH_DATA_OVER)
                .sendEvent(this, MainHolder.class);
    }

    @Override
    public void onMainRefrshEmpty() {

    }

    @Override
    public void onMainRefrshFail(Throwable throwable) {

    }
}
