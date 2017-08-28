package com.jmarkstar.carlist_core.presenter.module.cartype.builtdate;

import com.jmarkstar.carlist_core.presenter.base.MvpPresenter;

/**
 * Created by jmarkstar on 27/08/2017.
 */
public interface SelectBuiltDateMvpPresenter<V extends SelectBuiltDateMvpView>
        extends MvpPresenter<V> {
    void doGetBuiltDates(String manufacturer, String mainType);
}