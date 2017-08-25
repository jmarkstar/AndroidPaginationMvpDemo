package com.jmarkstar.carlist_core.presenter.module.cartype.manufacturer;

import com.jmarkstar.carlist_core.domain.interactor.CarTypeDispatcher;
import com.jmarkstar.carlist_core.domain.interactor.CarTypeDispatcherImpl;
import com.jmarkstar.carlist_core.domain.repository.manager.CarTypeDataManager;
import com.jmarkstar.carlist_core.domain.repository.manager.CarTypeDataManagerImpl;
import com.jmarkstar.carlist_core.domain.repository.sharepreferences.AppPreferences;

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

    @Provides AppPreferences provideAppPreferences(AppPreferences appPreferences){
        return appPreferences;
    }

    @Provides SelectManufacturerMvpView provideSelectManufacturerMvpView(){
        return mView;
    }

    @Provides CarTypeDataManager  provideCarTypeDataManager(CarTypeDataManagerImpl carTypeDataManager){
        return carTypeDataManager;
    }

    @Provides CarTypeDispatcher provideCarTypeDispatcher(CarTypeDispatcherImpl carTypeDispatcher){
        return carTypeDispatcher;
    }

    @Provides SelectManufacturerMvpPresenter<SelectManufacturerMvpView>
        provideSelectManufacturerMvpPresenter(SelectManufacturerPresenter<SelectManufacturerMvpView> presenter){
            presenter.attachView(mView);
            return presenter;
    }

}
