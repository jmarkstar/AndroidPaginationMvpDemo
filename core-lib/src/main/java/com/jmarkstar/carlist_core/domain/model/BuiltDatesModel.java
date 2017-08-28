package com.jmarkstar.carlist_core.domain.model;

import java.util.List;

/**
 * Created by jmarkstar on 24/08/2017.
 */

public class BuiltDatesModel {

    private List<ItemModel> builtDates;

    public List<ItemModel> getBuiltDates() {
        return builtDates;
    }

    public void setBuiltDates(List<ItemModel> builtDates) {
        this.builtDates = builtDates;
    }

    @Override
    public String toString() {
        return "BuiltDatesModel{" +
                "builtDates=" + builtDates +
                '}';
    }
}
