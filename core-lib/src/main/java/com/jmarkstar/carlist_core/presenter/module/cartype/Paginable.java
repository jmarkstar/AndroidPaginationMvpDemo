package com.jmarkstar.carlist_core.presenter.module.cartype;

import android.support.annotation.StringRes;

/**
 * Created by jmarkstar on 27/08/2017.
 */

public interface Paginable {
    void showPaginationProgress();
    void hidePaginationProgress();
    void showPaginationError(String message);
    void showPaginationError(@StringRes int message);
}
