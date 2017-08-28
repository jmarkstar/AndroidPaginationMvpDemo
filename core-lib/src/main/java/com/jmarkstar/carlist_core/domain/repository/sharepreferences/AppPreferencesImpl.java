package com.jmarkstar.carlist_core.domain.repository.sharepreferences;

import android.content.SharedPreferences;
import javax.inject.Inject;

/**
 * Created by jmarkstar on 25/08/2017.
 */
public class AppPreferencesImpl implements AppPreferences {

    private static final String MANUFACTURER_PAGE = "manufacturer_page";
    private static final String MANUFACTURER_MAX_PAGES = "manufacturer_max_pages";
    private static final String MAIN_TYPE_PAGE_PREFIX = "maintype_page_";
    private static final String MAIN_TYPE_MAX_PAGES_PREFIX = "maintype_max_pages_";

    @Inject SharedPreferences mSharedPreferences;

    @Inject AppPreferencesImpl(){}

    public void saveMainTypesPage(String manufacturer, int page){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(MAIN_TYPE_PAGE_PREFIX+manufacturer, page);
        editor.apply();
    }

    public int getMainTypesPage(String manufacturer){
        return mSharedPreferences.getInt(MAIN_TYPE_PAGE_PREFIX+manufacturer, 0);
    }

    public void saveMainTypesTotalPageCount(String manufacturer, int page){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(MAIN_TYPE_MAX_PAGES_PREFIX+manufacturer, page);
        editor.apply();
    }

    public int getMainTypesTotalPageCount(String manufacturer){
        return mSharedPreferences.getInt(MAIN_TYPE_MAX_PAGES_PREFIX+manufacturer, 0);
    }

    public void saveManufacturerPage(int page){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(MANUFACTURER_PAGE, page);
        editor.apply();
    }

    public int getManufacturerPage(){
        return mSharedPreferences.getInt(MANUFACTURER_PAGE, 0);
    }

    public void saveManufacturerTotalPageCount(int page){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(MANUFACTURER_MAX_PAGES, page);
        editor.apply();
    }

    public int getManufacturerTotalPageCount(){
        return mSharedPreferences.getInt(MANUFACTURER_MAX_PAGES, 0);
    }

    @Override public void deleteAll() {
        mSharedPreferences.edit().clear().apply();
    }

}
