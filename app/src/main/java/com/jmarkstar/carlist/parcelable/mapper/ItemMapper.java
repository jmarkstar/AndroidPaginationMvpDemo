package com.jmarkstar.carlist.parcelable.mapper;

import com.jmarkstar.carlist.parcelable.ItemParcelable;
import com.jmarkstar.carlist_core.domain.model.ItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jmarkstar on 26/08/2017.
 */

public class ItemMapper {

    public static ArrayList<ItemParcelable> mapListModelToListParcelable(List<ItemModel> models){
        ArrayList<ItemParcelable> parcelables = new ArrayList<>();
        for(ItemModel model : models){
            parcelables.add(mapModelToParcelable(model));
        }
        return parcelables;
    }

    public static List<ItemModel> mapListParcelableToListModel(ArrayList<ItemParcelable> parcelables){
        List<ItemModel> models = new ArrayList<>();
        for(ItemParcelable parcelable : parcelables){
            models.add(mapParcelableToModel(parcelable));
        }
        return models;
    }

    public static ItemParcelable mapModelToParcelable(ItemModel model){
        ItemParcelable selectedManufacturer = new ItemParcelable();
        selectedManufacturer.setName(model.getName());
        selectedManufacturer.setNumber(model.getNumber());
        return selectedManufacturer;
    }

    public static ItemModel mapParcelableToModel(ItemParcelable parcelable){
        ItemModel model = new ItemModel();
        model.setName(parcelable.getName());
        model.setNumber(parcelable.getNumber());
        return model;
    }
}
