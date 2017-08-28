package com.jmarkstar.carlist_core.presenter.module.cartype;

import com.jmarkstar.carlist_core.domain.interactor.CarTypeDispatcher;
import com.jmarkstar.carlist_core.presenter.base.BasePresenter;
import com.jmarkstar.carlist_core.presenter.base.MvpView;
import javax.inject.Inject;

/**
 * Created by jmarkstar on 22/08/2017.
 */
public class CarTypePresenter<V extends MvpView>
        extends BasePresenter<V>
        implements CarTypeMvpPresenter<V>{

    @Inject CarTypeDispatcher mCarTypeDispatcher;

    @Inject CarTypePresenter(){}

    @Override public void doDeleteCacheFilters() {
        mCarTypeDispatcher.deleteCacheFilters();
    }
}
