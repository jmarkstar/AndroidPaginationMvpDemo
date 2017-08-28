package com.jmarkstar.carlist.ui.cartype.pagination.builtdate;

import com.jmarkstar.carlist.di.ActivityScope;
import com.jmarkstar.carlist.di.ApplicationComponent;
import com.jmarkstar.carlist_core.presenter.module.cartype.builtdate.SelectBuiltDateModule;
import dagger.Component;

/**
 * Created by jmarkstar on 25/08/2017.
 */
@ActivityScope
@Component(modules = {SelectBuiltDateModule.class}, dependencies = ApplicationComponent.class)
public interface SelectBuiltDateComponent {
    void inject(SelectBuiltDateActivity activity);
}
