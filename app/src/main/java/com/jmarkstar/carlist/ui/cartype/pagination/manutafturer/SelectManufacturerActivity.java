package com.jmarkstar.carlist.ui.cartype.pagination.manutafturer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jmarkstar.carlist.R;
import com.jmarkstar.carlist.di.ApplicationComponent;
import com.jmarkstar.carlist.ui.BaseActivity;
import com.jmarkstar.carlist_core.presenter.module.cartype.manufacturer.SelectManufacturerModule;
import com.jmarkstar.carlist_core.presenter.module.cartype.manufacturer.SelectManufacturerMvpView;

import java.util.HashMap;

public class SelectManufacturerActivity extends BaseActivity implements SelectManufacturerMvpView{

    private static final String SELECTED_MANUFACTURER = "selected_manufacturer";

    private String mSelectedManufacturerId;

    public static void start(Context context, String selectedManufacturerId) {
        Intent starter = new Intent(context, SelectManufacturerActivity.class);
        starter.putExtra(SELECTED_MANUFACTURER, selectedManufacturerId);
        context.startActivity(starter);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_manufacturer);

        mSelectedManufacturerId = getIntent().getStringExtra(SELECTED_MANUFACTURER);


    }

    @Override protected void initComponent(ApplicationComponent applicationComponent) {
        DaggerSelectManufacturerComponent.builder()
                .applicationComponent(applicationComponent)
                .selectManufacturerModule(new SelectManufacturerModule(this))
                .build()
                .inject(this);
    }

    @Override public void showProgress() {

    }

    @Override public void hideProgress() {

    }

    @Override public void loadDownProgress() {

    }

    @Override public void loadUpProgress() {

    }

    @Override public void showManufacturerList(HashMap<String, String> manufacturers) {

    }
}
