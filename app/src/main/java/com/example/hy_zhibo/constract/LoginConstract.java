package com.example.hy_zhibo.constract;


import com.example.biyingxiangmu.base.IBaseView;
import com.example.biyingxiangmu.base.IPresenter;
import com.example.biyingxiangmu.bean.UserInfoBean;

public interface LoginConstract {

    interface View extends IBaseView {
        void loginReturn(UserInfoBean result);
    }

    interface Presenter extends IPresenter<View> {
        void login(String username, String password);
    }

}
