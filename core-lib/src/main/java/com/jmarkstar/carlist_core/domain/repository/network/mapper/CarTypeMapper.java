package com.jmarkstar.carlist_core.domain.repository.network.mapper;

import com.jmarkstar.carlist_core.domain.model.ItemModel;
import com.jmarkstar.carlist_core.domain.model.PaginationItemsModel;
import com.jmarkstar.carlist_core.domain.repository.network.response.BaseResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Mapper from Response to Model.
 * Created by jmarkstar on 24/08/2017.
 */
public class CarTypeMapper {

    public static PaginationItemsModel mapResponseToItemsModel(BaseResponse<HashMap<String, String>> response){
        PaginationItemsModel model = new PaginationItemsModel();
        model.setPage(response.getPage());
        model.setPageSize(response.getPageSize());
        model.setTotalPageCount(response.getTotalPageCount());
        for(Map.Entry<String, String> item : response.getWkda().entrySet()){
            model.getItems().add(mapMapEntryToModel(item));
        }
        Collections.sort(model.getItems());
        return model;
    }

    public static List<ItemModel> mapResponseToBuiltDatesModel(BaseResponse<HashMap<String, String>> response){
        List<ItemModel> builtDates = new ArrayList<>();
        for(Map.Entry<String, String> item : response.getWkda().entrySet()){
            builtDates.add(mapMapEntryToModel(item));
        }
        Collections.sort(builtDates, new Comparator<ItemModel>() {
            @Override public int compare(ItemModel itemModel, ItemModel t1) {
                Integer year1 = Integer.parseInt(itemModel.getNumber().trim());
                Integer year2 = Integer.parseInt(t1.getNumber().trim());
                return year1> year2 ? -1 : (year1 < year2 ? 1:0);
            }
        });
        return builtDates;
    }

    private static ItemModel mapMapEntryToModel(Map.Entry<String, String> item){
        ItemModel itemModel = new ItemModel();
        itemModel.setNumber(item.getKey());
        itemModel.setName(item.getValue());
        return itemModel;
    }
}
