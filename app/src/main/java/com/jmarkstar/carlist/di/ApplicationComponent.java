package com.jmarkstar.carlist.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.jmarkstar.carlist.ui.main.MainActivity;
import com.jmarkstar.carlist_core.domain.interactor.executor.Executor;
import com.jmarkstar.carlist_core.domain.interactor.executor.ExecutorModule;
import com.jmarkstar.carlist_core.domain.interactor.executor.MainThread;
import com.jmarkstar.carlist_core.domain.repository.database.DatabaseModule;
import com.jmarkstar.carlist_core.domain.repository.network.ApiTestService;
import com.jmarkstar.carlist_core.domain.repository.network.NetworkModule;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by jmarkstar on 22/08/2017.
 */
@Singleton
@Component(modules = {ApplicationModule.class, DatabaseModule.class, NetworkModule.class, ExecutorModule.class})
public interface ApplicationComponent {

    Context getContext();
    SharedPreferences getSharedPreferences();
    Executor getExecutor();
    MainThread getMainThread();
    ApiTestService getApiTestService();

    void inject(MainActivity mainActivity);

}
