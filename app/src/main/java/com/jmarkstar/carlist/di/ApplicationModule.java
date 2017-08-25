package com.jmarkstar.carlist.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.jmarkstar.carlist.util.AppConstants;
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


    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context){
        return context.getSharedPreferences(AppConstants.SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }
}
