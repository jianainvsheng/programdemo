package com.source.sdk.beziesearch.bezier;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.source.sdk.R;
import com.source.sdk.widget.search.array.base.adapter.BaseArrayAdapter;
import com.source.sdk.widget.search.array.base.holder.BaseArrayHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjian on 2018/8/6.
 */

public class SearchLayoutAdapter extends BaseArrayAdapter<SearchModel, SearchItemHolder> {

    private boolean isDeleteOver = true;

    private OnDeleteListener mL;

    @Override
    public SearchItemHolder onCreateViewHolder(ViewGroup parent, int type) {
        SearchItemHolder holder;
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.psearch_topsearch_item_layout, parent,false
        );
        holder = new SearchItemHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchItemHolder holder, int position) {

        if (holder != null) {
            holder.setData(getDatas().get(position));
            if(!isDeleteOver){
                holder.getContentView().setVisibility(View.VISIBLE);
            }
        }
    }

    public void setDataList(String... datas) {

        if (datas == null || datas.length < 0) {
            super.setData(null);
        } else {
            List<SearchModel> beans = new ArrayList();
            for (String s : datas) {
                if(!TextUtils.isEmpty(s)){
                    SearchModel model = new SearchModel();
                    model.mText = s;
                    model.setType(0);
                    beans.add(model);
                }
            }
            super.setData(beans);
        }
    }

    public void setOnDeleteListener(OnDeleteListener l){

        this.mL = l;
    }

    public void notifyDataSetChanged() {

        notifyDataSetChanged(null);
    }

    @Override
    public void notifyDataSetChanged(int... positions) {
        super.notifyDataSetChanged(positions);
    }

    public void addData(String... slist) {

        if (slist == null || slist.length <= 0) {
            return;
        }
        SearchModel[] beans = new SearchModel[slist.length];
        int index = 0;
        for (String s : slist) {
            SearchModel model = new SearchModel();
            model.mText = s;
            model.setType(0);
            beans[index] = model;
        }
        super.addData(beans);
    }

    @Override
    public void onDeleteView(final SearchItemHolder holder) {

        if(holder.getPosition() == 0 && getItemCount() <= 1){
            Toast.makeText(holder.getContext(),"只有一个不能删除", Toast.LENGTH_SHORT).show();
            if(mL != null){
                mL.onDeleteListener(holder,true);
            }
            return;
        }
        if (isDeleteOver) {
            isDeleteOver = false;
            if (holder.getPosition() == getDatas().size() - 1) {
                super.onDeleteView(holder);
                isDeleteOver = true;
                if(mL != null){
                    mL.onDeleteListener(holder,false);
                }
            } else {
                final View view = holder.getContentView();
                final ViewGroup parentView = getTargetLayout();
                int left = view.getLeft();
                final int dex = holder.getPosition() + 1;
                Rect globeRect = new Rect();
                view.getLocalVisibleRect(globeRect);
                if(view.getMeasuredWidth() > globeRect.width()){
                    left = view.getMeasuredWidth() - globeRect.width() + left;
                }
                Rect viewRect = new Rect();
                view.getGlobalVisibleRect(viewRect);
                final int finalLeft = left;
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
                valueAnimator.setDuration(300);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float animatedValue = (float) valueAnimator.getAnimatedValue();
                        view.setAlpha(0.0f);
                        if (animatedValue == 1.0f) {
                            SearchLayoutAdapter.super.onDeleteView(holder);
                            isDeleteOver = true;
                            if(mL != null){
                                mL.onDeleteListener(holder,false);
                            }
                        }else{
                            moveItemLeft(dex,finalLeft,animatedValue,parentView);
                        }
                    }
                });
                valueAnimator.start();
            }
        }
    }


    private void moveItemLeft(int dex,int left ,float a ,ViewGroup viewGroup) {
        int size = 0;
        for(int i = dex ; i < viewGroup.getChildCount() ; i ++){
            View view = viewGroup.getChildAt(i);
            if(i == dex){
                size = (int)((float) (view.getLeft() - left) * a);
            }
            view.layout(view.getLeft() - size,view.getTop(),view.getRight() - size,view.getBottom());
        }
    }

    @Override
    public int onAninmotaionDelayed() {
        return 600;
    }

    public static interface OnDeleteListener{

        void onDeleteListener(final BaseArrayHolder<SearchModel> holder, boolean isOnlyOneAndFrist);
    }
}

