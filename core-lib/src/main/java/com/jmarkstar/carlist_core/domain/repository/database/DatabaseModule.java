package com.jmarkstar.carlist_core.domain.repository.database;

import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jmarkstar on 22/08/2017.
 */
@Module
public final class DatabaseModule {

    @Singleton
    @Provides
    CarListDatabaseHelper provideDataBaseHelper(Context context){
        return new CarListDatabaseHelper(context);
    }
}
