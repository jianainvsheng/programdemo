package com.source.sdk.addcat.holder;

import android.view.View;
import android.widget.ListView;

import com.source.sdk.R;
import com.source.sdk.addcat.adapter.ListViewAdapter;
import com.source.sdk.addcat.addcat.data.TestData;
import com.source.sdk.common.holder.eventmanger.internal.event.BaseEvent;
import com.source.sdk.common.holder.holdermanger.holder.BaseSubscriberHolder;
import com.source.sdk.common.holder.holdermanger.holder.IHolderManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjian on 2019/3/15.
 */

public class AddCatHolder extends BaseSubscriberHolder<Object>{

    private ListView mListView;

    private ListViewAdapter mAdapter;

    public AddCatHolder(View contentView, IHolderManager manager) {
        super(contentView, manager);
        mListView = contentView.findViewById(R.id.listview);
        mAdapter = new ListViewAdapter(getContext(),contentView.findViewById(R.id.target_view));
        mListView.setAdapter(mAdapter);
        int[] resIds = new int[3];
        resIds[0] = R.drawable.image_1;
        resIds[1] = R.drawable.image_2;
        resIds[2] = R.drawable.image_3;
        List<TestData> datalist = new ArrayList<>();
        for (int i = 0 ; i < 30 ; i ++){

            TestData data = new TestData();
            data.name = "大家好，才是真的好" + i;
            data.resId = resIds[i % 3];
            datalist.add(data);
        }
        mAdapter.setData(datalist);
    }

    @Override
    public void builder(BaseEvent baseEvent, Object data) {


    }
}
