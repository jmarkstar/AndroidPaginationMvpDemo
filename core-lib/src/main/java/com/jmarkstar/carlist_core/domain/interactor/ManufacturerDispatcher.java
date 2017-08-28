package com.jmarkstar.carlist_core.domain.interactor;

import com.jmarkstar.carlist_core.domain.model.PaginationInfoModel;
import com.jmarkstar.carlist_core.domain.model.PaginationItemsModel;

/**
 * Created by jmarkstar on 27/08/2017.
 */
public interface ManufacturerDispatcher {
    void getManufactures(Boolean fromServer, Integer page, Integer pageSize, String waKey, Action.Callback<PaginationItemsModel> callback);
    void getPaginationInfo(final Action.Callback<PaginationInfoModel> callback);
}
