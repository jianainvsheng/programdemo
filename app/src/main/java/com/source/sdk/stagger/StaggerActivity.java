package com.source.sdk.stagger;

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
import com.source.sdk.stagger.holder.StaggerHolder;
import com.source.sdk.stagger.model.StaggerListModel;
import com.source.sdk.stagger.model.request.StaggerRequest;
import com.source.sdk.stagger.presenter.StaggerContact;
import com.source.sdk.stagger.presenter.StaggerPresenter;

/**
 * Created by yangjian on 2019/3/18.
 */
@IHolder(
        holders = @IHolderInfo(holderClass = StaggerHolder.class,resId = R.id.widget_refreshLayout_layout)
)
@IActivity(value = "/source/StaggerActivity")
public class StaggerActivity extends BaseSubscriberActivity<StaggerContact.StaggerView,StaggerPresenter> implements StaggerContact.StaggerView{

    private StaggerRequest mRequest;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequest = new StaggerRequest();
        mRequest.curentPagger = 1;
        getPresenter().onRefreshStaggerListRequest(mRequest,true);
    }

    @Override
    public int onCreateLayoutResId() {
        return R.layout.widget_refresh_staggered_gridview;
    }

    @NonNull
    @Override
    public StaggerPresenter createPresenter() {
        return new StaggerPresenter();
    }

    @Override
    public void reload(View view) {

    }

    @Override
    public void onMessageEvent(BaseEvent baseEvent) {

        if(baseEvent.getFromClass() == StaggerHolder.class){

            if(StaggerHolder.EVENT_REFRESH_DATA == baseEvent.getEventType()){
                mRequest.curentPagger = 1;
                getPresenter().onRefreshStaggerListRequest(mRequest,false);

            }else if(StaggerHolder.EVENT_LOAD_DATA == baseEvent.getEventType()){
                mRequest.curentPagger = mRequest.curentPagger + 1;
                getPresenter().onLoadStaggerListRequest(mRequest);
            }
        }
    }

    @Override
    public void onStaggerListRefreshSuccess(StaggerListModel model) {

        BaseEvent.builder(this)
                .setData(model)
                .setFromClass(this.getClass())
                .setEventType(StaggerHolder.EVENT_REFRESH_DATA_FINISH)
                .sendEvent(this,StaggerHolder.class);
    }

    @Override
    public void onStaggerListRefreshFail(Throwable throwable) {

    }

    @Override
    public void onStaggerListRefreshEmpty() {

    }

    @Override
    public void onTaleListRefreshFinish(StaggerListModel model) {
        BaseEvent.builder(this)
                .setData(model)
                .setFromClass(this.getClass())
                .setEventType(StaggerHolder.EVENT_REFRESH_DATA_OVER)
                .sendEvent(this,StaggerHolder.class);
    }

    @Override
    public void onStaggerListLoadSuccess(StaggerListModel model) {

        BaseEvent.builder(this)
                .setData(model)
                .setFromClass(this.getClass())
                .setEventType(StaggerHolder.EVENT_LOAD_DATA_FINISH)
                .sendEvent(this,StaggerHolder.class);
    }

    @Override
    public void onStaggerListLoadFail(Throwable throwable) {

    }

    @Override
    public void onStaggerListLoadEmpty() {

    }

    @Override
    public void onStaggerListLoadFinish(StaggerListModel model) {

        BaseEvent.builder(this)
                .setData(model)
                .setFromClass(this.getClass())
                .setEventType(StaggerHolder.EVENT_LOAD_DATA_OVER)
                .sendEvent(this,StaggerHolder.class);
    }
}
