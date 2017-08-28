package com.jmarkstar.carlist_core.domain.model;

import android.support.annotation.NonNull;

/**
 * Created by jmarkstar on 26/08/2017.
 */
public class ItemModel implements Comparable<ItemModel> {

    private String number;
    private String name;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override public String toString() {
        return "ItemModel{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }

    @Override public int compareTo(@NonNull ItemModel itemModel) {
        return getName().compareTo(itemModel.getName());
    }
}
