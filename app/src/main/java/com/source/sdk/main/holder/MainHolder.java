package com.source.sdk.main.holder;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.source.sdk.R;
import com.source.sdk.common.holder.eventmanger.internal.event.BaseEvent;
import com.source.sdk.common.holder.holdermanger.holder.BaseSubscriberHolder;
import com.source.sdk.common.holder.holdermanger.holder.IHolderManager;
import com.source.sdk.main.holder.adapter.MainAdapter;
import com.source.sdk.main.modle.MainListModel;
import com.source.sdk.widget.smartlayout.SmartRefreshLayout;
import com.source.sdk.widget.smartlayout.api.RefreshLayout;
import com.source.sdk.widget.smartlayout.listener.OnLoadMoreListener;
import com.source.sdk.widget.smartlayout.listener.OnRefreshListener;

/**
 * Created by yangjian on 2018/9/5.
 */

public class MainHolder extends BaseSubscriberHolder<MainListModel> implements OnLoadMoreListener,OnRefreshListener {

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

    private SmartRefreshLayout mRefreshLayout;

    private ListView mCommentListView;

    private MainAdapter mCommentAdapter;

    public MainHolder(View contentView, IHolderManager manager) {
        super(contentView, manager);
        this.mRefreshLayout = contentView.findViewById(R.id.widget_refreshLayout_layout);
        this.mCommentListView = contentView.findViewById(R.id.widget_listview_layout);
        this.mCommentAdapter = new MainAdapter();
        this.mCommentListView.setAdapter(this.mCommentAdapter);
        this.mRefreshLayout.setEnableClipHeaderWhenFixedBehind(false);
        this.mRefreshLayout.setOnRefreshListener(this);
        this.mRefreshLayout.setOnLoadMoreListener(this);
        this.mRefreshLayout.setEnableRefresh(true);
        this.mRefreshLayout.setEnableLoadMore(true);
        this.mRefreshLayout.setEnableOverScrollBounce(false);
        this.mRefreshLayout.setEnableOverScrollDrag(false);
    }

    @Override
    public void builder(BaseEvent event, MainListModel data) {

        if(event.getEventType() == EVENT_REFRESH_DATA_FINISH){
            stopScrolly(mCommentListView);
            mCommentListView.setSelection(0);
            mCommentListView.scrollTo(0,0);
            mRefreshLayout.setNoMoreData(false);
            mRefreshLayout.finishRefresh(true);
            mCommentAdapter.setData(data.list);

        }else if (event.getEventType() == EVENT_LOAD_DATA_FINISH){
            mRefreshLayout.finishLoadMore(true);
            mCommentAdapter.addData(data.list);
        }else if (event.getEventType() == EVENT_LOAD_DATA_OVER){
            mCommentAdapter.addData(data.list);
            mRefreshLayout.finishLoadMoreWithNoMoreData();
        } else if(event.getEventType() == EVENT_REFRESH_DATA_OVER){
            stopScrolly(mCommentListView);
            mCommentListView.setSelection(0);
            mCommentListView.scrollTo(0,0);
            mRefreshLayout.finishRefresh(true);
            mCommentAdapter.setData(data.list);
            mRefreshLayout.finishLoadMoreWithNoMoreData();
        }else if(event.getEventType() == EVENT_CLEAR_DATA_ALL){
            if(mCommentAdapter != null){
                mCommentAdapter.clearData();
            }
        }else if(event.getEventType() == EVENT_REFRESH_DATA_FAIL){
            if(mCommentAdapter != null){
                mRefreshLayout.finishRefresh(false);
            }
        }else if(event.getEventType() == EVENT_LOAD_DATA_FAIL){

            mRefreshLayout.finishLoadMore(false);
        }else if(event.getEventType() == EVENT_REFRESH_DATA_EMPTY){

            if(mCommentAdapter != null){
                mRefreshLayout.finishRefresh(true);
            }

        }else if(event.getEventType() == EVENT_LOAD_DATA_EMPTY){
            if(mCommentAdapter != null){
                mRefreshLayout.finishLoadMore(true);
            }
            mRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    public void onMessageEvent(BaseEvent event) {
        super.onMessageEvent(event);
    }

    public void stopScrolly(ViewGroup viewGroup){

        viewGroup.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 0, 0, 0));
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {

        stopScrolly(mCommentListView);
        BaseEvent.builder(getContext())
                .setEventType(EVENT_LOAD_DATA)
                .setFromClass(this.getClass())
                .sendEvent(getContext(), getTargetClass());
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {

        BaseEvent.builder(getContext())
                .setEventType(EVENT_REFRESH_DATA)
                .setFromClass(this.getClass())
                .sendEvent(getContext(), getTargetClass());
    }
}
