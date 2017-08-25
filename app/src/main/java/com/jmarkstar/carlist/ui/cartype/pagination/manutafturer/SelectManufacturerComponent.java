package com.jmarkstar.carlist.ui.cartype.pagination.manutafturer;

import com.jmarkstar.carlist.di.ActivityScope;
import com.jmarkstar.carlist.di.ApplicationComponent;
import com.jmarkstar.carlist_core.presenter.module.cartype.manufacturer.SelectManufacturerModule;

import dagger.Component;

/**
 * Created by jmarkstar on 25/08/2017.
 */
@ActivityScope
@Component(modules = {SelectManufacturerModule.class}, dependencies = {ApplicationComponent.class})
public interface SelectManufacturerComponent {
    void inject(SelectManufacturerActivity activity);
}
