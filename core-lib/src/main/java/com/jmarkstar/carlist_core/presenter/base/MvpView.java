package com.jmarkstar.carlist_core.presenter.base;

import android.support.annotation.StringRes;

/**
 * Created by jmarkstar on 24/08/2017.
 */

public interface MvpView {
    void showProgress();
    void hideProgress();
    void showErrorMessage(@StringRes int errorMessage);
    void showErrorMessage(String errorMessage);
    boolean isConnected();
    boolean isAirplaneModeOff();
}
