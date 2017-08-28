package com.jmarkstar.carlist_core.presenter.module.cartype.maintype;

import com.jmarkstar.carlist_core.domain.interactor.MainTypeDataDispatcher;
import com.jmarkstar.carlist_core.domain.interactor.MainTypeDataDispatcherImpl;
import com.jmarkstar.carlist_core.domain.repository.manager.MainTypeDataManager;
import com.jmarkstar.carlist_core.domain.repository.manager.MainTypeDataManagerImpl;
import com.jmarkstar.carlist_core.domain.repository.sharepreferences.AppPreferences;
import com.jmarkstar.carlist_core.domain.repository.sharepreferences.AppPreferencesImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jmarkstar on 25/08/2017.
 */
@Module
public class SelectMainTypeModule {

    private SelectMainTypeMvpView mView;

    public SelectMainTypeModule(SelectMainTypeMvpView mView){
        this.mView = mView;
    }

    @Provides AppPreferences provideAppPreferences(AppPreferencesImpl appPreferences){
        return appPreferences;
    }

    @Provides SelectMainTypeMvpView provideSelectMainTypeMvpView(){
        return mView;
    }

    @Provides MainTypeDataManager provideMainTypeDataManager(MainTypeDataManagerImpl dataManager){
        return dataManager;
    }

    @Provides MainTypeDataDispatcher provideMainTypeDispatcher(MainTypeDataDispatcherImpl dispatcher){
        return dispatcher;
    }

    @Provides SelectMainTypeMvpPresenter<SelectMainTypeMvpView>
        provideSelectMainTypeMvpPresenter(SelectMainTypePresenter<SelectMainTypeMvpView> presenter){
            presenter.attachView(mView);
            return presenter;
    }
}
