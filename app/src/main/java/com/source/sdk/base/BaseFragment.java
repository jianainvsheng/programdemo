package com.source.sdk.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.source.sdk.R;
import com.source.sdk.base.eventbus.Event;
import com.source.sdk.base.eventbus.EventUtils;
import com.source.sdk.common.dialog.commom.GNormalDialog;
import com.source.sdk.common.dialog.helper.usually.LoadingHelper;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private Dialog dialog;

    private static final String TAG = "BaseFragment";
    /**
     * 布局
     */
    public View mRootView;

    protected boolean mIsVisiable;

    /**
     * 上下文对象
     **/
    public Context mContext;
    /**
     * 获取状态栏的高度
     */

    public int mStatusBarHeight = -1;

    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isRegisterEventBus()) {
            EventUtils.register(this);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getResource(), container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        onBuildView(mRootView);
        getBundleArg();
        initView();
        setListeners();

        return mRootView;
    }

    public void onBuildView(View view){

    }

    @Override
    public void onResume() {
        super.onResume();
        if(this.isGMClickPv()) {
        }

    }
    @Override
    public void onPause() {
        super.onPause();
        if(this.isGMClickPv()) {
        }

    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(this.isGMClickPv()) {
        }

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(this.isGMClickPv()) {
        }

    }

    public boolean isGMClickPv() {
        return true;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        hideLoadingDialog();
        if (isRegisterEventBus()) {
            EventUtils.unregister(this);
        }
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        hideLoadingDialog();
        super.onDetach();
    }

    protected boolean isRegisterEventBus() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(Event event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(Event event) {

    }

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    protected void receiveStickyEvent(Event event) {

    }

    public abstract void initView();

    /**
     * 获取传递过来的bundle参数
     **/
    public void getBundleArg() {
    }

    public void setListeners() {
    }

    public abstract int getResource();

    /**
     * TextView
     *
     * @param id
     * @return
     */
    public TextView findTextView(int id) {
        return (TextView) mRootView.findViewById(id);
    }

    /**
     * Button
     *
     * @param id
     * @return
     */
    public Button findButton(int id) {
        return (Button) mRootView.findViewById(id);
    }

    /**
     * LinearLayout
     *
     * @param id
     * @return
     */
    public LinearLayout findLinearLayout(int id) {
        return (LinearLayout) mRootView.findViewById(id);
    }

    /**
     * RelativeLayout
     *
     * @param id
     * @return
     */
    public RelativeLayout findRelativeLayout(int id) {
        return (RelativeLayout) mRootView.findViewById(id);
    }

    /**
     * ImageView
     *
     * @param id
     * @return
     */
    public ImageView findImageView(int id) {
        return (ImageView) mRootView.findViewById(id);
    }

    /**
     * RatingBar
     *
     * @param id
     * @return
     */
    public RatingBar findRatingBar(int id) {
        return (RatingBar) mRootView.findViewById(id);
    }


    /**
     * 通过Id获取view
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T findViewByIdHelper(int id) {
        View targetView = null;
        if (null != mRootView) {
            targetView = mRootView.findViewById(id);
        }
        return targetView == null ? null : (T) targetView;
    }

    /**
     * 当前Fragment后退事件处理
     *
     * @return
     */
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /**
     * 主线程中调用
     * 显示加载数据对话框
     */
    public void showLoadingDialog() {
        if (isDetached() || getActivity()==null) {
            return;
        }

        if (dialog == null) {
            dialog = GNormalDialog.onCreateBuiler(getContext())
                    .setThemeStyleResId(R.style.dialog_style)
                    .setHelperClass(LoadingHelper.class)
                    .build();
            dialog.show();
        } else {
            if (!dialog.isShowing()) {
                dialog.show();
            }
        }
    }

    /**
     * 主线程中调用
     * 隐藏加载数据对话框
     */
    public void hideLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public Dialog getDialog() {
        return dialog;
    }
}
