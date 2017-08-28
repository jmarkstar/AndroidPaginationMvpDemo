package com.jmarkstar.carlist.ui.cartype.pagination;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.jmarkstar.carlist.R;
import com.jmarkstar.carlist.di.ApplicationComponent;
import com.jmarkstar.carlist.parcelable.SelectedItemParcelable;
import com.jmarkstar.carlist.ui.BaseActivity;
import com.jmarkstar.carlist.ui.cartype.pagination.builtdate.SelectBuiltDateActivity;
import com.jmarkstar.carlist.ui.cartype.pagination.maintype.SelectMainTypeActivity;
import com.jmarkstar.carlist.ui.cartype.pagination.manutafturer.SelectManufacturerActivity;
import com.jmarkstar.carlist.util.AppConstants;
import com.jmarkstar.carlist_core.presenter.base.MvpView;
import com.jmarkstar.carlist_core.presenter.module.cartype.CarTypeModule;
import com.jmarkstar.carlist_core.presenter.module.cartype.CarTypeMvpPresenter;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jmarkstar on 26/08/2017.
 */
public class CarTypePaginationActivity extends BaseActivity {

    private static final String TAG = "CarTypePaginationAct";

    public static final String SELECTED_BUILT_DATE = "selected_built_date";
    public static final String SELECTED_MAIN_TYPE = "selected_main_type";
    public static final String SELECTED_MANUFACTURER = "selected_manufacturer";

    public static final int REQUEST_MANUFACTURER = 0;
    public static final int REQUEST_MAIN_TYPE = 1;
    public static final int REQUEST_BUILT_DATE = 2;

    @Inject CarTypeMvpPresenter<MvpView> mPresenter;

    @BindView(R.id.tv_manufacturer) TextView mTvManufacturer;
    @BindView(R.id.tv_main_type) TextView mTvMainType;
    @BindView(R.id.tv_built_date) TextView mTvBuiltDate;

    private SelectedItemParcelable selectedManufacturer;
    private SelectedItemParcelable selectedMainType;
    private SelectedItemParcelable selectedBuildDate;

    public static void start(Context context) {
        Intent starter = new Intent(context, CarTypePaginationActivity.class);
        context.startActivity(starter);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_type_pagination);
    }

    @Override protected void initComponent(ApplicationComponent applicationComponent) {
        DaggerCarTypePaginationComponent.builder()
                .applicationComponent(applicationComponent)
                .carTypeModule(new CarTypeModule(this))
                .build()
                .inject(this);
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        if(selectedManufacturer!=null)
            outState.putParcelable(SELECTED_MANUFACTURER, selectedManufacturer);
        if(selectedMainType!=null)
            outState.putParcelable(SELECTED_MAIN_TYPE, selectedMainType);
        if(selectedBuildDate!=null)
            outState.putParcelable(SELECTED_BUILT_DATE, selectedBuildDate);
        super.onSaveInstanceState(outState);
    }

    @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
        selectedManufacturer = savedInstanceState.getParcelable(SELECTED_MANUFACTURER);
        selectedMainType = savedInstanceState.getParcelable(SELECTED_MAIN_TYPE);
        selectedBuildDate = savedInstanceState.getParcelable(SELECTED_BUILT_DATE);
        if(selectedManufacturer!=null)
            mTvManufacturer.setText(selectedManufacturer.getName());
        if(selectedMainType!=null)
            mTvMainType.setText(selectedMainType.getName());
        if(selectedBuildDate!=null)
            mTvBuiltDate.setText(selectedBuildDate.getName());
        super.onRestoreInstanceState(savedInstanceState);
    }

    @OnClick(R.id.tv_manufacturer) public void onSelectManufacturer(){
        SelectManufacturerActivity.start(this, selectedManufacturer);
    }

    @OnClick(R.id.tv_main_type) public void onSelectMainType(){
        if(selectedManufacturer!=null)
            SelectMainTypeActivity.start(this, selectedManufacturer, selectedMainType);
        else
            Toast.makeText(this, R.string.manufacturer_required, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tv_built_date) public void onSelectBuiltDate(){
        if(selectedManufacturer==null){
            Toast.makeText(this, R.string.manufacturer_required, Toast.LENGTH_SHORT).show();
        }else if(selectedMainType==null){
            Toast.makeText(this, R.string.main_type_required, Toast.LENGTH_SHORT).show();
        }else{
            SelectBuiltDateActivity.start(this, selectedManufacturer, selectedMainType, selectedBuildDate);
        }
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_MANUFACTURER:
                if(resultCode == Activity.RESULT_OK){
                    selectedManufacturer = data.getParcelableExtra(SELECTED_MANUFACTURER);
                    if(selectedManufacturer!=null){
                        selectedMainType = null;
                        selectedBuildDate = null;
                        mTvManufacturer.setText(selectedManufacturer.getName());
                        mTvMainType.setText(AppConstants.EMPTY);
                        mTvBuiltDate.setText(AppConstants.EMPTY);
                    }
                }
                break;
            case REQUEST_MAIN_TYPE:
                if(resultCode == Activity.RESULT_OK){
                    selectedMainType = data.getParcelableExtra(SELECTED_MAIN_TYPE);
                    if(selectedMainType!=null){
                        selectedBuildDate = null;
                        mTvMainType.setText(selectedMainType.getName());
                        mTvBuiltDate.setText(AppConstants.EMPTY);
                    }
                }
                break;
            case REQUEST_BUILT_DATE:
                if(resultCode == Activity.RESULT_OK){
                    selectedBuildDate = data.getParcelableExtra(SELECTED_BUILT_DATE);
                    if(selectedBuildDate!=null)
                        mTvBuiltDate.setText(selectedBuildDate.getName());
                }
                break;

        }
    }

    @Override public void showProgress() {}

    @Override public void hideProgress() {}

    @Override protected void onDestroy() {
        mPresenter.doDeleteCacheFilters();
        mPresenter.detachView();
        super.onDestroy();
    }
}
