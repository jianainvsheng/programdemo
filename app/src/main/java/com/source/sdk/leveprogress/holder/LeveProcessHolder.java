package com.source.sdk.leveprogress.holder;

import android.graphics.Color;
import android.view.View;

import com.source.sdk.R;
import com.source.sdk.common.holder.eventmanger.internal.event.BaseEvent;
import com.source.sdk.common.holder.holdermanger.holder.BaseSubscriberHolder;
import com.source.sdk.common.holder.holdermanger.holder.IHolderManager;
import com.source.sdk.widget.leve.LevelProgressBar;

/**
 * Created by yangjian on 2019/3/15.
 */

public class LeveProcessHolder extends BaseSubscriberHolder<Float> implements View.OnClickListener{

    private LevelProgressBar mProgressBar;

    public LeveProcessHolder(View contentView, IHolderManager manager) {
        super(contentView, manager);
        this.mProgressBar = contentView.findViewById(R.id.user_progress_bar);
        this.mProgressBar.setProgress(Color.parseColor("#FF9055"),Color.parseColor("#FD4366"));
        this.mProgressBar.setLevels(1000);
    }

    @Override
    public void builder(BaseEvent baseEvent, Float integer) {

        int process = (int) (integer * 1000);
        this.mProgressBar.setCurrentLevel(process);
        this.mProgressBar.setAnimMaxTime(1000);
    }

    @Override
    public void onClick(View view) {


    }
}
