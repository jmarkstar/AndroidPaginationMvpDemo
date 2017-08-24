package com.jmarkstar.carlist_core.domain.interactor;

import com.jmarkstar.carlist_core.R;
import com.jmarkstar.carlist_core.domain.interactor.executor.Executor;
import com.jmarkstar.carlist_core.domain.model.BuiltDatesModel;
import com.jmarkstar.carlist_core.domain.model.PaginationMainTypesModel;
import com.jmarkstar.carlist_core.domain.model.PaginationManufaturesModel;
import com.jmarkstar.carlist_core.domain.repository.manager.CarTypeDataManager;
import com.jmarkstar.carlist_core.exception.CallbackNullPointerException;
import javax.inject.Inject;

/**
 * Created by jmarkstar on 23/08/2017.
 */
public class CarTypeDispatcherImpl implements CarTypeDispatcher {

    private static final String TAG = "CarTypeDispatcherImpl";

    @Inject Executor mExecutor;
    @Inject CarTypeDataManager mCarTypeDataManager;

    @Inject CarTypeDispatcherImpl(){}

    @Override public void getManufactures(final Integer page, final Integer pageSize, final String waKey,
                                          final Action.Callback<PaginationManufaturesModel> callback) {
        if (null == callback) {
            throw new CallbackNullPointerException(R.string.exception_callback_null);
        }
        this.mExecutor.execute(new Action() {
            @Override public void run() {
                mCarTypeDataManager.getManufactures(page, pageSize, waKey, callback);
            }
        });
    }

    @Override public void getMainTypes(final String manufacturer, final Integer page, final Integer pageSize, final String waKey,
                                       final Action.Callback<PaginationMainTypesModel> callback) {
        if (null == callback) {
            throw new CallbackNullPointerException(R.string.exception_callback_null);
        }
        this.mExecutor.execute(new Action() {
            @Override public void run() {
                mCarTypeDataManager.getMainTypes(manufacturer, page, pageSize, waKey, callback);
            }
        });
    }

    @Override public void getBuiltDates(final String manufacturer, final String mainType, final String waKey,
                                        final Action.Callback<BuiltDatesModel> callback) {
        if (null == callback) {
            throw new CallbackNullPointerException(R.string.exception_callback_null);
        }
        this.mExecutor.execute(new Action() {
            @Override public void run() {
                mCarTypeDataManager.getBuiltDates(manufacturer, mainType, waKey, callback);
            }
        });
    }
}
