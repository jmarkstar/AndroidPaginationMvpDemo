package com.jmarkstar.carlist_core.presenter.module.cartype.maintype;

import com.jmarkstar.carlist_core.domain.model.ItemModel;
import com.jmarkstar.carlist_core.presenter.base.MvpView;
import com.jmarkstar.carlist_core.presenter.module.cartype.Paginable;
import java.util.List;

/**
 * Created by jmarkstar on 27/08/2017.
 */

public interface SelectMainTypeMvpView extends Paginable, MvpView{
    void showMainTypeList(List<ItemModel> mainTypes);
}
