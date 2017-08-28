package com.jmarkstar.carlist_core.domain.repository.manager;

import com.jmarkstar.carlist_core.domain.interactor.Action;
import com.jmarkstar.carlist_core.domain.model.ItemModel;
import java.util.List;

/**
 * Created by jmarkstar on 27/08/2017.
 */
public interface BuiltDatesDataManager {
    void getBuiltDates(String manufacturer, String mainType, String waKey, Action.Callback<List<ItemModel>> callback);
}
