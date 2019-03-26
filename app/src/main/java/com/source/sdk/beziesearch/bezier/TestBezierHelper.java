package com.source.sdk.beziesearch.bezier;

import android.graphics.PointF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.source.sdk.R;
import com.source.sdk.beziesearch.bezier.value.TestBezierValue;
import com.source.sdk.widget.bezie.BaseBezierHelper;

/**
 * Created by yangjian on 2018/10/15.
 */

public class TestBezierHelper extends BaseBezierHelper<View,TestBezierValue> {

    @Override
    public TestBezierValue onCreateBezieValue(ViewGroup viewGroup, PointF startF) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.psearch_topsearch_item_layout, viewGroup,false
        );
        view.setVisibility(View.VISIBLE);
//        final TextView textView = new TextView(viewGroup.getContext());
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//        );
//        textView.setLayoutParams(params);
//        textView.setVisibility(View.VISIBLE);
//        textView.setTextSize(12);
        TestBezierValue value = new TestBezierValue(view);
        return value;
    }

}
