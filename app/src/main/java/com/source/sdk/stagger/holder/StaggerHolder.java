package com.source.sdk.stagger.holder;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.source.sdk.R;
import com.source.sdk.common.empty.EmptyViewBox;
import com.source.sdk.common.holder.eventmanger.internal.event.BaseEvent;
import com.source.sdk.common.holder.holdermanger.holder.BaseSubscriberHolder;
import com.source.sdk.common.holder.holdermanger.holder.IHolderManager;
import com.source.sdk.stagger.adapter.StaggerListAdapter;
import com.source.sdk.stagger.boundary.StaggerBoundary;
import com.source.sdk.stagger.model.StaggerListModel;
import com.source.sdk.widget.smartlayout.SmartRefreshLayout;
import com.source.sdk.widget.smartlayout.api.RefreshLayout;
import com.source.sdk.widget.smartlayout.listener.OnLoadMoreListener;
import com.source.sdk.widget.smartlayout.listener.OnRefreshListener;
import com.source.sdk.widget.stagger.StaggeredGridView;

/**
 * Created by yangjian on 2018/8/26.
 */

public class StaggerHolder extends BaseSubscriberHolder<StaggerListModel> implements OnRefreshListener, OnLoadMoreListener {

    /**
     * 刷新数据
     */
    public static final int EVENT_REFRESH_DATA = 0;

    /**
     * 加载数据
     */
    public static final int EVENT_LOAD_DATA = 1;

    /**
     * 刷新完成
     */
    public static final int EVENT_REFRESH_DATA_FINISH = 2;


    /**
     * 加载完所有数据了
     */
    public static final int EVENT_REFRESH_DATA_OVER = 6;

    /**
     * 加载完成数据
     */
    public static final int EVENT_LOAD_DATA_FINISH = 3;

    /**
     * 加载完所有数据了
     */
    public static final int EVENT_LOAD_DATA_OVER = 4;

    /**
     * 加载完成数据
     */
    public static final int EVENT_LOAD_DATA_FAIL = 5;


    /**
     * 清除完数据
     */
    public static final int EVENT_CLEAR_DATA_ALL = 10;


    /**
     * 刷新数据失败
     */
    public static final int EVENT_REFRESH_DATA_FAIL = 11;


    /**
     * 刷新数据但是为空
     */
    public static final int EVENT_REFRESH_DATA_EMPTY = 12;

    /**
     * 加载数据但是为空
     */
    public static final int EVENT_LOAD_DATA_EMPTY = 13;


    /**
     * 无网络
     */

    public static final int EVENT_SHOW_NO_NET = 14;

    /**
     * 无数据
     */
    public static final int EVENT_SHOW_NO_DATA = 15;

    /**
     * 定位
     */
    public static final int EVENT_SHOW_NO_LOCAL = 16;

    /**
     * 正常显示
     */
    public static final int EVENT_SHOW_SUCCESS = 17;

    private SmartRefreshLayout mRefreshLayout;

    private StaggeredGridView mGridView;

    private StaggerListAdapter mHomeTaleAdapter;

    private EmptyViewBox mStatusLayoutManager;

    public StaggerHolder(View contentView, IHolderManager manager) {

        super(contentView, manager);
        this.mRefreshLayout = contentView.findViewById(R.id.widget_refreshLayout_layout);
        this.mGridView = contentView.findViewById(R.id.widget_staggered_grid_view);
        this.mHomeTaleAdapter = new StaggerListAdapter(getTargetClass());
        this.mGridView.setAdapter(mHomeTaleAdapter);
        //this.mRefreshLayout.setEnableClipFooterWhenFixedBehind(false);
        this.mRefreshLayout.setEnableClipHeaderWhenFixedBehind(false);
        this.mRefreshLayout.setOnRefreshListener(this);
        this.mRefreshLayout.setOnLoadMoreListener(this);
        this.mRefreshLayout.setEnableRefresh(true);
        this.mRefreshLayout.setEnableLoadMore(true);
        this.mRefreshLayout.setEnableOverScrollBounce(false);
        this.mRefreshLayout.setEnableOverScrollDrag(false);
        this.mRefreshLayout.setScrollBoundaryDecider(new StaggerBoundary(mGridView));
        initEmptyView(this.mGridView);
    }

