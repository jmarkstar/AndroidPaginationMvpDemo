package com.jmarkstar.carlist_core.domain.repository.manager;

import com.jmarkstar.carlist_core.domain.interactor.Action;
import com.jmarkstar.carlist_core.domain.model.ItemModel;
import com.jmarkstar.carlist_core.domain.repository.network.ApiTestService;
import com.jmarkstar.carlist_core.domain.repository.network.RemoteCallback;
import com.jmarkstar.carlist_core.domain.repository.network.mapper.CarTypeMapper;
import com.jmarkstar.carlist_core.domain.repository.network.response.BaseResponse;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;

/**
 * Created by jmarkstar on 27/08/2017.
 */
public class BuiltDatesDataManagerImpl extends BaseDataManager implements BuiltDatesDataManager {

    @Inject ApiTestService mApiTestService;

    @Inject BuiltDatesDataManagerImpl(){}

    @Override public void getBuiltDates(String manufacturer, String mainType, String waKey, final Action.Callback<List<ItemModel>> callback) {
        Call<BaseResponse<HashMap<String, String>>> responseCall =  mApiTestService.getBuiltDates(manufacturer, mainType, waKey);
        responseCall.enqueue(new RemoteCallback<BaseResponse<HashMap<String, String>>>() {
            @Override public void onSuccess(BaseResponse<HashMap<String, String>> response) {
                List<ItemModel> builtDates = CarTypeMapper.mapResponseToBuiltDatesModel(response);
                notifySuccess(builtDates, callback);
            }

            @Override public void onFailed(Throwable throwable) {
                notifyError(throwable, callback);
            }
        });
    }
}
