package com.source.sdk.banneractivity.holder;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.source.sdk.R;
import com.source.sdk.banneractivity.adapter.SampleAdapter;
import com.source.sdk.banneractivity.loader.BannerImageLoad;
import com.source.sdk.banneractivity.model.BannerModel;
import com.source.sdk.banneractivity.model.item.BannerItemModel;
import com.source.sdk.common.holder.eventmanger.internal.event.BaseEvent;
import com.source.sdk.common.holder.holdermanger.holder.BaseSubscriberHolder;
import com.source.sdk.common.holder.holdermanger.holder.IHolderManager;
import com.source.sdk.widget.banner.Banner;
import com.source.sdk.widget.banner.BannerConfig;
import com.source.sdk.widget.banner.listener.OnBannerListener;
import com.source.sdk.widget.banner.transformer.AccordionTransformer;
import com.source.sdk.widget.banner.transformer.BackgroundToForegroundTransformer;
import com.source.sdk.widget.banner.transformer.CubeInTransformer;
import com.source.sdk.widget.banner.transformer.CubeOutTransformer;
import com.source.sdk.widget.banner.transformer.DefaultTransformer;
import com.source.sdk.widget.banner.transformer.DepthPageTransformer;
import com.source.sdk.widget.banner.transformer.FlipHorizontalTransformer;
import com.source.sdk.widget.banner.transformer.FlipVerticalTransformer;
import com.source.sdk.widget.banner.transformer.ForegroundToBackgroundTransformer;
import com.source.sdk.widget.banner.transformer.RotateDownTransformer;
import com.source.sdk.widget.banner.transformer.RotateUpTransformer;
import com.source.sdk.widget.banner.transformer.ScaleInOutTransformer;
import com.source.sdk.widget.banner.transformer.StackTransformer;
import com.source.sdk.widget.banner.transformer.TabletTransformer;
import com.source.sdk.widget.banner.transformer.ZoomInTransformer;
import com.source.sdk.widget.banner.transformer.ZoomOutSlideTransformer;
import com.source.sdk.widget.banner.transformer.ZoomOutTranformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjian on 2019/3/15.
 */

public class BannerBottomHolder extends BaseSubscriberHolder<Object>implements AdapterView.OnItemClickListener {

    private ListView listView;

    List<Class<? extends ViewPager.PageTransformer>> transformers=new ArrayList<>();

    public static int EVENT_TRANSFORMER_CHANGE = 0;

    public BannerBottomHolder(View contentView, IHolderManager manager) {
        super(contentView, manager);
        this.listView = contentView.findViewById(R.id.list);
        String[] data = getContext().getResources().getStringArray(R.array.anim);
        listView.setAdapter(new SampleAdapter(getContext(), data));
        initData();
        listView.setOnItemClickListener(this);
    }

    @Override
    public void builder(BaseEvent baseEvent, Object data) {


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        BaseEvent.builder(getContext())
                .setEventType(EVENT_TRANSFORMER_CHANGE)
                .setData(transformers.get(position))
                .setFromClass(this.getClass())
                .sendEvent(getContext(),BannerHolder.class);
    }

    public void initData(){
        transformers.add(DefaultTransformer.class);
        transformers.add(AccordionTransformer.class);
        transformers.add(BackgroundToForegroundTransformer.class);
        transformers.add(ForegroundToBackgroundTransformer.class);
        transformers.add(CubeInTransformer.class);//兼容问题，慎用
        transformers.add(CubeOutTransformer.class);
        transformers.add(DepthPageTransformer.class);
        transformers.add(FlipHorizontalTransformer.class);
        transformers.add(FlipVerticalTransformer.class);
        transformers.add(RotateDownTransformer.class);
        transformers.add(RotateUpTransformer.class);
        transformers.add(ScaleInOutTransformer.class);
        transformers.add(StackTransformer.class);
        transformers.add(TabletTransformer.class);
        transformers.add(ZoomInTransformer.class);
        transformers.add(ZoomOutTranformer.class);
        transformers.add(ZoomOutSlideTransformer.class);
    }
}
