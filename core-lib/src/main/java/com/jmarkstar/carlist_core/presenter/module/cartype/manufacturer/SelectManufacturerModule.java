package com.jmarkstar.carlist_core.presenter.module.cartype.manufacturer;

import com.jmarkstar.carlist_core.domain.interactor.ManufacturerDispatcher;
import com.jmarkstar.carlist_core.domain.interactor.ManufacturerDispatcherImpl;
import com.jmarkstar.carlist_core.domain.repository.manager.ManufacturerDataManager;
import com.jmarkstar.carlist_core.domain.repository.manager.ManufacturerDataManagerImpl;
import com.jmarkstar.carlist_core.domain.repository.sharepreferences.AppPreferences;
import com.jmarkstar.carlist_core.domain.repository.sharepreferences.AppPreferencesImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jmarkstar on 24/08/2017.
 */
@Module
public class SelectManufacturerModule {

    private SelectManufacturerMvpView mView;

    public SelectManufacturerModule(SelectManufacturerMvpView mView){
        this.mView = mView;
    }

    @Provides AppPreferences provideAppPreferences(AppPreferencesImpl appPreferences){
        return appPreferences;
    }

    @Provides SelectManufacturerMvpView provideSelectManufacturerMvpView(){
        return mView;
    }

    @Provides ManufacturerDataManager provideManufacturerDataManager(ManufacturerDataManagerImpl dataManager){
        return dataManager;
    }

    @Provides ManufacturerDispatcher provideManufacturerDispatcher(ManufacturerDispatcherImpl dispatcher){
        return dispatcher;
    }

    @Provides SelectManufacturerMvpPresenter<SelectManufacturerMvpView>
        provideSelectManufacturerMvpPresenter(SelectManufacturerPresenter<SelectManufacturerMvpView> presenter){
            presenter.attachView(mView);
            return presenter;
    }

}
