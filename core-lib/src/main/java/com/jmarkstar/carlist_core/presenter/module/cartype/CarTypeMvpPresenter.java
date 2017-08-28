package com.jmarkstar.carlist_core.presenter.module.cartype;

import com.jmarkstar.carlist_core.presenter.base.MvpPresenter;
import com.jmarkstar.carlist_core.presenter.base.MvpView;

/**
 * Created by jmarkstar on 27/08/2017.
 */

public interface CarTypeMvpPresenter<V extends MvpView> extends MvpPresenter<V> {
    void doDeleteCacheFilters();
}
