package com.jmarkstar.carlist_core.presenter.module.cartype.manufacturer;

import com.jmarkstar.carlist_core.domain.model.ItemModel;
import com.jmarkstar.carlist_core.presenter.base.MvpView;
import com.jmarkstar.carlist_core.presenter.module.cartype.Paginable;
import java.util.List;

/**
 * Created by jmarkstar on 24/08/2017.
 */
public interface SelectManufacturerMvpView  extends Paginable, MvpView {

    void showManufacturerList(List<ItemModel> manufacturers);
}
