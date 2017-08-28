package com.jmarkstar.carlist_core.domain.repository.database.entity.mapper;

import com.jmarkstar.carlist_core.domain.model.ItemModel;
import com.jmarkstar.carlist_core.domain.repository.database.entity.ItemData;
import com.jmarkstar.carlist_core.domain.repository.database.entity.ItemType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jmarkstar on 27/08/2017.
 */

public class ItemDataMap {

    public static List<ItemModel> mapListDataToListModel(List<ItemData> datas){
        List<ItemModel> models = new ArrayList<>();
        for(ItemData data : datas){
            models.add(mapDataToModel(data));
        }
        Collections.sort(models);
        return models;
    }

    public static List<ItemData> mapListModelToItemData(List<ItemModel> models, ItemType itemType){
        List<ItemData> datas = new ArrayList<>();
        for(ItemModel model : models){
            datas.add(mapModelToData(model, itemType));
        }
        return datas;
    }

    public static List<ItemData> mapListModelToMainTypeData(List<ItemModel> models, String manufacturer, ItemType itemType){
        List<ItemData> datas = new ArrayList<>();
        for(ItemModel model : models){
            datas.add(mapModelToMainTypeData(model, manufacturer, itemType));
        }
        return datas;
    }

    public static ItemData mapModelToMainTypeData(ItemModel model, String manufacturer, ItemType itemType){
        ItemData data = new ItemData();
        data.setNumber(model.getNumber());
        data.setName(model.getName());
        data.setManufacturer(manufacturer);
        data.setType(itemType.getValue());
        return data;
    }

    public static ItemData mapModelToData(ItemModel model, ItemType itemType){
        ItemData data = new ItemData();
        data.setNumber(model.getNumber());
        data.setName(model.getName());
        data.setType(itemType.getValue());
        return data;
    }

    public static ItemModel mapDataToModel(ItemData data){
        ItemModel itemModel = new ItemModel();
        itemModel.setName(data.getName());
        itemModel.setNumber(data.getNumber());
        return itemModel;
    }
}
