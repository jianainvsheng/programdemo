package com.source.sdk.twoLevel.holder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.source.sdk.R;
import com.source.sdk.common.holder.eventmanger.internal.event.BaseEvent;
import com.source.sdk.common.holder.holdermanger.holder.BaseSubscriberHolder;
import com.source.sdk.common.holder.holdermanger.holder.IHolderManager;
import com.source.sdk.twoLevel.utils.StatusBarUtil;
import com.source.sdk.widget.smartlayout.api.OnTwoLevelListener;
import com.source.sdk.widget.smartlayout.api.RefreshHeader;
import com.source.sdk.widget.smartlayout.api.RefreshLayout;
import com.source.sdk.widget.smartlayout.header.TwoLevelHeader;
import com.source.sdk.widget.smartlayout.listener.SimpleMultiPurposeListener;

/**
 * Created by yangjian on 2019/3/15.
 */

public class TwoLevelHolder extends BaseSubscriberHolder<Object>{

    public TwoLevelHolder(View contentView, IHolderManager manager) {
        super(contentView, manager);

        final View floor = contentView.findViewById(R.id.secondfloor);
        final Toolbar toolbar = contentView.findViewById(R.id.toolbar);
        final TwoLevelHeader header = contentView.findViewById(R.id.header);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });

        final RefreshLayout refreshLayout = contentView.findViewById(R.id.refreshLayout);
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
            }
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Toast.makeText(getContext(),"触发刷新事件",Toast.LENGTH_SHORT).show();
                refreshLayout.finishRefresh(2000);
            }
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                toolbar.setAlpha(1 - Math.min(percent, 1));
                floor.setTranslationY(Math.min(offset - floor.getHeight() + toolbar.getHeight(), refreshLayout.getLayout().getHeight() - floor.getHeight()));
            }
//            @Override
//            public void onHeaderPulling(@NonNull RefreshHeader header, float percent, int offset, int bottomHeight, int maxDragHeight) {
//                toolbar.setAlpha(1 - Math.min(percent, 1));
//                floor.setTranslationY(Math.min(offset - floor.getHeight() + toolbar.getHeight(), mRefreshLayout.getLayout().getHeight() - floor.getHeight()));
//            }
//            @Override
//            public void onHeaderReleasing(@NonNull RefreshHeader header, float percent, int offset, int bottomHeight, int maxDragHeight) {
//                toolbar.setAlpha(1 - Math.min(percent, 1));
//                floor.setTranslationY(Math.min(offset - floor.getHeight() + toolbar.getHeight(), mRefreshLayout.getLayout().getHeight() - floor.getHeight()));
//            }
        });

        /*
         * 主动打开二楼
         */
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                header.openTwoLevel(true);
            }
        });

        header.setOnTwoLevelListener(new OnTwoLevelListener() {
            @Override
            public boolean onTwoLevel(@NonNull RefreshLayout refreshLayout) {
                Toast.makeText(getContext(),"触发二楼事件",Toast.LENGTH_SHORT).show();
                getContentView().findViewById(R.id.secondfloor_content).animate().alpha(1).setDuration(2000);
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        header.finishTwoLevel();
                        getContentView().findViewById(R.id.secondfloor_content).animate().alpha(0).setDuration(1000);
                    }
                },5000);
                return true;//true 将会展开二楼状态 false 关闭刷新
            }
        });

//        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout mRefreshLayout) {
//                Toast.makeText(getContext(),"触发刷新事件",Toast.LENGTH_SHORT).show();
//                mRefreshLayout.finishRefresh(2000);
//            }
//        });

        //状态栏透明和间距处理
        StatusBarUtil.immersive((Activity) getContext());
        StatusBarUtil.setMargin(getContext(),  getContentView().findViewById(R.id.classics));
        StatusBarUtil.setPaddingSmart(getContext(), getContentView().findViewById(R.id.toolbar));
        StatusBarUtil.setPaddingSmart(getContext(), getContentView().findViewById(R.id.contentPanel));
    }

    @Override
    public void builder(BaseEvent baseEvent, Object data) {


    }
}
