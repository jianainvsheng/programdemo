package com.source.sdk.main.presenter;

import android.os.Handler;

import com.source.sdk.common.mvp.MvpBasePresenter;
import com.source.sdk.main.modle.MainItemModel;
import com.source.sdk.main.modle.MainListModel;
import com.source.sdk.main.modle.request.MainRequest;

import java.util.ArrayList;

public class MainPresenter extends MvpBasePresenter<MainContact.MainView> implements MainContact.MainPre {

    @Override
    public void onMainRefurshRequest(MainRequest request, boolean isShowDialog) {
        if (!isViewAttached())
            return;
        if (isShowDialog)
            getView().showLoadingDialog();

        //测试数据

        final MainListModel listModel = new MainListModel();
        listModel.list = new ArrayList<>();
        MainItemModel model = new MainItemModel();
        model.comntlv = "3";
        model.nikename = "测试popupwinow";
        model.comnttime = System.currentTimeMillis() + "";
        listModel.list.add(model);

        model = new MainItemModel();
        model.comntlv = "4";
        model.nikename = "测试Binner";
        model.comnttime = System.currentTimeMillis() + "";
        listModel.list.add(model);

        model = new MainItemModel();
        model.comntlv = "5";
        model.nikename = "测试进度条";
        model.comnttime = System.currentTimeMillis() + "";
        listModel.list.add(model);

        model = new MainItemModel();
        model.comntlv = "4";
        model.nikename = "测试贝塞尔搜索";
        model.comnttime = System.currentTimeMillis() + "";
        listModel.list.add(model);

        model = new MainItemModel();
        model.comntlv = "3";
        model.nikename = "测试添加购物车";
        model.comnttime = System.currentTimeMillis() + "";
        listModel.list.add(model);

        model = new MainItemModel();
        model.comntlv = "4.5";
        model.nikename = "测试MagicIndicator";
        model.comnttime = System.currentTimeMillis() + "";
        listModel.list.add(model);

        model = new MainItemModel();
        model.comntlv = "3.5";
        model.nikename = "测试瀑布流";
        model.comnttime = System.currentTimeMillis() + "";
        listModel.list.add(model);

        model = new MainItemModel();
        model.comntlv = "3.8";
        model.nikename = "下拉二楼层";
        model.comnttime = System.currentTimeMillis() + "";
        listModel.list.add(model);


        for(int i = 0 ; i < 20 ; i++){
            model = new MainItemModel();
            model.comntlv = i % 5 + ".5";
            model.nikename = "待测试" + i;
            model.comnttime = System.currentTimeMillis() + "";
            listModel.list.add(model);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isViewAttached())
                    return;
                getView().hideLoadingDialog();
                getView().onMainRefrshSuccess(listModel);
            }
        }, 1000);

    }
}
