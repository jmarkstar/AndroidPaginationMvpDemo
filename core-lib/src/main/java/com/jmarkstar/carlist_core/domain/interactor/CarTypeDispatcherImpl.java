package com.jmarkstar.carlist_core.domain.interactor;

import com.jmarkstar.carlist_core.domain.interactor.executor.Executor;
import com.jmarkstar.carlist_core.domain.repository.manager.CarTypeDataManager;
import javax.inject.Inject;

/**
 * Created by jmarkstar on 23/08/2017.
 */
public class CarTypeDispatcherImpl implements CarTypeDispatcher {

    @Inject Executor mExecutor;
    @Inject CarTypeDataManager mCarTypeDataManager;

    @Inject CarTypeDispatcherImpl(){}

    @Override public void deleteCacheFilters() {
        this.mExecutor.execute(new Action() {
            @Override public void run() {
                mCarTypeDataManager.deleteCacheFilters();
            }
        });
    }
}
