package com.jmarkstar.carlist.ui.cartype.pagination.maintype;

import com.jmarkstar.carlist.di.ActivityScope;
import com.jmarkstar.carlist.di.ApplicationComponent;
import com.jmarkstar.carlist_core.presenter.module.cartype.maintype.SelectMainTypeModule;

import dagger.Component;

/**
 * Created by jmarkstar on 25/08/2017.
 */
@ActivityScope
@Component(modules = {SelectMainTypeModule.class}, dependencies = {ApplicationComponent.class})
public interface SelectMainTypeComponent {
    void inject(SelectMainTypeActivity activity);
}
