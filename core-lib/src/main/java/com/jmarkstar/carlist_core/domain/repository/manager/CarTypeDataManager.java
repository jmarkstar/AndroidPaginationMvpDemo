package com.jmarkstar.carlist_core.domain.repository.manager;

import com.jmarkstar.carlist_core.domain.interactor.Action;
import com.jmarkstar.carlist_core.domain.model.BuiltDatesModel;
import com.jmarkstar.carlist_core.domain.model.PaginationMainTypesModel;
import com.jmarkstar.carlist_core.domain.model.PaginationManufaturesModel;

/**
 * Created by jmarkstar on 23/08/2017.
 */
public interface CarTypeDataManager {
    void getManufactures(Integer page, Integer pageSize, String waKey, Action.Callback<PaginationManufaturesModel> callback);
    void getMainTypes(String manufacturer, Integer page, Integer pageSize, String waKey, Action.Callback<PaginationMainTypesModel> callback);
    void getBuiltDates(String manufacturer, String mainType, String waKey, Action.Callback<BuiltDatesModel> callback);
}
