package com.source.sdk.base.holder;

import android.os.Bundle;

import com.source.sdk.base.BaseMvpNormalActivity;
import com.source.sdk.base.BaseMvpNormalView;
import com.source.sdk.common.holder.eventmanger.EventManager;
import com.source.sdk.common.holder.eventmanger.internal.kernel.ISubscriber;
import com.source.sdk.common.holder.holdermanger.holder.BaseSubscriberHolder;
import com.source.sdk.common.holder.holdermanger.holder.HolderUtils;
import com.source.sdk.common.holder.holdermanger.holder.IHolderManager;
import com.source.sdk.common.holder.holdermanger.holder.context.SubscriberContext;
import com.source.sdk.common.holder.holdermanger.holder.impl.SubscriberContextHolder;
import com.source.sdk.common.mvp.MvpBasePresenter;

/**
 * @author Created by yangjian-ds3 on 2018/4/10.
 */

public abstract class BaseSubscriberActivity<V extends BaseMvpNormalView, P extends MvpBasePresenter<V>> extends BaseMvpNormalActivity<V,P> implements IHolderManager,ISubscriber {

    private SubscriberContext mSubscriberContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubscriberContext = new SubscriberContextHolder();
        EventManager.getDefault().registerObserver(this, this);
        setContentView(onCreateLayoutResId());
        HolderUtils.onFindHolder(this,this);
    }

    public abstract int onCreateLayoutResId();

    @Override
    public void addSubscriberHolder(BaseSubscriberHolder<?> holder){
        mSubscriberContext.onAttach(holder);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mSubscriberContext != null){
            mSubscriberContext.onDetachedFromContext(this);
        }
        EventManager.getDefault().removeObserver(this, this);
    }

    @Override
    public SubscriberContext getContextHolder() {
        return mSubscriberContext;
    }

}
