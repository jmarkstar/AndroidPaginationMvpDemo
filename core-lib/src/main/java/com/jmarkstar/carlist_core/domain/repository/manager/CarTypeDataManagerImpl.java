package com.jmarkstar.carlist_core.domain.repository.manager;

import android.util.Log;

import com.jmarkstar.carlist_core.domain.repository.database.dao.ItemDao;
import com.jmarkstar.carlist_core.domain.repository.sharepreferences.AppPreferences;
import com.jmarkstar.carlist_core.exception.LocalDatabaseException;

import javax.inject.Inject;

/** DataManager get data from services, local database or sharepreferences but in this case
 * I´m just going to use Api Rest.
 * Created by jmarkstar on 23/08/2017.
 */
public class CarTypeDataManagerImpl extends BaseDataManager implements CarTypeDataManager {

    @Inject AppPreferences mAppPreferences;
    @Inject ItemDao mItemDao;

    @Inject public CarTypeDataManagerImpl() {}


    @Override public void deleteCacheFilters() {
        try {
            mAppPreferences.deleteAll();
            mItemDao.deleteAll();
            Log.v("CarTypeDataManagerImpl", "Cache was cleaned.");
        } catch (LocalDatabaseException e) {
            e.printStackTrace();
        }
    }
}
