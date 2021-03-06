package com.source.sdk.main.holder;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.source.sdk.R;
import com.source.sdk.common.holder.holder.BaseHolder;
import com.source.sdk.main.modle.MainItemModel;
import com.source.sdk.prouter.PRouter;
import com.source.sdk.widget.fullmanager.FullyGridLayoutManager;
import com.source.sdk.widget.rating.StarBar;
import com.source.sdk.widget.slidelayout.SlideLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yangjian on 2018/9/5.
 */

public class MainItemtHolder extends BaseHolder<MainItemModel> implements View.OnClickListener{

    private TextView mUserName ,mUserCommetTime;

    private StarBar mUserStar;

    private SlideLayout mSlideLayout;

    private View mDeleteView;

    public MainItemtHolder(View contentView,SlideLayout.OnStateChangeListener listener) {
        super(contentView);
        FullyGridLayoutManager gridLayoutManager = new FullyGridLayoutManager(getContext(),3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        this.mUserName = contentView.findViewById(R.id.activity_comment_item_tale_name);
        this.mUserStar = contentView.findViewById(R.id.activity_comment_item_tale_starBar);
        this.mUserCommetTime = contentView.findViewById(R.id.activity_comment_item_tale_time);
        this.mUserName.setOnClickListener(this);
        this.mSlideLayout =  contentView.findViewById(R.id.slidelayout_test);
        this.mSlideLayout.setOnStateChangeListener(listener);
        this.mDeleteView = contentView.findViewById(R.id.item_menu);
        this.mDeleteView.setOnClickListener(this);
    }

    @Override
    public void setData(MainItemModel data) {
        super.setData(data);
//        setTextView(mUserCommetTime,data.comnttime);
        setTextView(mUserName,data.nikename);
        try{
            float starNum = Float.parseFloat(data.comntlv);
            mUserStar.setStarMark(starNum);
        }catch (Exception e){

        }

        String showTime = "";
        if (!TextUtils.isEmpty(data.comnttime)) {

            try {
                Long time = Long.parseLong(data.comnttime);

                showTime = transferLongToDate(time);
            } catch (Exception e) {

            }

        }
        setTextView(mUserCommetTime, showTime);

    }

    private String transferLongToDate(Long millSec) {

        return transferLongToDate("yyyy-MM-dd HH:mm:ss", millSec);

    }

    private String transferLongToDate(String dateFormat, Long millSec) {

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        Date date = new Date(millSec * 1000);

        return sdf.format(date);

    }

    private void setTextView(TextView textView , String text){

        if(!TextUtils.isEmpty(text)){

            textView.setVisibility(View.VISIBLE);
            textView.setText(text);
        }else{
            textView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {

        if(v == mDeleteView){

            Toast.makeText(getContext(),"删除" + getData().nikename + "  position : " + getPosition(),Toast.LENGTH_SHORT).show();
        }else if(v == mUserName){

            if(getPosition() == 0){
                PRouter.getInstance().navigation(getContentView(),"/source/PopupActivity");
            }else if(getPosition() == 1){
                PRouter.getInstance().navigation(getContentView(),"/source/BannerActivity");
            }else if(getPosition() == 2){
                PRouter.getInstance().navigation(getContentView(),"/source/LeveProcessActivity");
            }else if(getPosition() == 3){
                PRouter.getInstance().navigation(getContentView(),"/source/BezieSearchActivity");
            }else if(getPosition() == 4){
                PRouter.getInstance().navigation(getContentView(),"/source/AddCatActivity");
            }else if(getPosition() == 5){
                PRouter.getInstance().navigation(getContentView(),"/source/MagicIndicatorActivity");
            }else if(getPosition() == 6){
                PRouter.getInstance().navigation(getContentView(),"/source/StaggerActivity");
            }else if(getPosition() == 7){
                PRouter.getInstance().navigation(getContentView(),"/source/TwoLevelActivity");
            }
        }
    }
}
