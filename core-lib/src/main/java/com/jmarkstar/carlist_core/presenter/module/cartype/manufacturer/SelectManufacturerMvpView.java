package com.jmarkstar.carlist_core.presenter.module.cartype.manufacturer;

import com.jmarkstar.carlist_core.presenter.base.MvpView;
import java.util.HashMap;

/**
 * Created by jmarkstar on 24/08/2017.
 */

public interface SelectManufacturerMvpView  extends MvpView {
    void loadDownProgress();
    void loadUpProgress();
    void showManufacturerList(HashMap<String, String> manufacturers);
}
