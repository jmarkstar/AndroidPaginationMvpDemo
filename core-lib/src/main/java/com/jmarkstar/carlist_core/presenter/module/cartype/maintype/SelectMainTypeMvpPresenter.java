package com.jmarkstar.carlist_core.presenter.module.cartype.maintype;

import com.jmarkstar.carlist_core.presenter.base.MvpPresenter;

/**
 * Created by jmarkstar on 27/08/2017.
 */
public interface SelectMainTypeMvpPresenter<V extends SelectMainTypeMvpView>
    extends MvpPresenter<V> {
    void doGetMoreMainTypes(String manufacturer);
    void doGetMainTypes(String manufacturer);
    boolean isLastPage();
    boolean isLoading();
}
