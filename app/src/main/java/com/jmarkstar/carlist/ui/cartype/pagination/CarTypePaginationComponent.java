package com.jmarkstar.carlist.ui.cartype.pagination;

import com.jmarkstar.carlist.di.ActivityScope;
import com.jmarkstar.carlist.di.ApplicationComponent;
import com.jmarkstar.carlist_core.presenter.module.cartype.CarTypeModule;
import dagger.Component;

/**
 * Created by jmarkstar on 27/08/2017.
 */
@ActivityScope
@Component(modules = {CarTypeModule.class}, dependencies = {ApplicationComponent.class})
public interface CarTypePaginationComponent {
    void inject(CarTypePaginationActivity activity);
}
