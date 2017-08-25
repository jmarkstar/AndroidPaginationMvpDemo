package com.jmarkstar.carlist_core.presenter.module.cartype.manufacturer;

import com.jmarkstar.carlist_core.presenter.base.MvpPresenter;

/**
 * Created by jmarkstar on 24/08/2017.
 */

public interface SelectManufacturerMvpPresenter<V extends SelectManufacturerMvpView>
    extends MvpPresenter<V>{
    void doUpManufacturerList();
    void doDownManufacturerList();
}