    @Override
    public void builder(BaseEvent event, StaggerListModel data) {

        mRefreshLayout.setEnableLoadMore(true);
        if (event.getEventType() == EVENT_REFRESH_DATA_FINISH) {
            stopScrolly(mGridView);
            //mGridView.setSelection(0);
            //mGridView.scrollTo(0,0);
            mHomeTaleAdapter.setData(null);
            mRefreshLayout.setNoMoreData(false);
            mRefreshLayout.finishRefresh(true);
            mHomeTaleAdapter.setData(data.list);

        } else if (event.getEventType() == EVENT_LOAD_DATA_FINISH) {
            mRefreshLayout.finishLoadMore(true);
            mHomeTaleAdapter.addData(data.list);
        } else if (event.getEventType() == EVENT_LOAD_DATA_OVER) {
            mHomeTaleAdapter.addData(data.list);
            mRefreshLayout.finishLoadMoreWithNoMoreData();
        } else if (event.getEventType() == EVENT_REFRESH_DATA_OVER) {
            stopScrolly(mGridView);
            // mGridView.setSelection(0);
            // mGridView.scrollTo(0,0);
            mHomeTaleAdapter.setData(null);
            mRefreshLayout.finishRefresh(true);
            mHomeTaleAdapter.setData(data.list);
            mRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    public void initEmptyView(View view) {

        mStatusLayoutManager = new EmptyViewBox(getContext(), view);
        mStatusLayoutManager.setOnEmptyClickListener(new EmptyViewBox.OnEmptyClickListener() {
            @Override
            public void reload(View view) {

            }
        });
    }

    @Override
    public void onMessageEvent(BaseEvent event) {
        super.onMessageEvent(event);
        if (getPosition() != event.getPosition()) {
            return;
        }
        if (event.getEventType() == EVENT_SHOW_NO_NET) {

            mRefreshLayout.setEnableLoadMore(false);
            mRefreshLayout.finishRefresh(false);
            mStatusLayoutManager.showNoNetConnLayout();

        } else if (event.getEventType() == EVENT_SHOW_NO_DATA) {

            mRefreshLayout.setEnableLoadMore(false);
            mRefreshLayout.finishRefresh(false);
            mStatusLayoutManager.showNullDataLayout();
        } else if (event.getEventType() == EVENT_SHOW_NO_LOCAL) {

            mRefreshLayout.setEnableLoadMore(false);
            mRefreshLayout.finishRefresh(false);
            mStatusLayoutManager.showNullLocalLayout();
        } else if (event.getEventType() == EVENT_SHOW_SUCCESS) {

            mStatusLayoutManager.hideAll();
        }
        if (event.getEventType() == EVENT_CLEAR_DATA_ALL) {
            if (mHomeTaleAdapter != null) {
                mHomeTaleAdapter.clearData();
            }
        } else if (event.getEventType() == EVENT_REFRESH_DATA_FAIL) {
            if (mHomeTaleAdapter != null) {
                mRefreshLayout.finishRefresh(false);
            }
            if (mHomeTaleAdapter.getCount() <= 0) {
                mRefreshLayout.setEnableLoadMore(false);
                mStatusLayoutManager.showNullDataLayout();
            }
        } else if (event.getEventType() == EVENT_LOAD_DATA_FAIL) {

            mRefreshLayout.finishLoadMore(false);
        } else if (event.getEventType() == EVENT_REFRESH_DATA_EMPTY) {

            if (mHomeTaleAdapter != null) {
                mRefreshLayout.finishRefresh(true);
            }
            if (mHomeTaleAdapter.getCount() <= 0) {

                mRefreshLayout.setEnableLoadMore(false);
                mStatusLayoutManager.showNullDataLayout();
            }

        } else if (event.getEventType() == EVENT_LOAD_DATA_EMPTY) {
            if (mHomeTaleAdapter != null) {
                mRefreshLayout.finishLoadMore(true);
            }
            mRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    public void stopScrolly(ViewGroup viewGroup) {

        viewGroup.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 0, 0, 0));
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {

        stopScrolly(mGridView);
        BaseEvent.builder(getContext())
                .setEventType(EVENT_LOAD_DATA)
                .setFromClass(this.getClass())
                .sendEvent(getContext(), getTargetClass());
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {

        stopScrolly(mGridView);
        BaseEvent.builder(getContext())
                .setEventType(EVENT_REFRESH_DATA)
                .setFromClass(this.getClass())
                .sendEvent(getContext(), getTargetClass());
    }

    public StaggerListAdapter getAdapter() {

        return mHomeTaleAdapter;
    }
}
