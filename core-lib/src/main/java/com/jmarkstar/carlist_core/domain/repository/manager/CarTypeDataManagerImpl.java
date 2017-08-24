package com.jmarkstar.carlist_core.domain.repository.manager;

import com.jmarkstar.carlist_core.domain.interactor.Action;
import com.jmarkstar.carlist_core.domain.model.BuiltDatesModel;
import com.jmarkstar.carlist_core.domain.model.PaginationMainTypesModel;
import com.jmarkstar.carlist_core.domain.model.PaginationManufaturesModel;
import com.jmarkstar.carlist_core.domain.repository.network.ApiTestService;
import com.jmarkstar.carlist_core.domain.repository.network.RemoteCallback;
import com.jmarkstar.carlist_core.domain.repository.network.mapper.CarTypeMapper;
import com.jmarkstar.carlist_core.domain.repository.network.response.BaseResponse;
import java.util.HashMap;
import javax.inject.Inject;
import retrofit2.Call;

/** DataManager get data from services, local database or sharepreferences but in this case
 * I´m just going to use Api Rest.
 * Created by jmarkstar on 23/08/2017.
 */
public class CarTypeDataManagerImpl extends BaseDataManager implements CarTypeDataManager {

    @Inject ApiTestService mApiTestService;

    @Inject public CarTypeDataManagerImpl() {}


    @Override public void getManufactures(Integer page, Integer pageSize, String waKey,
                                          final Action.Callback<PaginationManufaturesModel> callback) {
        Call<BaseResponse<HashMap<String, String>>> responseCall = mApiTestService.getManufacturers(page, pageSize, waKey);
        responseCall.enqueue(new RemoteCallback<BaseResponse<HashMap<String, String>>>() {
            @Override public void onSuccess(BaseResponse<HashMap<String, String>> response) {
                notifySuccess(CarTypeMapper.mapResponseToManufactureModel(response), callback);
            }

            @Override public void onFailed(Throwable throwable) {
                notifyError(throwable, callback);
            }
        });
    }

    @Override public void getMainTypes(String manufacturer, Integer page, Integer pageSize, String waKey,
                                       final Action.Callback<PaginationMainTypesModel> callback) {
        Call<BaseResponse<HashMap<String, String>>> responseCall = mApiTestService.getMainTypes(manufacturer, page, pageSize, waKey);
        responseCall.enqueue(new RemoteCallback<BaseResponse<HashMap<String, String>>>() {
            @Override public void onSuccess(BaseResponse<HashMap<String, String>> response) {
                notifySuccess(CarTypeMapper.mapResponseToMainTypesModel(response), callback);
            }

            @Override public void onFailed(Throwable throwable) {
                notifyError(throwable, callback);
            }
        });
    }

    @Override public void getBuiltDates(String manufacturer, String mainType, String waKey,
                                        final Action.Callback<BuiltDatesModel> callback) {
        Call<BaseResponse<HashMap<String, String>>> responseCall = mApiTestService.getBuiltDates(manufacturer, mainType, waKey);
        responseCall.enqueue(new RemoteCallback<BaseResponse<HashMap<String, String>>>() {
            @Override public void onSuccess(BaseResponse<HashMap<String, String>> response) {
                notifySuccess(CarTypeMapper.mapResponseToBuiltDatesModel(response), callback);
            }

            @Override public void onFailed(Throwable throwable) {
                notifyError(throwable, callback);
            }
        });
    }
}
