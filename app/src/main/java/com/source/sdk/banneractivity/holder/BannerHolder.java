package com.source.sdk.banneractivity.holder;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.source.sdk.R;
import com.source.sdk.banneractivity.loader.BannerImageLoad;
import com.source.sdk.banneractivity.model.BannerModel;
import com.source.sdk.banneractivity.model.item.BannerItemModel;
import com.source.sdk.common.holder.eventmanger.internal.event.BaseEvent;
import com.source.sdk.common.holder.holdermanger.holder.BaseSubscriberHolder;
import com.source.sdk.common.holder.holdermanger.holder.IHolderManager;
import com.source.sdk.widget.banner.Banner;
import com.source.sdk.widget.banner.BannerConfig;
import com.source.sdk.widget.banner.listener.OnBannerListener;

import java.util.List;

/**
 * Created by yangjian on 2019/3/15.
 */

public class BannerHolder extends BaseSubscriberHolder<BannerModel>implements OnBannerListener {

    private Banner mBanner;

    public BannerHolder(View contentView, IHolderManager manager) {
        super(contentView, manager);
        this.mBanner = contentView.findViewById(R.id.hot_top_banner);
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
    }

    @Override
    public void builder(BaseEvent baseEvent, BannerModel bannerModel) {

        List<BannerItemModel> datalist = bannerModel.list;
        if (datalist != null && datalist.size() > 0) {
            mBanner.setImages(datalist)
                    .setImageLoader(new BannerImageLoad())
                    .setOnBannerListener(this)
                    .start();
        }
    }

    @Override
    public void OnBannerClick(int i) {
        //点击
        Toast.makeText(getContext(),"position : " + i,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMessageEvent(BaseEvent event) {
        super.onMessageEvent(event);
        if(event.getFromClass() == BannerBottomHolder.class){

            if(event.getEventType() == BannerBottomHolder.EVENT_TRANSFORMER_CHANGE){

                Class<? extends ViewPager.PageTransformer> tran = (Class<? extends ViewPager.PageTransformer>) event.getData();
                mBanner.setBannerAnimation(tran);
            }
        }
    }
}
