package com.jmarkstar.carlist_core.domain.repository.sharepreferences;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by jmarkstar on 25/08/2017.
 */

public class AppPreferences {

    private static final String MANUFACTURER_PAGE = "manufacturer_page";
    private static final String MANUFACTURER_NEXT_PAGE = "manufacturer_next_page";
    private static final String MANUFACTURER_MAX_PAGES = "manufacturer_max_pages";

    @Inject SharedPreferences mSharedPreferences;

    @Inject AppPreferences(){}

    public void saveManufacturerPage(int page){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(MANUFACTURER_PAGE, page);
        editor.apply();
    }

    public int getManufacturerPage(){
        return mSharedPreferences.getInt(MANUFACTURER_PAGE, 0);
    }

    public void saveManufacturerNextPage(int page){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(MANUFACTURER_NEXT_PAGE, page);
        editor.apply();
    }

    public int getManufacturerNextPage(){
        return mSharedPreferences.getInt(MANUFACTURER_NEXT_PAGE, 0);
    }

    public void saveManufacturerMaxPages(int page){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(MANUFACTURER_MAX_PAGES, page);
        editor.apply();
    }

    public int getManufacturerMaxPages(){
        return mSharedPreferences.getInt(MANUFACTURER_MAX_PAGES, 0);
    }

}
