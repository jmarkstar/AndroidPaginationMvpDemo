package com.jmarkstar.carlist_core.presenter.module.cartype;

import com.jmarkstar.carlist_core.domain.interactor.CarTypeDispatcher;
import com.jmarkstar.carlist_core.domain.interactor.CarTypeDispatcherImpl;
import com.jmarkstar.carlist_core.domain.repository.manager.CarTypeDataManager;
import com.jmarkstar.carlist_core.domain.repository.manager.CarTypeDataManagerImpl;
import com.jmarkstar.carlist_core.domain.repository.sharepreferences.AppPreferences;
import com.jmarkstar.carlist_core.domain.repository.sharepreferences.AppPreferencesImpl;
import com.jmarkstar.carlist_core.presenter.base.MvpView;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jmarkstar on 22/08/2017.
 */
@Module
public class CarTypeModule {

    private MvpView mView;

    public CarTypeModule(MvpView mView){
        this.mView = mView;
    }

    @Provides AppPreferences provideAppPreferences(AppPreferencesImpl appPreferences){
        return appPreferences;
    }

    @Provides MvpView provideMvpViewMvpView(){
        return mView;
    }

    @Provides CarTypeDataManager provideCarTypeDataManager(CarTypeDataManagerImpl dataManager){
        return dataManager;
    }

    @Provides CarTypeDispatcher provideCarTypeDispatcher(CarTypeDispatcherImpl dispatcher){
        return dispatcher;
    }

    @Provides CarTypeMvpPresenter<MvpView>
        provideCarTypeMvpPresenter(CarTypePresenter<MvpView> presenter){
            presenter.attachView(mView);
            return presenter;
    }
}
