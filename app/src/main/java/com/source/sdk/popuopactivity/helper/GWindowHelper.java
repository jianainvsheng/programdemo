package com.source.sdk.popuopactivity.helper;

import android.animation.ValueAnimator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.source.sdk.R;
import com.source.sdk.widget.window.builder.BasePopupBuilder;
import com.source.sdk.widget.window.helper.BasePopupHelper;
import com.source.sdk.widget.window.popup.BasePopupWindow;

/**
 * Created by yangjian on 2019/3/6.
 */

public class GWindowHelper extends BasePopupHelper<BasePopupBuilder> {

    private View contentView;

    private View okButton;

    private View cancleButton;

    ValueAnimator animator;

    private View matchView;

    @Override
    public int onCreateViewLayoutId() {
        return R.layout.window_popup;
    }

    @Override
    public void onBuilder(View view, BasePopupBuilder builder, final BasePopupWindow decorWindow) {

        this.contentView = view.findViewById(R.id.content_view);
        this.matchView = view.findViewById(R.id.match_view);
        this.okButton = view.findViewById(R.id.ok);
        this.cancleButton = view.findViewById(R.id.cancle);
        this.cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decorWindow.hidePopupWindow();
            }
        });
//        this.matchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
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

        final int height = contentView.getMeasuredHeight();
        final int startY = - height;
        final int xoff = contentView.getMeasuredHeight();
        anim(contentView,startY,xoff,500);
    }

    private void finishAnim(){

        if(animator != null){
            animator.cancel();
            animator.removeAllUpdateListeners();
            animator = null;
        }
    }
    @Override
    public int hidePopupWindowDuration() {
        final int xoff = - contentView.getMeasuredHeight();
        anim(contentView,(int)contentView.getY() , xoff , 500);
        return 500;
    }

    @Override
    public boolean canNotHidePopupWindow() {
        if(animator != null && animator.isRunning()){
            return true;
        }
        return false;
    }

    @Override
    public boolean canNotShowPopupWindow() {
        if(animator != null && animator.isRunning()){
            return true;
        }
        return false;
    }

    private void anim(final View view ,final int startY,final int xoff,int duration){

        if(animator != null && animator.isRunning()){
            return;
        }
        finishAnim();
        //final int startY = (int) (- height + contentView.getY());
        animator = ValueAnimator.ofInt(100);
        animator.setDuration(500);
        view.setY(startY);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int value = (int) animation.getAnimatedValue();
                int y = (int)((float)value / 100 * xoff) + startY;
                view.setY(y);
            }
        });
        animator.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //判断坐标是否落在view的下方
        if(getContentView() == null){
            return super.onTouchEvent(event);
        }
        final RelativeLayout.LayoutParams params =
                (RelativeLayout.LayoutParams) getContentView().getLayoutParams();
        int bottom = params.topMargin + getContentView().findViewById(R.id.content_view).getMeasuredHeight();
        int pointY = (int) event.getY();
        if(pointY > bottom){
            getBuilderData().getGPopupWindow().hidePopupWindow();
            return true;
        }
        return super.onTouchEvent(event);
    }
}
