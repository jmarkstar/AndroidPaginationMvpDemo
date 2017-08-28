package com.jmarkstar.carlist_core.presenter.base;

import android.support.annotation.NonNull;
import android.util.Log;
import com.jmarkstar.carlist_core.R;
import com.jmarkstar.carlist_core.exception.LocalDatabaseException;
import com.jmarkstar.carlist_core.exception.NetworkException;
import com.jmarkstar.carlist_core.exception.UnAuthorizedApiException;

/**
 * Created by jmarkstar on 24/08/2017.
 */

public abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    protected V mView;

    public final void attachView(@NonNull V view) {
        mView = view;
    }

    public final void detachView() {
        mView = null;
    }

    protected final boolean isViewAttached() {
        return mView != null;
    }

    protected final int getErrorMessage(Throwable ex){
        ex.printStackTrace();
        int message;
        if(ex instanceof LocalDatabaseException){
            message = ((LocalDatabaseException)ex).getIdRsMessage();
        } else if(ex instanceof UnAuthorizedApiException){
            message = R.string.exception_unauthorized_api;
        } else if(ex instanceof NetworkException){
            NetworkException nEx = (NetworkException)ex;
            Log.v("handleError", "code = "+nEx.getHttpCode());
            if(nEx.getHttpCode() == 400){
                message = R.string.exception_400_bad_request;
            }else if(nEx.getHttpCode() == 404){
                message = R.string.exception_404_not_found;
            }else{
                message = R.string.exception_connection_error;
            }
        }else{
            message =  R.string.exception_connection_error;
        }
        return message;
    }
}