package com.example.hy_zhibo.base;

public interface IPresenter <V extends IBaseView>{

    void attachView(V view);

    void detachView();

}
