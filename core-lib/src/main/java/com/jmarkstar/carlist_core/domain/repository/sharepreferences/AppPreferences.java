package com.jmarkstar.carlist_core.domain.repository.sharepreferences;

/**
 * Created by jmarkstar on 26/08/2017.
 */

public interface AppPreferences {

    void saveMainTypesPage(String manufacturer, int page);
    int getMainTypesPage(String manufacturer);
    void saveMainTypesTotalPageCount(String manufacturer, int page);
    int getMainTypesTotalPageCount(String manufacturer);

    void saveManufacturerPage(int page);
    int getManufacturerPage();
    void saveManufacturerTotalPageCount(int page);
    int getManufacturerTotalPageCount();

    void deleteAll();
}
