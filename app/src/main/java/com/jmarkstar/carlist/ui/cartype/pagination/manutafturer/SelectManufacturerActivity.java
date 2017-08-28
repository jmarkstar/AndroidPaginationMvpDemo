package com.jmarkstar.carlist.ui.cartype.pagination.manutafturer;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.jmarkstar.carlist.R;
import com.jmarkstar.carlist.customview.ErrorMessageView;
import com.jmarkstar.carlist.di.ApplicationComponent;
import com.jmarkstar.carlist.parcelable.ItemParcelable;
import com.jmarkstar.carlist.parcelable.mapper.ItemMapper;
import com.jmarkstar.carlist.ui.BaseActivity;
import com.jmarkstar.carlist.ui.cartype.pagination.CarTypePaginationActivity;
import com.jmarkstar.carlist.ui.cartype.pagination.PaginationAdapter;
import com.jmarkstar.carlist.util.PaginationScrollListener;
import com.jmarkstar.carlist_core.domain.model.ItemModel;
import com.jmarkstar.carlist_core.presenter.module.cartype.manufacturer.SelectManufacturerModule;
import com.jmarkstar.carlist_core.presenter.module.cartype.manufacturer.SelectManufacturerMvpPresenter;
import com.jmarkstar.carlist_core.presenter.module.cartype.manufacturer.SelectManufacturerMvpView;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;

/**
 * Created by jmarkstar on 26/08/2017.
 */
public class SelectManufacturerActivity extends BaseActivity
        implements SelectManufacturerMvpView, PaginationAdapter.OnClickPaginationItem{

    private static final String TAG = "SelectManufacAct";
    private static final String SELECTED_MANUFACTURER = "selected_manufacturer";
    private static final String CURRENT_SCROLLING_POSITION = "current_scrolling_position";

    @Inject SelectManufacturerMvpPresenter<SelectManufacturerMvpView> mPresenter;

    @BindView(R.id.rv_manufacturers) RecyclerView mRvManufacturers;
    @BindView(R.id.pg_main_progress) ProgressBar mPgMainProgress;
    @BindView(R.id.emv_message) ErrorMessageView mEmvMessage;

    private LinearLayoutManager linearLayoutManager;
    private ManufacturerAdapter mAdapter;

    private boolean isFirstTime;
    private Integer mCurrentScrollingPosition;
    private ItemParcelable mSelectedManufacturer;

    public static void start(AppCompatActivity activity, ItemParcelable selectedManufacturer) {
        Intent starter = new Intent(activity, SelectManufacturerActivity.class);
        starter.putExtra(SELECTED_MANUFACTURER, selectedManufacturer);
        activity.startActivityForResult(starter, CarTypePaginationActivity.REQUEST_MANUFACTURER);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_manufacturer);
        settingsToolbar();

        mSelectedManufacturer = getIntent().getParcelableExtra(SELECTED_MANUFACTURER);

        mAdapter = new ManufacturerAdapter();
        mAdapter.addOnClickPaginationItem(this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvManufacturers.setLayoutManager(linearLayoutManager);
        mRvManufacturers.setItemAnimator(new DefaultItemAnimator());
        mRvManufacturers.setAdapter(mAdapter);
        mRvManufacturers.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override protected void loadMoreItems() {
                isFirstTime = false;
                mPresenter.doGetMoreManufactures();
            }

            @Override public boolean isLastPage() {
                return mPresenter.isLastPage();
            }

            @Override public boolean isLoading() {
                return mPresenter.isLoading();
            }
        });
        isFirstTime = true;
        mPresenter.doGetManufactures();
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(SELECTED_MANUFACTURER, mSelectedManufacturer);
        outState.putInt(CURRENT_SCROLLING_POSITION, linearLayoutManager.findFirstVisibleItemPosition());
        super.onSaveInstanceState(outState);
    }

    @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mCurrentScrollingPosition = savedInstanceState.getInt(CURRENT_SCROLLING_POSITION);
        mSelectedManufacturer = savedInstanceState.getParcelable(SELECTED_MANUFACTURER);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override protected void initComponent(ApplicationComponent applicationComponent) {
        DaggerSelectManufacturerComponent.builder()
            .applicationComponent(applicationComponent)
            .selectManufacturerModule(new SelectManufacturerModule(this))
            .build()
            .inject(this);
    }

    @Override public void onClickItem(ItemModel model) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(CarTypePaginationActivity.SELECTED_MANUFACTURER, ItemMapper.mapModelToParcelable(model));
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override public void showProgress() {
        if(mRvManufacturers.getVisibility() == View.VISIBLE)
            mRvManufacturers.setVisibility(View.GONE);
        if(mEmvMessage.getVisibility() == View.VISIBLE)
            mEmvMessage.setVisibility(View.GONE);
        mPgMainProgress.setVisibility(View.VISIBLE);
    }

    @Override public void hideProgress() {
        mPgMainProgress.setVisibility(View.GONE);
    }

    @Override public void showErrorMessage(@StringRes int errorMessage) {
        showError(getString(errorMessage));
    }

    @Override public void showErrorMessage(String errorMessage) {
        showError(errorMessage);
    }

    @Override public void showPaginationProgress() {
        mRvManufacturers.post(new Runnable() {
            @Override public void run() {
                mAdapter.addLoadingFooter();
            }
        });
    }

    @Override public void hidePaginationProgress() {
        mAdapter.removeLoadingFooter();
    }

    @Override public void showPaginationError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override public void showPaginationError(@StringRes int message) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show();
    }

    @Override public void showManufacturerList(List<ItemModel> manufacturers) {
        if(mRvManufacturers.getVisibility() == View.GONE)
            mRvManufacturers.setVisibility(View.VISIBLE);
        if(mEmvMessage.getVisibility() == View.VISIBLE)
            mEmvMessage.setVisibility(View.GONE);
        mAdapter.addItems(manufacturers);

        if(isFirstTime && mCurrentScrollingPosition!=null)
            mRvManufacturers.scrollToPosition(mCurrentScrollingPosition);
        else
            scrollToSelectedItem(manufacturers);
    }

    @Override public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    /** Scroll 2 less than the select built date.
     * */
    private void scrollToSelectedItem(List<ItemModel> manufacturers){
        if(mSelectedManufacturer!=null){
            int selectedItemPosition = -1;
            for(int i=0; i<manufacturers.size();i++){
                if(manufacturers.get(i).getNumber().equals(mSelectedManufacturer.getNumber())){
                    selectedItemPosition = i;
                }
            }
            if(selectedItemPosition>=0){
                int minus;
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                    minus = 2;
                else
                    minus = 1;

                if(selectedItemPosition>2 )
                    mRvManufacturers.scrollToPosition(selectedItemPosition-minus);
                else
                    mRvManufacturers.scrollToPosition(selectedItemPosition);
            }
        }
    }

    private void showError(String errorMessage){
        mRvManufacturers.setVisibility(View.GONE);
        mEmvMessage.setVisibility(View.VISIBLE);
        mEmvMessage.setCause(errorMessage);
        mEmvMessage.onRetry(new View.OnClickListener() {
            @Override public void onClick(View view) {
                mPresenter.doGetManufactures();
            }
        });
    }

    private void settingsToolbar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle(R.string.manufacturer_title);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
