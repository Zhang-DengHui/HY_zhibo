package com.example.hy_zhibo.fragments;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.hy_zhibo.R;
import com.example.hy_zhibo.base.BaseFragment;
import com.example.hy_zhibo.presenter.OwnPresenter;
import com.example.hy_zhibo.ui.LoginActivity;
import com.example.hy_zhibo.ui.SettingActivitiy;
import com.example.hy_zhibo.ui.UserDetailsActivity;
import com.example.hy_zhibo.utils.SpUtils;


import butterknife.BindView;
import butterknife.OnClick;

public class OwnFragment extends BaseFragment {

    public static final int REQUEST_LOGIN_CODE = 100;
    public static final int REQUEST_SETTING_CODE = 101;


    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.txt_login)
    TextView txtLogin;
    @BindView(R.id.layout_no_login)
    ConstraintLayout layoutNoLogin;
    @BindView(R.id.txt_nickname)
    TextView txtNickname;
    @BindView(R.id.txt_user_sign)
    TextView txtUserSign;
    @BindView(R.id.layout_login)
    ConstraintLayout layoutLogin;
    @BindView(R.id.layout_userinfo)
    ConstraintLayout layoutUserinfo;
    @BindView(R.id.txt_setting)
    TextView txtSetting;
    @BindView(R.id.img_details)
    ImageView imgDetails;

    @Override
    protected void initData() {

    }

    @Override
    protected OwnPresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        //判断用户是否登录来处理用户信息的显示
        updateLoginState();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_own;
    }

    /**
     * 刷新登录状态的页面显示
     */
    private void updateLoginState(){
        String username = SpUtils.getInstance().getString("username");
        if(!TextUtils.isEmpty(username)){
            layoutLogin.setVisibility(View.VISIBLE);
            layoutNoLogin.setVisibility(View.GONE);
        }else{
            layoutLogin.setVisibility(View.GONE);
            layoutNoLogin.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.txt_login,R.id.txt_setting,R.id.img_details})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.txt_login:
                gotoLogin();
                break;
            case R.id.txt_setting:
                gotoSetting();
                break;
            case R.id.img_details:
                gotoDetails();
                break;
        }
    }

    /**
     * 调整登录页面
     */
    private void gotoLogin(){
        Intent intent = new Intent(context, LoginActivity.class);
        startActivityForResult(intent,REQUEST_LOGIN_CODE);
    }

    /**
     * 调整设置页面
     */
    private void gotoSetting(){
        Intent intent = new Intent(context, SettingActivitiy.class);
        startActivityForResult(intent,REQUEST_LOGIN_CODE);
    }

    /**
     * 进入详情页面
     */
    private void gotoDetails(){
        Intent intent = new Intent(context, UserDetailsActivity.class);
        startActivityForResult(intent,REQUEST_LOGIN_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //登录成功进行界面刷新处理
        if(requestCode == REQUEST_LOGIN_CODE){
            updateUserInfo();
        }else if(requestCode == REQUEST_SETTING_CODE){
            updateLoginState();
        }
    }

    /**
     * 刷新用户数据
     */
    private void  updateUserInfo(){
        layoutLogin.setVisibility(View.VISIBLE);
        layoutNoLogin.setVisibility(View.GONE);
        //用户头像
        String headUrl = SpUtils.getInstance().getString("avater");
        if(!TextUtils.isEmpty(headUrl)){
            Glide.with(context).load(headUrl).into(imgHead);
        }
        //昵称
        String nickname = SpUtils.getInstance().getString("username");
        if(!TextUtils.isEmpty(nickname)){
            txtNickname.setText(nickname);
        }
        //签名
    }
}
