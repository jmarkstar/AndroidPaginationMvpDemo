package com.jmarkstar.carlist.ui.cartype.pagination.builtdate;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import com.jmarkstar.carlist.R;
import com.jmarkstar.carlist.customview.ErrorMessageView;
import com.jmarkstar.carlist.di.ApplicationComponent;
import com.jmarkstar.carlist.parcelable.SelectedItemParcelable;
import com.jmarkstar.carlist.parcelable.mapper.ItemMapper;
import com.jmarkstar.carlist.ui.BaseActivity;
import com.jmarkstar.carlist.ui.cartype.pagination.CarTypePaginationActivity;
import com.jmarkstar.carlist_core.domain.model.ItemModel;
import com.jmarkstar.carlist_core.presenter.module.cartype.builtdate.SelectBuiltDateModule;
import com.jmarkstar.carlist_core.presenter.module.cartype.builtdate.SelectBuiltDateMvpPresenter;
import com.jmarkstar.carlist_core.presenter.module.cartype.builtdate.SelectBuiltDateMvpView;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;

/**
 * Created by jmarkstar on 26/08/2017.
 */
public class SelectBuiltDateActivity extends BaseActivity
        implements SelectBuiltDateMvpView, BuiltDateAdapter.OnClickItemListener {

    private static final String SELECTED_MANUFACTURER = "selected_manufacturer";
    private static final String SELECTED_MAIN_TYPE = "selected_main_type";
    private static final String SELECTED_BUILT_DATE = "selected_built_date";
    private static final String CURRENT_SCROLLING_POSITION = "current_scrolling_position";

    @Inject SelectBuiltDateMvpPresenter<SelectBuiltDateMvpView> mPresenter;

    @BindView(R.id.rv_built_dates) RecyclerView mRvBuiltDates;
    @BindView(R.id.pg_main_progress) ProgressBar mPgMainProgress;
    @BindView(R.id.emv_message) ErrorMessageView mEmvMessage;

    private LinearLayoutManager linearLayoutManager;

    private BuiltDateAdapter mAdapter;
    private Integer mCurrentScrollingPosition;
    private SelectedItemParcelable mSelectedMainType;
    private SelectedItemParcelable mSelectedManufacturer;
    private SelectedItemParcelable mSelectedBuiltDate;

    public static void start(AppCompatActivity activity, SelectedItemParcelable manufacturer,
                             SelectedItemParcelable mainType, SelectedItemParcelable builtDate) {
        Intent starter = new Intent(activity, SelectBuiltDateActivity.class);
        starter.putExtra(SELECTED_MANUFACTURER, manufacturer);
        starter.putExtra(SELECTED_MAIN_TYPE, mainType);
        starter.putExtra(SELECTED_MAIN_TYPE, mainType);
        starter.putExtra(SELECTED_BUILT_DATE, builtDate);
        activity.startActivityForResult(starter, CarTypePaginationActivity.REQUEST_BUILT_DATE);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_built_date);
        settingsToolbar();

        mSelectedManufacturer = getIntent().getParcelableExtra(SELECTED_MANUFACTURER);
        mSelectedMainType = getIntent().getParcelableExtra(SELECTED_MAIN_TYPE);
        mSelectedBuiltDate = getIntent().getParcelableExtra(SELECTED_BUILT_DATE);

        mAdapter = new BuiltDateAdapter(this);
        mAdapter.addOnClickItemListener(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvBuiltDates.setLayoutManager(linearLayoutManager);
        mRvBuiltDates.setItemAnimator(new DefaultItemAnimator());
        mRvBuiltDates.setAdapter(mAdapter);

        if(mSelectedManufacturer !=null && mSelectedMainType!=null)
            mPresenter.doGetBuiltDates(mSelectedManufacturer.getNumber(), mSelectedMainType.getNumber());
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(SELECTED_MANUFACTURER, mSelectedManufacturer);
        outState.putParcelable(SELECTED_MAIN_TYPE, mSelectedMainType);
        outState.putParcelable(SELECTED_BUILT_DATE, mSelectedBuiltDate);
        outState.putInt(CURRENT_SCROLLING_POSITION, linearLayoutManager.findFirstVisibleItemPosition());
        super.onSaveInstanceState(outState);
    }

    @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mCurrentScrollingPosition = savedInstanceState.getInt(CURRENT_SCROLLING_POSITION);
        mSelectedManufacturer = savedInstanceState.getParcelable(SELECTED_MANUFACTURER);
        mSelectedMainType = savedInstanceState.getParcelable(SELECTED_MAIN_TYPE);
        mSelectedBuiltDate = savedInstanceState.getParcelable(SELECTED_BUILT_DATE);
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
        DaggerSelectBuiltDateComponent.builder()
            .applicationComponent(applicationComponent)
            .selectBuiltDateModule(new SelectBuiltDateModule(this))
            .build()
            .inject(this);
    }

    @Override public void showProgress() {
        if(mRvBuiltDates.getVisibility() == View.VISIBLE)
            mRvBuiltDates.setVisibility(View.GONE);
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

    @Override public void showBuiltDatesList(List<ItemModel> builtDates) {
        if(mRvBuiltDates.getVisibility() == View.GONE)
            mRvBuiltDates.setVisibility(View.VISIBLE);
        if(mEmvMessage.getVisibility() == View.VISIBLE)
            mEmvMessage.setVisibility(View.GONE);
        mAdapter.addList(builtDates);

        if(mCurrentScrollingPosition!=null)
            mRvBuiltDates.scrollToPosition(mCurrentScrollingPosition);
        else
            scrollToSelectedItem(builtDates);
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
        if(mSelectedBuiltDate!=null){
            int selectedItemPosition = -1;
            for(int i=0; i<manufacturers.size();i++){
                if(manufacturers.get(i).getNumber().equals(mSelectedBuiltDate.getNumber())){
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
                    mRvBuiltDates.scrollToPosition(selectedItemPosition-minus);
                else
                    mRvBuiltDates.scrollToPosition(selectedItemPosition);
            }
        }
    }

    private void showError(String errorMessage){
        mRvBuiltDates.setVisibility(View.GONE);
        mEmvMessage.setVisibility(View.VISIBLE);
        mEmvMessage.setCause(errorMessage);
        mEmvMessage.onRetry(new View.OnClickListener() {
            @Override public void onClick(View view) {
                mPresenter.doGetBuiltDates(mSelectedManufacturer.getNumber(), mSelectedMainType.getNumber());
            }
        });
    }

    private void settingsToolbar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle(R.string.built_date_title);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override public void onClickItem(ItemModel item) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(CarTypePaginationActivity.SELECTED_BUILT_DATE, ItemMapper.mapModelToParcelable(item));
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
