package com.source.sdk.popuopactivity.holder;

import android.view.View;

import com.source.sdk.R;
import com.source.sdk.common.holder.eventmanger.internal.event.BaseEvent;
import com.source.sdk.common.holder.holdermanger.holder.BaseSubscriberHolder;
import com.source.sdk.common.holder.holdermanger.holder.IHolderManager;
import com.source.sdk.popuopactivity.helper.GWindowCenterLevelNumberHelper;
import com.source.sdk.popuopactivity.helper.GWindowCenterNumberHelper;
import com.source.sdk.popuopactivity.helper.GWindowHelper;
import com.source.sdk.popuopactivity.helper.GWindowNumberLeftHelper;
import com.source.sdk.popuopactivity.helper.GWindowRightNumberHelper;
import com.source.sdk.popuopactivity.helper.GWindowTopRightHelper;
import com.source.sdk.widget.window.popup.gwindow.GPopupWindow;
import com.source.sdk.widget.window.windowenum.WindowEnum;

/**
 * Created by yangjian on 2019/3/15.
 */

public class PopupHolder extends BaseSubscriberHolder<String> implements View.OnClickListener{

    GPopupWindow gPopupWindow = null;

    GPopupWindow lPopupWindow = null;


    public PopupHolder(View contentView, IHolderManager manager) {
        super(contentView, manager);
        contentView.findViewById(R.id.popup).setOnClickListener(this);
        contentView.findViewById(R.id.bottom).setOnClickListener(this);
        contentView.findViewById(R.id.bottom_right).setOnClickListener(this);
        contentView.findViewById(R.id.center_view).setOnClickListener(this);
        contentView.findViewById(R.id.center).setOnClickListener(this);
        contentView.findViewById(R.id.popup_top_right).setOnClickListener(this);
    }

    @Override
    public void builder(BaseEvent baseEvent, String s) {


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.popup){

            if(gPopupWindow == null){
                gPopupWindow = GPopupWindow.createPopupWindow(getContext(), GWindowHelper.class)
                        .setWindowEnum(WindowEnum.WINDOW_CENTER)
                        .setLevel(1)
                        .showPopupView(view);
            }else{
                if(gPopupWindow.isPopupWindowShow()){

                    gPopupWindow.hidePopupWindow();
                }else{
                    gPopupWindow.showPopupWindow(view);
                }
            }
        }else if(view.getId() == R.id.bottom){

            GPopupWindow.createPopupWindow(getContext(), GWindowRightNumberHelper.class)
                    .setWindowEnum(WindowEnum.WINDOW_RIGHT)
                    .setOffY(- view.getMeasuredHeight())
                    .setOutsideClickHide(true)
                    .showPopupView(view);
        }else if(view.getId() == R.id.popup_top_right){
            GPopupWindow.createPopupWindow(getContext(), GWindowTopRightHelper.class)
                    .setWindowEnum(WindowEnum.WINDOW_RIGHT)
                    .setOffY(20)
                    .setOffX(-30)
                    .setOutsideClickHide(true)
                    .showPopupView(view);

        }else if(view.getId() == R.id.bottom_right){

            GPopupWindow.createPopupWindow(getContext(), GWindowNumberLeftHelper.class)
                    .setWindowEnum(WindowEnum.WINDOW_LEFT)
                    .setOffY(- view.getMeasuredHeight())
                    .showPopupView(view);
        }else if(view.getId() == R.id.center){
            GPopupWindow.createPopupWindow(getContext(), GWindowCenterNumberHelper.class)
                    .setWindowEnum(WindowEnum.WINDOW_CENTER)
                    .setOffY(- view.getMeasuredHeight())
                    .showPopupView(view);
        }else if(view.getId() == R.id.center_view){

            if(lPopupWindow == null){
                lPopupWindow = GPopupWindow.createPopupWindow(getContext(), GWindowCenterLevelNumberHelper.class)
                        .setWindowEnum(WindowEnum.WINDOW_CENTER)
                        .setLevel(2)
                        .setOffY(- view.getMeasuredHeight())
                        .showPopupView(view);
            }else{

                if(lPopupWindow.isPopupWindowShow()){
                    lPopupWindow.hidePopupWindow();
                }else{
                    lPopupWindow.showPopupWindow(view);
                }
            }
        }
    }
}
