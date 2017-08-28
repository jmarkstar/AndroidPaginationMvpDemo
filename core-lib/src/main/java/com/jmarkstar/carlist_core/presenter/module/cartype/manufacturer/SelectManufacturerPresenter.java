package com.jmarkstar.carlist_core.presenter.module.cartype.manufacturer;

import android.util.Log;
import com.jmarkstar.carlist_core.R;
import com.jmarkstar.carlist_core.domain.interactor.Action;
import com.jmarkstar.carlist_core.domain.interactor.ManufacturerDispatcher;
import com.jmarkstar.carlist_core.domain.model.PaginationInfoModel;
import com.jmarkstar.carlist_core.domain.model.PaginationItemsModel;
import com.jmarkstar.carlist_core.exception.ApiException;
import com.jmarkstar.carlist_core.presenter.base.BasePresenter;
import com.jmarkstar.carlist_core.util.Constants;
import javax.inject.Inject;

/**
 * Created by jmarkstar on 24/08/2017.
 */
public class SelectManufacturerPresenter<V extends SelectManufacturerMvpView>
    extends BasePresenter<V>
    implements SelectManufacturerMvpPresenter<V>{

    private static final String TAG = "ManufacturerPresenter";

    @Inject ManufacturerDispatcher mManufacturerDispatcher;

    private PaginationInfoModel paginationInfoModel;
    private boolean isLoading;
    private boolean isFirstRequest;

    @Inject SelectManufacturerPresenter(){}

    @Override public void doGetManufactures() {
        Log.v(TAG, "doGetManufactures()");
        if(mView.isAirplaneModeOff() && mView.isConnected()){
            getManufacturers();
        }else{
            if(isViewAttached())
                mView.showErrorMessage(R.string.exception_connection_error);
        }
    }

    @Override public void doGetMoreManufactures() {
        Log.v(TAG, "doGetMoreManufactures()");
        if(mView.isAirplaneModeOff() && mView.isConnected()){
            getMoreManufacturers();
        }else{
            if(isViewAttached())
                mView.showPaginationError(R.string.exception_connection_error);
        }
    }

    @Override public boolean isLastPage() {
        return paginationInfoModel.getPage() == paginationInfoModel.getTotalPageCount();
    }

    @Override public boolean isLoading() {
        return isLoading;
    }

    private void getMoreManufacturers(){
        isLoading = true;
        mManufacturerDispatcher.getPaginationInfo(new Action.Callback<PaginationInfoModel>() {
            @Override public void onSuccess(PaginationInfoModel pagination) {
                paginationInfoModel = pagination;
                Log.v(TAG, paginationInfoModel.toString());
                isFirstRequest = false;
                int nextPage = paginationInfoModel.getPage()+1;
                mView.showPaginationProgress();
                getManufacturers(true, nextPage);
            }

            @Override public void onError(Throwable ex) {}
        });
    }

    private void getManufacturers(){
        isLoading = true;
        mManufacturerDispatcher.getPaginationInfo(new Action.Callback<PaginationInfoModel>() {
            @Override public void onSuccess(PaginationInfoModel pagination) {
                paginationInfoModel = pagination;
                Log.v(TAG, paginationInfoModel.toString());
                if(isViewAttached())
                    mView.showProgress();

                boolean fromServer = true;
                if(paginationInfoModel.getPage() > 0){
                    fromServer = false;
                }
                isFirstRequest = true;
                getManufacturers(fromServer, paginationInfoModel.getPage());
            }

            @Override public void onError(Throwable ex) {}
        });
    }

    /** Get manufactures from data manager.
     * */
    private void getManufacturers(final Boolean fromServer, final Integer page){
        Log.v(TAG, "getManufacturer("+fromServer+", "+page+")");
        mManufacturerDispatcher.getManufactures(fromServer, page, Constants.PAGE_SIZE, Constants.WA_KEY,
            new Action.Callback<PaginationItemsModel>() {
                @Override public void onSuccess(PaginationItemsModel response) {
                    if(isViewAttached()){
                        if(isFirstRequest)
                            mView.hideProgress();
                        else
                            mView.hidePaginationProgress();
                        paginationInfoModel.setPage(response.getPage());
                        paginationInfoModel.setTotalPageCount(response.getTotalPageCount());
                        mView.showManufacturerList(response.getItems());
                    }
                    isLoading = false;
                }

                @Override public void onError(Throwable ex) {
                    if(ex instanceof ApiException){
                        ApiException apiException = (ApiException) ex;
                        if(isViewAttached()){
                            if(isFirstRequest)
                                mView.showErrorMessage(apiException.getErrorMessage());
                            else
                                mView.showPaginationError(apiException.getErrorMessage());
                        }
                    } else {
                        if(isViewAttached()) {
                            if (isFirstRequest)
                                mView.showErrorMessage(getErrorMessage(ex));
                            else
                                mView.showPaginationError(getErrorMessage(ex));
                        }
                    }

                    if(isViewAttached()){
                        if(isFirstRequest)
                            mView.hideProgress();
                        else
                            mView.hidePaginationProgress();
                    }
                    isLoading = false;
                }
            });
    }
}
