package com.jmarkstar.carlist_core.domain.repository.manager;

import com.jmarkstar.carlist_core.domain.interactor.Action;
import com.jmarkstar.carlist_core.domain.model.PaginationInfoModel;
import com.jmarkstar.carlist_core.domain.model.PaginationItemsModel;

/**
 * Created by jmarkstar on 27/08/2017.
 */

public interface MainTypeDataManager {
    void getMainTypes(Boolean fromServer, String manufacturer, Integer page, Integer pageSize,
                      String waKey, Action.Callback<PaginationItemsModel> callback);
    void getPaginationInfo(String manufacturer, final Action.Callback<PaginationInfoModel> callback);
}
