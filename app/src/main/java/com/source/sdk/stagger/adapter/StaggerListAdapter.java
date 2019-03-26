package com.source.sdk.stagger.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.source.sdk.R;
import com.source.sdk.common.holder.holder.adapter.BaseListViewAdapter;
import com.source.sdk.stagger.holder.StatggerListItemHolder;
import com.source.sdk.stagger.model.StaggerListItemModel;
import com.source.sdk.widget.stagger.util.DynamicHeightImageView;

import java.util.Random;

/**
 * @author Created by yangjian-ds3 on 2018/4/12.
 */

public class StaggerListAdapter extends BaseListViewAdapter<StaggerListItemModel,StatggerListItemHolder> {

    private Class<?> mTargetClass;

    private final Random mRandom;

    private final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();

    public StaggerListAdapter(Class<?> targetClass){

        this.mTargetClass = targetClass;
        mRandom = new Random();
    }

    @Override
    public StatggerListItemHolder onCreateViewHolder(ViewGroup parent) {

        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.activity_stagger_item,parent,false);
        StatggerListItemHolder holder = new StatggerListItemHolder(itemView,mTargetClass);
        return holder;
    }

    @Override
    public void onBindViewHolder(StatggerListItemHolder holder, int position) {

        final double positionHeight = getPositionRatio(getData().get(position),position);
        final DynamicHeightImageView itemView = holder.getLayoutView();
        holder.getContentView().requestFocus();
        itemView.setHeightRatio(positionHeight);
        holder.setData(getData().get(position));
    }

    private double getPositionRatio(StaggerListItemModel model, final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
        if (ratio == 0) {
            ratio = getRandomHeightRatio(model.imgwidth,model.imgheight);
            if(ratio == 0d){
                ratio = getRandomHeightRatio();
            }
            sPositionHeightRatios.append(position, ratio);
        }
        return ratio;
    }

    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0) + 1.5; // height will be 1.5 - 2.0 the width
    }

    private double getRandomHeightRatio(int imgwidth,int imgheight){

        if(imgwidth != 0 && imgheight != 0){

            return ((double) imgheight / imgwidth) ;
        }
        return 0d;
    }

}
