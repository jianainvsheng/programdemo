package com.source.sdk.banneractivity.loader;

import android.content.Context;
import android.widget.ImageView;

import com.source.sdk.banneractivity.model.item.BannerItemModel;
import com.source.sdk.widget.banner.loader.ImageLoader;

public class BannerImageLoad extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        BannerItemModel itemModel = (BannerItemModel) path;
//        GImage.imageLoader().with(context)
//                .load(itemModel.image)
//                .into(imageView);
        imageView.setImageResource(itemModel.resId);
    }
}
