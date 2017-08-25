package com.jmarkstar.carlist.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.jmarkstar.carlist.CarListApplication;
import com.jmarkstar.carlist.di.ApplicationComponent;
import com.jmarkstar.carlist.util.AppUtils;
import com.jmarkstar.carlist_core.presenter.base.MvpView;

import butterknife.ButterKnife;

/**
 * Created by jmarkstar on 22/08/2017.
 */
public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponent(((CarListApplication)getApplication()).getApplicationComponent());
    }

    @Override public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected abstract void initComponent(ApplicationComponent applicationComponent);

    @Override public boolean isConnected() {
        return AppUtils.isConnected(this);
    }

    @Override public boolean isAirplaneModeOff() {
        return AppUtils.isAirplaneModeOff(this);
    }

    @Override public void showErrorMessage(@StringRes int errorMessage) {
        Toast.makeText(this, getString(errorMessage), Toast.LENGTH_LONG).show();
    }

    @Override public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }
}
