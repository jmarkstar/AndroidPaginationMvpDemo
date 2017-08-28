package com.jmarkstar.carlist_core.domain.repository.manager;

import com.jmarkstar.carlist_core.domain.interactor.Action;
import com.jmarkstar.carlist_core.domain.model.PaginationInfoModel;
import com.jmarkstar.carlist_core.domain.model.PaginationItemsModel;

/**
 * Created by jmarkstar on 26/08/2017.
 */

public interface ManufacturerDataManager {
    void getManufactures(Boolean fromServer, Integer page, Integer pageSize, String waKey, Action.Callback<PaginationItemsModel> callback);
    void getPaginationInfo(final Action.Callback<PaginationInfoModel> callback);
}
