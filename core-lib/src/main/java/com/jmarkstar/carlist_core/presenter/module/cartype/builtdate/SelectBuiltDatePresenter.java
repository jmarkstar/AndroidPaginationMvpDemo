package com.jmarkstar.carlist_core.presenter.module.cartype.builtdate;

import android.util.Log;

import com.jmarkstar.carlist_core.R;
import com.jmarkstar.carlist_core.domain.interactor.Action;
import com.jmarkstar.carlist_core.domain.interactor.BuiltDatesDispatcher;
import com.jmarkstar.carlist_core.domain.model.ItemModel;
import com.jmarkstar.carlist_core.exception.ApiException;
import com.jmarkstar.carlist_core.presenter.base.BasePresenter;
import com.jmarkstar.carlist_core.util.Constants;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by jmarkstar on 27/08/2017.
 */
public class SelectBuiltDatePresenter<V extends SelectBuiltDateMvpView>
        extends BasePresenter<V>
        implements SelectBuiltDateMvpPresenter<V> {

    private static final String TAG = "BuiltDatesPresenter";

    @Inject BuiltDatesDispatcher mBuiltDatesDispatcher;

    @Inject SelectBuiltDatePresenter(){}

    @Override public void doGetBuiltDates(String manufacturer, String mainType) {
        Log.v(TAG, "doGetBuiltDates("+manufacturer+", "+mainType+")");
        mView.showProgress();
        if(mView.isAirplaneModeOff() && mView.isConnected()){
            getBuiltDates(manufacturer, mainType);
        }else{
            if(isViewAttached()){
                mView.hideProgress();
                mView.showErrorMessage(R.string.exception_connection_error);
            }
        }
    }

    private void getBuiltDates(String manufacturer, String mainType){
        mBuiltDatesDispatcher.getBuiltDates(manufacturer, mainType, Constants.WA_KEY, new Action.Callback<List<ItemModel>>() {
            @Override public void onSuccess(List<ItemModel> response) {
                if(isViewAttached()){
                    mView.showBuiltDatesList(response);
                    mView.hideProgress();
                }
            }

            @Override public void onError(Throwable ex) {
                if(ex instanceof ApiException){
                    ApiException apiException = (ApiException)ex;
                    if(isViewAttached())
                        mView.showErrorMessage(apiException.getErrorMessage());
                } else {
                    mView.showErrorMessage(getErrorMessage(ex));
                }
                if(isViewAttached())
                    mView.hideProgress();
            }
        });
    }
}
