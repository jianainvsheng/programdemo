package com.source.sdk.beziesearch.holder;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.source.sdk.R;
import com.source.sdk.beziesearch.bezier.SearchLayoutAdapter;
import com.source.sdk.beziesearch.bezier.TestBezierHelper;
import com.source.sdk.common.holder.eventmanger.internal.event.BaseEvent;
import com.source.sdk.common.holder.holdermanger.holder.BaseSubscriberHolder;
import com.source.sdk.common.holder.holdermanger.holder.IHolderManager;
import com.source.sdk.widget.bezie.listener.OnBezieValuerListener;
import com.source.sdk.widget.search.SearchScrollView;

/**
 * Created by yangjian on 2019/3/15.
 */

public class BezieSearchHolder extends BaseSubscriberHolder<Object>{


    private TextView oneText,twoText,thressText,fourText;

    private TestBezierHelper helper ;

    private SearchScrollView searchScrollView;

    private SearchLayoutAdapter mAdapter;

    private String text = "";

    public BezieSearchHolder(View contentView, IHolderManager manager) {
        super(contentView, manager);
        searchScrollView = contentView.findViewById(R.id.home_top_searchlayout);
        mAdapter = new SearchLayoutAdapter();
        searchScrollView.setAdapter(mAdapter);
        oneText = contentView.findViewById(R.id.test_one);
        twoText = contentView.findViewById(R.id.test_two);
        thressText = contentView.findViewById(R.id.test_thress);
        fourText = contentView.findViewById(R.id.test_four);
        helper = new TestBezierHelper();
        helper.setBezieValueListener(new OnBezieValuerListener<View>() {
            @Override
            public void onStartValue(View view, float x, float y) {

                ((TextView)view.findViewById(R.id.search_item)).setText(text);
            }

            @Override
            public void onBezieValue(View view, float x, float y) {

            }

            @Override
            public void onEndValue(View view, float x, float y) {
                ((TextView)view.findViewById(R.id.search_item)).setText("");
                view.setVisibility(View.GONE);
                searchScrollView.getLastView().setVisibility(View.VISIBLE);
            }
        });

        oneText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(helper == null){
//                    helper = new TestBezierHelper();
//                 //   helper.setBezieValue(oneText,twoText);
//                }
//                else{
//                    helper.startAnimator();
//                }
                text = oneText.getText().toString();
                if(mAdapter.getItemCount() >= 2){

                    mAdapter.setDataList(mAdapter.getDatas().get(0).mText,
                            text);
                }else{
                    mAdapter.addData(text);
                }
                oneText.post(new Runnable() {
                    @Override
                    public void run() {
                        helper.setBezieValue((Activity) getContext(),oneText,searchScrollView.getLastView());
                    }
                });

            }
        });

        twoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text = twoText.getText().toString();
                if(mAdapter.getItemCount() >= 2){

                    mAdapter.setDataList(mAdapter.getDatas().get(0).mText,
                            text);
                }else{
                    mAdapter.addData(text);
                }
                twoText.post(new Runnable() {
                    @Override
                    public void run() {
                        helper.setBezieValue((Activity) getContext(),twoText,searchScrollView.getLastView());
                    }
                });
            }
        });

        thressText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = thressText.getText().toString();
                if(mAdapter.getItemCount() >= 2){

                    mAdapter.setDataList(mAdapter.getDatas().get(0).mText,
                            text);
                }else{
                    mAdapter.addData(text);
                }
                thressText.post(new Runnable() {
                    @Override
                    public void run() {
                        helper.setBezieValue((Activity) getContext(),thressText,searchScrollView.getLastView());
                    }
                });
            }
        });

        fourText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = fourText.getText().toString();
                if(mAdapter.getItemCount() >= 2){

                    mAdapter.setDataList(mAdapter.getDatas().get(0).mText,
                            text);
                }else{
                    mAdapter.addData(text);
                }
                fourText.post(new Runnable() {
                    @Override
                    public void run() {
                        helper.setBezieValue((Activity) getContext(),fourText,searchScrollView.getLastView());
                    }
                });
            }
        });

    }

    @Override
    public void builder(BaseEvent baseEvent, Object data) {


    }
}
