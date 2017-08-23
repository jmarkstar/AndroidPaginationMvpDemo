package com.jmarkstar.carlist.di;

import android.app.Application;
import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jmarkstar on 22/08/2017.
 */
@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application){
        this.mApplication = application;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return mApplication;
    }

}
