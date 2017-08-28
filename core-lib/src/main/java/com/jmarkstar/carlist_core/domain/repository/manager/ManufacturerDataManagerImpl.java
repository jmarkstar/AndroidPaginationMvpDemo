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
 * Created by jmarkstar on 26/08/2017.
 */
public class ManufacturerDataManagerImpl extends BaseDataManager implements ManufacturerDataManager {

    @Inject ApiTestService mApiTestService;
    @Inject ItemDao mItemDao;
    @Inject AppPreferences mAppPreferences;

    @Inject public ManufacturerDataManagerImpl() {}

    @Override public void getManufactures(Boolean fromServer, Integer page, Integer pageSize,
                                          String waKey, final Action.Callback<PaginationItemsModel> callback) {
        if(fromServer){
            Call<BaseResponse<HashMap<String, String>>> responseCall = mApiTestService.getManufacturers(page, pageSize, waKey);
            responseCall.enqueue(new RemoteCallback<BaseResponse<HashMap<String, String>>>() {
                @Override public void onSuccess(BaseResponse<HashMap<String, String>> response) {
                    mAppPreferences.saveManufacturerPage(response.getPage());
                    mAppPreferences.saveManufacturerTotalPageCount(response.getTotalPageCount());
                    try {
                        PaginationItemsModel paginationItemsModel = CarTypeMapper.mapResponseToItemsModel(response);
                        mItemDao.insertList(ItemDataMap.mapListModelToManufacturerData(paginationItemsModel.getItems(), ItemType.MANUFACTURER));
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
                paginationItemsModel.setPage(mAppPreferences.getManufacturerPage());
                paginationItemsModel.setTotalPageCount(mAppPreferences.getManufacturerTotalPageCount());
                paginationItemsModel.setItems(ItemDataMap.mapListDataToListModel(mItemDao.getItemsByType(ItemType.MANUFACTURER, null)));
                mItemDao.close();
                notifySuccess( paginationItemsModel, callback);
            } catch (LocalDatabaseException e) {
                notifyError(e, callback);
            }
        }
    }

    @Override public void getPaginationInfo(Action.Callback<PaginationInfoModel> callback) {
        PaginationInfoModel paginationInfoModel = new PaginationInfoModel();
        paginationInfoModel.setPage(mAppPreferences.getManufacturerPage());
        paginationInfoModel.setTotalPageCount(mAppPreferences.getManufacturerTotalPageCount());
        notifySuccess(paginationInfoModel, callback);
    }
}
