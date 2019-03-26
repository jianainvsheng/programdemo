package com.source.sdk.banneractivity.present;

import com.source.sdk.R;
import com.source.sdk.banneractivity.model.BannerModel;
import com.source.sdk.banneractivity.model.item.BannerItemModel;
import com.source.sdk.common.mvp.MvpBasePresenter;

import java.util.ArrayList;

public class BannerPresenter extends MvpBasePresenter<BannerContact.BannerView> implements BannerContact.BannerPre {

    @Override
    public void onBannerhRequest() {

        BannerModel listModel = new BannerModel();
        listModel.list = new ArrayList<>();
        BannerItemModel itemModel = new BannerItemModel();
        itemModel.resId = R.drawable.png_1;
        listModel.list.add(itemModel);

        itemModel = new BannerItemModel();
        itemModel.resId = R.drawable.png_2;
        listModel.list.add(itemModel);

        itemModel = new BannerItemModel();
        itemModel.resId = R.drawable.png_3;
        listModel.list.add(itemModel);
        getView().onBannerSuccess(listModel);
    }
}
