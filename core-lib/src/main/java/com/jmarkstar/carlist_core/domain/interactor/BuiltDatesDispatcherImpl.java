package com.jmarkstar.carlist_core.domain.interactor;

import com.jmarkstar.carlist_core.R;
import com.jmarkstar.carlist_core.domain.interactor.executor.Executor;
import com.jmarkstar.carlist_core.domain.model.ItemModel;
import com.jmarkstar.carlist_core.domain.repository.manager.BuiltDatesDataManager;
import com.jmarkstar.carlist_core.exception.CallbackNullPointerException;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by jmarkstar on 27/08/2017.
 */
public class BuiltDatesDispatcherImpl implements BuiltDatesDispatcher {

    @Inject Executor mExecutor;
    @Inject BuiltDatesDataManager mBuiltDatesDataManager;

    @Inject BuiltDatesDispatcherImpl(){}

    @Override public void getBuiltDates(final String manufacturer, final String mainType,
                                    final String waKey, final Action.Callback<List<ItemModel>> callback) {
        if (null == callback) {
            throw new CallbackNullPointerException(R.string.exception_callback_null);
        }
        this.mExecutor.execute(new Action() {
            @Override public void run() {
                mBuiltDatesDataManager.getBuiltDates(manufacturer, mainType, waKey, callback);
            }
        });
    }
}
