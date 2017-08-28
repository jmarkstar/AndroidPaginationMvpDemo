package com.jmarkstar.carlist_core.domain.interactor;

import com.jmarkstar.carlist_core.R;
import com.jmarkstar.carlist_core.domain.interactor.executor.Executor;
import com.jmarkstar.carlist_core.domain.model.PaginationInfoModel;
import com.jmarkstar.carlist_core.domain.model.PaginationItemsModel;
import com.jmarkstar.carlist_core.domain.repository.manager.ManufacturerDataManager;
import com.jmarkstar.carlist_core.exception.CallbackNullPointerException;
import javax.inject.Inject;

/**
 * Created by jmarkstar on 27/08/2017.
 */
public class ManufacturerDispatcherImpl implements ManufacturerDispatcher {

    @Inject Executor mExecutor;
    @Inject ManufacturerDataManager mManufacturerDataManager;

    @Inject ManufacturerDispatcherImpl(){}

    @Override public void getManufactures(final Boolean fromServer, final Integer page, final Integer pageSize,
                                          final String waKey, final Action.Callback<PaginationItemsModel> callback) {
        if (null == callback)
            throw new CallbackNullPointerException(R.string.exception_callback_null);
        this.mExecutor.execute(new Action() {
            @Override public void run() {
                mManufacturerDataManager.getManufactures(fromServer, page, pageSize, waKey, callback);
            }
        });
    }

    @Override public void getPaginationInfo(final Action.Callback<PaginationInfoModel> callback) {
        if (null == callback)
            throw new CallbackNullPointerException(R.string.exception_callback_null);
        this.mExecutor.execute(new Action() {
            @Override public void run() {
                mManufacturerDataManager.getPaginationInfo(callback);
            }
        });
    }
}
