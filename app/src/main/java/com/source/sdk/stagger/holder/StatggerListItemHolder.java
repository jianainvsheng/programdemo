package com.source.sdk.stagger.holder;

import android.view.View;
import android.widget.Toast;

import com.source.sdk.R;
import com.source.sdk.common.holder.holder.BaseHolder;
import com.source.sdk.stagger.model.StaggerListItemModel;
import com.source.sdk.widget.stagger.util.DynamicHeightImageView;

/**
 * Created by yangjian on 2018/8/26.
 */

public class StatggerListItemHolder extends BaseHolder<StaggerListItemModel> implements View.OnClickListener {

    private Class<?> mTargetClass;

    private View mLayoutView;

    private DynamicHeightImageView mBgImageView;

    public StatggerListItemHolder(View contentView, Class<?> targetClass) {
        super(contentView);
        this.mTargetClass = targetClass;
        this.getContentView().setFocusable(true);
        this.getContentView().setFocusableInTouchMode(true);
        contentView.findViewById(R.id.fragment_home_stagger_layout).setOnClickListener(this);
        this.mBgImageView = contentView.findViewById(R.id.fragment_home_stagger_item_bg);
        this.mBgImageView.setOnClickListener(this);
    }

    @Override
    public void setData(StaggerListItemModel data) {
        super.setData(data);
    //    mBgImageView.setImageResource(data.resId);
    }

    public DynamicHeightImageView getLayoutView() {

        return mBgImageView;
    }

    @Override
    public void onClick(View view) {

        if(view == mBgImageView){
            Toast.makeText(getContext(),"dynamic_position : " + getPosition(),Toast.LENGTH_SHORT).show();
        }
    }
}
