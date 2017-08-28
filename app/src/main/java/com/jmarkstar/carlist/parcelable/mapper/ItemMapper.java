package com.jmarkstar.carlist.parcelable.mapper;

import com.jmarkstar.carlist.parcelable.SelectedItemParcelable;
import com.jmarkstar.carlist_core.domain.model.ItemModel;

/**
 * Created by jmarkstar on 26/08/2017.
 */

public class ItemMapper {

    public static SelectedItemParcelable mapModelToParcelable(ItemModel model){
        SelectedItemParcelable selectedManufacturer = new SelectedItemParcelable();
        selectedManufacturer.setName(model.getName());
        selectedManufacturer.setNumber(model.getNumber());
        return selectedManufacturer;
    }
}
