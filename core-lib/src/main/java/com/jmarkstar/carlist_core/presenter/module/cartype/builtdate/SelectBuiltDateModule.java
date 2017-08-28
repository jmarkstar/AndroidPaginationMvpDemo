package com.jmarkstar.carlist_core.presenter.module.cartype.builtdate;

import com.jmarkstar.carlist_core.domain.interactor.BuiltDatesDispatcher;
import com.jmarkstar.carlist_core.domain.interactor.BuiltDatesDispatcherImpl;
import com.jmarkstar.carlist_core.domain.repository.manager.BuiltDatesDataManager;
import com.jmarkstar.carlist_core.domain.repository.manager.BuiltDatesDataManagerImpl;
import com.jmarkstar.carlist_core.domain.repository.sharepreferences.AppPreferences;
import com.jmarkstar.carlist_core.domain.repository.sharepreferences.AppPreferencesImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jmarkstar on 25/08/2017.
 */
@Module
public class SelectBuiltDateModule {

    private SelectBuiltDateMvpView mView;

    public SelectBuiltDateModule(SelectBuiltDateMvpView mView){
        this.mView = mView;
    }

    @Provides AppPreferences provideAppPreferences(AppPreferencesImpl appPreferences){
        return appPreferences;
    }

    @Provides SelectBuiltDateMvpView provideSelectBuiltDateMvpView(){
        return mView;
    }

    @Provides BuiltDatesDataManager provideBuiltDateDataManager(BuiltDatesDataManagerImpl dataManager){
        return dataManager;
    }

    @Provides BuiltDatesDispatcher provideBuiltDateDispatcher(BuiltDatesDispatcherImpl dispatcher){
        return dispatcher;
    }

    @Provides SelectBuiltDateMvpPresenter<SelectBuiltDateMvpView>
        provideSelectBuiltDateMvpPresenter(SelectBuiltDatePresenter<SelectBuiltDateMvpView> presenter){
            presenter.attachView(mView);
            return presenter;
    }
}
