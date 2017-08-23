package com.jmarkstar.carlist_core.domain.repository.manager;

import com.jmarkstar.carlist_core.domain.interactor.Action;
import com.jmarkstar.carlist_core.domain.interactor.executor.MainThread;
import javax.inject.Inject;

/**
 * Created by jmarkstar on 22/08/2017.
 */
abstract class BaseDataManager {

    @Inject
    MainThread mMainThread;

    void notifySuccess(final Object response, final Action.Callback callback) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(response);
            }
        });
    }

    void notifyError(final Throwable throwable, final Action.Callback callback) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(throwable);
            }
        });
    }
}
