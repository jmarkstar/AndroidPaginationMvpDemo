package com.jmarkstar.carlist_core.presenter.base;

/**
 * Created by jmarkstar on 24/08/2017.
 */

public interface MvpPresenter<V extends MvpView>{
    void attachView(V view);
    void detachView();
}
