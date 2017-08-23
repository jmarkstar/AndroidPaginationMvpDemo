package com.jmarkstar.carlist;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.jmarkstar.carlist.di.ApplicationComponent;
import com.jmarkstar.carlist.di.ApplicationModule;
import com.jmarkstar.carlist.di.DaggerApplicationComponent;
import com.jmarkstar.carlist.util.CrashlyticsTree;
import com.jmarkstar.carlist_core.domain.repository.database.DatabaseModule;
import com.jmarkstar.carlist_core.domain.repository.network.NetworkModule;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * Created by jmarkstar on 22/08/2017.
 */

public class CarListApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        settingAndroidLogging();
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule( new ApplicationModule(this))
                .databaseModule(new DatabaseModule())
                .networkModule(new NetworkModule(BuildConfig.BASE_URL))
                .build();
    }

    /** Settings Timber and Fabric
     * */
    private void settingAndroidLogging(){
        CrashlyticsCore core = new CrashlyticsCore.Builder()
                .disabled(BuildConfig.DEBUG)
                .build();
        Fabric.with(this, new Crashlytics.Builder().core(core).build() );
        if(BuildConfig.DEBUG){
            Timber.plant( new Timber.DebugTree());
        }
        Timber.plant(new CrashlyticsTree());
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
