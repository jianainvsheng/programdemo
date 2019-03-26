package com.source.sdk.main.holder.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.source.sdk.R;
import com.source.sdk.common.holder.holder.adapter.BaseListViewAdapter;
import com.source.sdk.main.holder.MainItemtHolder;
import com.source.sdk.main.modle.MainItemModel;
import com.source.sdk.widget.slidelayout.SlideLayout;

/**
 * Created by yangjian on 2018/9/5.
 */

public class MainAdapter extends BaseListViewAdapter<MainItemModel,MainItemtHolder> implements SlideLayout.OnStateChangeListener{

    private SlideLayout mSlideLayout;

    @Override
    public MainItemtHolder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.slide_list_item,parent,false
        );
        MainItemtHolder itemtHolder = new MainItemtHolder(view,this);
        return itemtHolder;
    }

    @Override
    public void onBindViewHolder(MainItemtHolder holder, int position) {

        holder.setData(getData().get(position));
    }

    @Override
    public void onClose(SlideLayout slideLayout) {
        mSlideLayout = null;
    }

    @Override
    public void onDown(SlideLayout layout) {
        if (mSlideLayout != null && layout != mSlideLayout){
            mSlideLayout.closeMenu();
        }
    }

    @Override
    public void onOpen(SlideLayout layout) {
        mSlideLayout = layout;
    }
}
