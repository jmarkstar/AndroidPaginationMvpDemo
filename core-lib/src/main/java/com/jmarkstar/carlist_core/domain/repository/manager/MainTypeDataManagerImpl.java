package com.jmarkstar.carlist_core.domain.repository.manager;

import com.jmarkstar.carlist_core.domain.interactor.Action;
import com.jmarkstar.carlist_core.domain.model.PaginationInfoModel;
import com.jmarkstar.carlist_core.domain.model.PaginationItemsModel;
import com.jmarkstar.carlist_core.domain.repository.database.dao.ItemDao;
import com.jmarkstar.carlist_core.domain.repository.database.entity.ItemType;
import com.jmarkstar.carlist_core.domain.repository.database.entity.mapper.ItemDataMap;
import com.jmarkstar.carlist_core.domain.repository.network.ApiTestService;
import com.jmarkstar.carlist_core.domain.repository.network.RemoteCallback;
import com.jmarkstar.carlist_core.domain.repository.network.mapper.CarTypeMapper;
import com.jmarkstar.carlist_core.domain.repository.network.response.BaseResponse;
import com.jmarkstar.carlist_core.domain.repository.sharepreferences.AppPreferences;
import com.jmarkstar.carlist_core.exception.LocalDatabaseException;
import java.util.HashMap;
import javax.inject.Inject;
import retrofit2.Call;

/**
 * Created by jmarkstar on 27/08/2017.
 */
public class MainTypeDataManagerImpl extends BaseDataManager implements MainTypeDataManager {

    @Inject ApiTestService mApiTestService;
    @Inject ItemDao mItemDao;
    @Inject AppPreferences mAppPreferences;

    @Inject MainTypeDataManagerImpl(){}

    @Override public void getMainTypes(Boolean fromServer, final String manufacturer, Integer page, Integer pageSize,
                                       String waKey, final Action.Callback<PaginationItemsModel> callback) {
        if(fromServer){
            Call<BaseResponse<HashMap<String, String>>> responseCall = mApiTestService.getMainTypes(manufacturer, page, pageSize, waKey);
            responseCall.enqueue(new RemoteCallback<BaseResponse<HashMap<String, String>>>() {
                @Override public void onSuccess(BaseResponse<HashMap<String, String>> response) {
                    mAppPreferences.saveMainTypesPage(manufacturer, response.getPage());
                    mAppPreferences.saveMainTypesTotalPageCount(manufacturer, response.getTotalPageCount());
                    PaginationItemsModel paginationItemsModel = CarTypeMapper.mapResponseToItemsModel(response);
                    try {
                        mItemDao.insertList(ItemDataMap.mapListModelToMainTypeData(
                                paginationItemsModel.getItems(), manufacturer, ItemType.MAIN_TYPE));
                        mItemDao.close();
                        notifySuccess(paginationItemsModel, callback);
                    } catch (LocalDatabaseException e) {
                        notifyError(e, callback);
                    }
                }

                @Override public void onFailed(Throwable throwable) {
                    notifyError(throwable, callback);
                }
            });
        }else{
            try {
                PaginationItemsModel paginationItemsModel = new PaginationItemsModel();
                paginationItemsModel.setPage(mAppPreferences.getMainTypesPage(manufacturer));
                paginationItemsModel.setTotalPageCount(mAppPreferences.getMainTypesTotalPageCount(manufacturer));
                paginationItemsModel.setItems(ItemDataMap.mapListDataToListModel(mItemDao.getItemsByType(ItemType.MAIN_TYPE, manufacturer)));
                mItemDao.close();
                notifySuccess( paginationItemsModel, callback);
            } catch (LocalDatabaseException e) {
                notifyError(e, callback);
            }
        }
    }

    @Override public void getPaginationInfo(String manufacturer, Action.Callback<PaginationInfoModel> callback) {
        PaginationInfoModel paginationInfoModel = new PaginationInfoModel();
        paginationInfoModel.setPage(mAppPreferences.getMainTypesPage(manufacturer));
        paginationInfoModel.setTotalPageCount(mAppPreferences.getMainTypesTotalPageCount(manufacturer));
        notifySuccess(paginationInfoModel, callback);
    }
}
