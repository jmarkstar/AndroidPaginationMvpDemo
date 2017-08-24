package com.jmarkstar.carlist_core.domain.model;

import java.util.HashMap;

/**
 * Created by jmarkstar on 24/08/2017.
 */

public class BuiltDatesModel {

    private HashMap<String, String> builtDates;

    public HashMap<String, String> getBuiltDates() {
        return builtDates;
    }

    public void setBuiltDates(HashMap<String, String> builtDates) {
        this.builtDates = builtDates;
    }

    @Override
    public String toString() {
        return "BuiltDatesModel{" +
                "builtDates=" + builtDates +
                '}';
    }
}
