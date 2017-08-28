package com.jmarkstar.carlist.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jmarkstar on 26/08/2017.
 */

public class ItemParcelable implements Parcelable {

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

    @Override
    public String toString() {
        return "ItemParcelable{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.number);
        dest.writeString(this.name);
    }

    public ItemParcelable() {
    }

    protected ItemParcelable(Parcel in) {
        this.number = in.readString();
        this.name = in.readString();
    }

    public static final Creator<ItemParcelable> CREATOR = new Creator<ItemParcelable>() {
        @Override
        public ItemParcelable createFromParcel(Parcel source) {
            return new ItemParcelable(source);
        }

        @Override
        public ItemParcelable[] newArray(int size) {
            return new ItemParcelable[size];
        }
    };
}
