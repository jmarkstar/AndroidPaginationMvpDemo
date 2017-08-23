package com.jmarkstar.carlist_core.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.util.Log;
import com.jmarkstar.carlist_core.R;
import com.jmarkstar.carlist_core.exception.LocalDatabaseException;
import com.jmarkstar.carlist_core.exception.NetworkException;
import com.jmarkstar.carlist_core.exception.UnAuthorizedApiException;

/**
 * Created by jmarkstar on 31/05/2017.
 */
public abstract class BaseContractor {

    public interface MvpView {
        void showProgress();
        void hideProgress();
        void showErrorMessage(@StringRes int errorMessage);
        void showErrorMessage(String errorMessage);
        boolean isConnected();
        boolean isAirplaneModeOff();
    }

    public interface MvpPresenter<V extends MvpView>{
        void attachView(V view);
        void detachView();
    }

    public static abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V>{

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

        protected final void handleError(Throwable ex){
            ex.printStackTrace();
            if(ex instanceof LocalDatabaseException){
                mView.showErrorMessage(((LocalDatabaseException)ex).getIdRsMessage());
            } else if(ex instanceof UnAuthorizedApiException){
                mView.showErrorMessage(R.string.exception_unauthorized_api);
            } else if(ex instanceof NetworkException){
                NetworkException nEx = (NetworkException)ex;
                Log.v("BaseContractor", "code = "+nEx.getHttpCode());
                if(nEx.getHttpCode() == 404){
                    mView.showErrorMessage(R.string.exception_404_not_found);
                }
            }else{
                mView.showErrorMessage(R.string.exception_connection_error);
            }
        }
    }
}
