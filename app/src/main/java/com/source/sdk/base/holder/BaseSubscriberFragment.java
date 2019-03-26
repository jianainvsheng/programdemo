package com.source.sdk.base.holder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.source.sdk.base.BaseMvpNormalFragment;
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

public abstract class BaseSubscriberFragment<V extends BaseMvpNormalView, P extends MvpBasePresenter<V>> extends BaseMvpNormalFragment<V,P> implements IHolderManager, ISubscriber {

    private SubscriberContext mSubscriberContext;

    public BaseSubscriberFragment() {
        mSubscriberContext = new SubscriberContextHolder();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventManager.getDefault().registerObserver(getContext(), this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HolderUtils.onFindHolder(this,this);
    }

    @Override
    public void addSubscriberHolder(BaseSubscriberHolder<?> holder) {
        mSubscriberContext.onAttach(holder);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSubscriberContext != null) {
            mSubscriberContext.onDetachedFromContext(getContext());
        }
        EventManager.getDefault().removeObserver(getContext(), this);
    }


    @Override
    public SubscriberContext getContextHolder() {
        return mSubscriberContext;
    }
}
