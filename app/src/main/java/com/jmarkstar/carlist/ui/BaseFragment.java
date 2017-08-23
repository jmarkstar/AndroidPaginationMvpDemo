package com.jmarkstar.carlist.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import com.jmarkstar.carlist.CarListApplication;
import com.jmarkstar.carlist.di.ApplicationComponent;

/**
 * Created by jmarkstar on 22/08/2017.
 */

public abstract class BaseFragment extends Fragment {

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        injectDependencies(((CarListApplication)getActivity().getApplication()).getApplicationComponent(), context);
    }

    protected abstract void injectDependencies(ApplicationComponent component, Context context);
}
