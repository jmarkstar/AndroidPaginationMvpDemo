package com.jmarkstar.carlist_core.presenter.module.cartype.manufacturer;

import com.jmarkstar.carlist_core.domain.interactor.Action;
import com.jmarkstar.carlist_core.domain.interactor.CarTypeDispatcher;
import com.jmarkstar.carlist_core.domain.model.PaginationManufaturesModel;
import com.jmarkstar.carlist_core.domain.repository.sharepreferences.AppPreferences;
import com.jmarkstar.carlist_core.exception.ApiException;
import com.jmarkstar.carlist_core.presenter.base.BasePresenter;
import com.jmarkstar.carlist_core.util.Constants;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by jmarkstar on 24/08/2017.
 */

public class SelectManufacturerPresenter<V extends SelectManufacturerMvpView>
    extends BasePresenter<V>
    implements SelectManufacturerMvpPresenter<V>{

    private final int MAX_PAGES_IN_MEMMORY = 3;

    @Inject AppPreferences mAppPreferences;
    @Inject CarTypeDispatcher mCarTypeDispatcher;

    private HashMap<String, String> mManufacturers;

    @Inject SelectManufacturerPresenter(){
        mManufacturers = new HashMap<>();
    }

    @Override public void doUpManufacturerList() {

    }

    @Override public void doDownManufacturerList() {
        int page = mAppPreferences.getManufacturerNextPage();
        int maxPages = mAppPreferences.getManufacturerMaxPages();
        //if(page <= maxPages){
            mView.loadDownProgress();
            getManufacturerFromServer(page);
       // }
    }

    private void getManufacturerFromServer(int page){
        mCarTypeDispatcher.getManufactures(page, Constants.PAGE_SIZE, Constants.WA_KEY,
            new Action.Callback<PaginationManufaturesModel>() {
                @Override public void onSuccess(PaginationManufaturesModel response) {
                    if(isViewAttached()){
                        int nextPage = response.getPage()+1;
                        int page = response.getPage();
                        mAppPreferences.saveManufacturerNextPage(nextPage);
                        mAppPreferences.saveManufacturerPage(page);
                        mView.showManufacturerList(response.getManufactures());
                    }
                }

                @Override public void onError(Throwable ex) {
                    if(isViewAttached()){
                        if(ex instanceof ApiException){
                            ApiException apiException = (ApiException) ex;
                            mView.showErrorMessage(apiException.getErrorMessage());
                        } else {
                            handleError(ex);
                        }
                    }
                }
            });
    }
}
