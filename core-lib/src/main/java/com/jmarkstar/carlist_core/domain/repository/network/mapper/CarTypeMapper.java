package com.jmarkstar.carlist_core.domain.repository.network.mapper;

import com.jmarkstar.carlist_core.domain.model.BuiltDatesModel;
import com.jmarkstar.carlist_core.domain.model.PaginationMainTypesModel;
import com.jmarkstar.carlist_core.domain.model.PaginationManufaturesModel;
import com.jmarkstar.carlist_core.domain.repository.network.response.BaseResponse;
import java.util.HashMap;

/** Mapper from Response to Model.
 * Created by jmarkstar on 24/08/2017.
 */
public class CarTypeMapper {

    public static PaginationManufaturesModel mapResponseToManufactureModel(BaseResponse<HashMap<String, String>> response){
        PaginationManufaturesModel model = new PaginationManufaturesModel();
        model.setPage(response.getPage());
        model.setPageSize(response.getPageSize());
        model.setTotalPageCount(response.getTotalPageCount());
        model.setManufactures(response.getWkda());
        return model;
    }

    public static PaginationMainTypesModel mapResponseToMainTypesModel(BaseResponse<HashMap<String, String>> response){
        PaginationMainTypesModel model = new PaginationMainTypesModel();
        model.setPage(response.getPage());
        model.setPageSize(response.getPageSize());
        model.setTotalPageCount(response.getTotalPageCount());
        model.setMainTypes(response.getWkda());
        return model;
    }

    public static BuiltDatesModel mapResponseToBuiltDatesModel(BaseResponse<HashMap<String, String>> response){
        BuiltDatesModel model = new BuiltDatesModel();
        model.setBuiltDates(response.getWkda());
        return model;
    }
}
