package com.source.sdk.popuopactivity.helper;

import android.view.View;

import com.source.sdk.R;
import com.source.sdk.widget.window.builder.BasePopupBuilder;
import com.source.sdk.widget.window.helper.BasePopupHelper;
import com.source.sdk.widget.window.popup.BasePopupWindow;

/**
 * Created by yangjian on 2019/3/6.
 */

public class GWindowTopRightHelper extends BasePopupHelper<BasePopupBuilder> {


    @Override
    public int onCreateViewLayoutId() {
        return R.layout.activity_popup_top_right;
    }

    @Override
    public void onBuilder(View view, BasePopupBuilder builder, final BasePopupWindow decorWindow) {

    }

    @Override
    public int postVectorY(BasePopupWindow decorWindow,View longitudinalView) {

        return 0;
    }

    @Override
    public int postVectorX(BasePopupWindow decorWindow,View longitudinalView) {
        return 0;
    }


    @Override
    public void showPopupWindow(BasePopupWindow decorWindow) {


    }

    @Override
    public int hidePopupWindowDuration() {

        return 0;
    }

    @Override
    public boolean canNotHidePopupWindow() {
        return false;
    }

    @Override
    public boolean canNotShowPopupWindow() {
        return false;
    }

}
