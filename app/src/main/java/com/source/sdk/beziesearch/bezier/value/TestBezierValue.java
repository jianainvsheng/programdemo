package com.source.sdk.beziesearch.bezier.value;

import android.graphics.PointF;
import android.view.View;

import com.source.sdk.widget.bezie.value.BaseBezierValue;

/**
 * Created by yangjian on 2018/10/15.
 */

public class TestBezierValue extends BaseBezierValue<View> {

    public TestBezierValue(View addView) {
        super(addView);

    }

    @Override
    public void onBezieValue(PointF pointF) {

        getAddView().setX(pointF.x);
        getAddView().setY(pointF.y);
    }
}
