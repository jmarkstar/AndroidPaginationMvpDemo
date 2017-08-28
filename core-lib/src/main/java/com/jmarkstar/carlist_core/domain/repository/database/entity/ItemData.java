package com.jmarkstar.carlist_core.domain.repository.database.entity;

/** Temporary data.
 * Created by jmarkstar on 27/08/2017.
 */
public class ItemData {

    public static final String TABLE_NAME = "item";
    public static final String ID_FIELD = "id";
    public static final String NUMBER_FIELD = "number";
    public static final String NAME_FIELD = "name";
    public static final String MANUFACTURER_FIELD = "manufacturer";
    public static final String TYPE_FIELD = "type";

    private Integer id;
    private String number;
    private String name;
    private String manufacturer;
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "ItemData{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", type=" + type +
                '}';
    }
}
