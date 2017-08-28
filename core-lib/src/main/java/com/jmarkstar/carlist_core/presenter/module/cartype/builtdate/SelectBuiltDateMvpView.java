package com.jmarkstar.carlist_core.presenter.module.cartype.builtdate;

import com.jmarkstar.carlist_core.domain.model.ItemModel;
import com.jmarkstar.carlist_core.presenter.base.MvpView;
import java.util.List;

/**
 * Created by jmarkstar on 27/08/2017.
 */
public interface SelectBuiltDateMvpView extends MvpView {
    void showBuiltDatesList(List<ItemModel> builtDates);
}
