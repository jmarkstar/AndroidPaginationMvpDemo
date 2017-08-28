package com.jmarkstar.carlist.ui.cartype.pagination.maintype;

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
import com.jmarkstar.carlist_core.presenter.module.cartype.maintype.SelectMainTypeModule;
import com.jmarkstar.carlist_core.presenter.module.cartype.maintype.SelectMainTypeMvpPresenter;
import com.jmarkstar.carlist_core.presenter.module.cartype.maintype.SelectMainTypeMvpView;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;

/**
 * Created by jmarkstar on 26/08/2017.
 */
public class SelectMainTypeActivity extends BaseActivity implements SelectMainTypeMvpView, PaginationAdapter.OnClickPaginationItem {

    private static final String SELECTED_MANUFACTURER = "selected_manufacturer";
    private static final String SELECTED_MAIN_TYPE = "selected_main_type";
    private static final String CURRENT_SCROLLING_POSITION = "current_scrolling_position";

    @Inject SelectMainTypeMvpPresenter<SelectMainTypeMvpView> mPresenter;

    @BindView(R.id.rv_main_types) RecyclerView mRvMainTypes;
    @BindView(R.id.pg_main_progress) ProgressBar mPgMainProgress;
    @BindView(R.id.emv_message) ErrorMessageView mEmvMessage;

    private LinearLayoutManager linearLayoutManager;
    private MainTypeAdapter mAdapter;

    private boolean isFirstTime;
    private Integer mCurrentScrollingPosition;
    private ItemParcelable mSelectedMainType;
    private ItemParcelable mSelectedManufacturer;

    public static void start(AppCompatActivity activity, ItemParcelable manufacturer,
                             ItemParcelable mainType) {
        Intent starter = new Intent(activity, SelectMainTypeActivity.class);
        starter.putExtra(SELECTED_MANUFACTURER, manufacturer);
        starter.putExtra(SELECTED_MAIN_TYPE, mainType);
        activity.startActivityForResult(starter, CarTypePaginationActivity.REQUEST_MAIN_TYPE);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_main_type);
        settingsToolbar();

        mSelectedManufacturer = getIntent().getParcelableExtra(SELECTED_MANUFACTURER);
        mSelectedMainType = getIntent().getParcelableExtra(SELECTED_MAIN_TYPE);

        mAdapter = new MainTypeAdapter();
        mAdapter.addOnClickPaginationItem(this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvMainTypes.setLayoutManager(linearLayoutManager);
        mRvMainTypes.setItemAnimator(new DefaultItemAnimator());
        mRvMainTypes.setAdapter(mAdapter);
        mRvMainTypes.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override protected void loadMoreItems() {
                isFirstTime = false;
                mPresenter.doGetMoreMainTypes(mSelectedManufacturer.getNumber());
            }

            @Override public boolean isLastPage() {
                return mPresenter.isLastPage();
            }

            @Override public boolean isLoading() {
                return mPresenter.isLoading();
            }
        });
        isFirstTime = true;
        mPresenter.doGetMainTypes(mSelectedManufacturer.getNumber());
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(SELECTED_MANUFACTURER, mSelectedManufacturer);
        outState.putParcelable(SELECTED_MAIN_TYPE, mSelectedMainType);
        outState.putInt(CURRENT_SCROLLING_POSITION, linearLayoutManager.findFirstVisibleItemPosition());
        super.onSaveInstanceState(outState);
    }

    @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mCurrentScrollingPosition = savedInstanceState.getInt(CURRENT_SCROLLING_POSITION);
        mSelectedManufacturer = savedInstanceState.getParcelable(SELECTED_MANUFACTURER);
        mSelectedMainType = savedInstanceState.getParcelable(SELECTED_MAIN_TYPE);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override protected void initComponent(ApplicationComponent applicationComponent) {
        DaggerSelectMainTypeComponent.builder()
                .applicationComponent(applicationComponent)
                .selectMainTypeModule(new SelectMainTypeModule(this))
                .build()
                .inject(this);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override public void showProgress() {
        if(mRvMainTypes.getVisibility() == View.VISIBLE)
            mRvMainTypes.setVisibility(View.GONE);
        if(mEmvMessage.getVisibility() == View.VISIBLE)
            mEmvMessage.setVisibility(View.GONE);
        mPgMainProgress.setVisibility(View.VISIBLE);
    }

    @Override public void hideProgress() {
        mPgMainProgress.setVisibility(View.GONE);
    }

    @Override public void showPaginationProgress() {
        mRvMainTypes.post(new Runnable() {
            @Override public void run() {
                mAdapter.addLoadingFooter();
            }
        });
    }

    @Override public void hidePaginationProgress() {
        mAdapter.removeLoadingFooter();
    }

    @Override public void showErrorMessage(@StringRes int errorMessage) {
        showError(getString(errorMessage));
    }

    @Override public void showErrorMessage(String errorMessage) {
        showError(errorMessage);
    }


    @Override public void showPaginationError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override public void showPaginationError(@StringRes int message) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show();
    }

    @Override public void showMainTypeList(List<ItemModel> mainTypes) {
        if(mRvMainTypes.getVisibility() == View.GONE)
            mRvMainTypes.setVisibility(View.VISIBLE);
        if(mEmvMessage.getVisibility() == View.VISIBLE)
            mEmvMessage.setVisibility(View.GONE);
        mAdapter.addItems(mainTypes);

        if(isFirstTime && mCurrentScrollingPosition!=null)
            mRvMainTypes.scrollToPosition(mCurrentScrollingPosition);
        else
            scrollToSelectedItem(mainTypes);
    }

    /** Scroll 2 less than the select built date.
     * */
    private void scrollToSelectedItem(List<ItemModel> mainTypes){
        if(mSelectedMainType!=null){
            int selectedItemPosition = -1;
            for(int i=0; i<mainTypes.size();i++){
                if(mainTypes.get(i).getNumber().equals(mSelectedMainType.getNumber())){
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
                    mRvMainTypes.scrollToPosition(selectedItemPosition-minus);
                else
                    mRvMainTypes.scrollToPosition(selectedItemPosition);
            }
        }
    }

    @Override public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    private void showError(String errorMessage){
        mRvMainTypes.setVisibility(View.GONE);
        mEmvMessage.setVisibility(View.VISIBLE);
        mEmvMessage.setCause(errorMessage);
        mEmvMessage.onRetry(new View.OnClickListener() {
            @Override public void onClick(View view) {
                mPresenter.doGetMainTypes(mSelectedManufacturer.getNumber());
            }
        });
    }

    private void settingsToolbar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle(R.string.main_type_title);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override public void onClickItem(ItemModel model) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(CarTypePaginationActivity.SELECTED_MAIN_TYPE, ItemMapper.mapModelToParcelable(model));
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
