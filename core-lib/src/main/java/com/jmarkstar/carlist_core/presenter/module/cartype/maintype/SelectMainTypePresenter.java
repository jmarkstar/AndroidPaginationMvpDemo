package com.jmarkstar.carlist_core.presenter.module.cartype.maintype;

import android.util.Log;
import com.jmarkstar.carlist_core.R;
import com.jmarkstar.carlist_core.domain.interactor.Action;
import com.jmarkstar.carlist_core.domain.interactor.MainTypeDataDispatcher;
import com.jmarkstar.carlist_core.domain.model.PaginationInfoModel;
import com.jmarkstar.carlist_core.domain.model.PaginationItemsModel;
import com.jmarkstar.carlist_core.exception.ApiException;
import com.jmarkstar.carlist_core.presenter.base.BasePresenter;
import com.jmarkstar.carlist_core.util.Constants;
import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by jmarkstar on 27/08/2017.
 */
public class SelectMainTypePresenter<V extends SelectMainTypeMvpView>
    extends BasePresenter<V>
    implements SelectMainTypeMvpPresenter<V>{

    @Inject MainTypeDataDispatcher mMainTypeDispatcher;

    private PaginationInfoModel paginationInfoModel;
    private boolean isLoading;
    private boolean isFirstRequest;

    @Inject SelectMainTypePresenter(){}

    @Override public void doGetMoreMainTypes(final String manufacturer) {
        Timber.d("doGetBuiltDates("+manufacturer+")");
        if(mView.isAirplaneModeOff() && mView.isConnected()){
            getMoreMainTypes(manufacturer);
        }else{
            if(isViewAttached())
                mView.showPaginationError(R.string.exception_connection_error);
        }
    }

    @Override public void doGetMainTypes(final String manufacturer) {
        Timber.d("doGetBuiltDates("+manufacturer+")");
        if(mView.isAirplaneModeOff() && mView.isConnected()){
            getMainTypes(manufacturer);
        }else{
            if(isViewAttached())
                mView.showErrorMessage(R.string.exception_connection_error);
        }
    }

    @Override public boolean isLastPage() {
        return isLoading;
    }

    @Override public boolean isLoading() {
        return paginationInfoModel.getPage() == paginationInfoModel.getTotalPageCount();
    }

    private void getMoreMainTypes(final String manufacturer){
        isLoading = true;
        mMainTypeDispatcher.getPaginationInfo(manufacturer, new Action.Callback<PaginationInfoModel>() {
            @Override public void onSuccess(PaginationInfoModel pagination) {
                paginationInfoModel = pagination;
                Timber.d(paginationInfoModel.toString());
                isFirstRequest = false;
                int nextPage = paginationInfoModel.getPage()+1;
                mView.showPaginationProgress();
                getMainTypesByManufacturer(true, nextPage, manufacturer);
            }

            @Override public void onError(Throwable ex) {}
        });
    }

    private void getMainTypes(final String manufacturer){
        isLoading = true;
        mMainTypeDispatcher.getPaginationInfo(manufacturer, new Action.Callback<PaginationInfoModel>() {
            @Override public void onSuccess(PaginationInfoModel pagination) {
                paginationInfoModel = pagination;
                Timber.d( paginationInfoModel.toString());
                if(isViewAttached())
                    mView.showProgress();

                boolean fromServer = true;
                if(paginationInfoModel.getPage() > 0){
                    fromServer = false;
                }
                isFirstRequest = true;
                getMainTypesByManufacturer(fromServer, paginationInfoModel.getPage(),manufacturer);
            }

            @Override public void onError(Throwable ex) {}
        });
    }


    private void getMainTypesByManufacturer(boolean fromServer, int page, String manufacturer){
        Timber.d("getMainTypesByManufacturer("+manufacturer+","+page+", "+manufacturer+")");
        mMainTypeDispatcher.getMainTypes(fromServer, manufacturer, page,
                Constants.PAGE_SIZE, Constants.WA_KEY, new Action.Callback<PaginationItemsModel>() {
            @Override public void onSuccess(PaginationItemsModel response) {
                paginationInfoModel.setPage(response.getPage());
                paginationInfoModel.setTotalPageCount(response.getTotalPageCount());
                Timber.d( "paginationInfoModel="+paginationInfoModel);
                if(isViewAttached()){
                    if(isFirstRequest)
                        mView.hideProgress();
                    else
                        mView.hidePaginationProgress();
                    mView.showMainTypeList(response.getItems());
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
