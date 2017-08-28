package com.jmarkstar.carlist_core.domain.repository.database.entity;

/**
 * Created by jmarkstar on 27/08/2017.
 */

public enum ItemType {
    MANUFACTURER(0), MAIN_TYPE(1), BUILT_DATE(2);

    private final int value;

    private ItemType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
