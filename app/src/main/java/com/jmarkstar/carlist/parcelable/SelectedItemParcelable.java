package com.jmarkstar.carlist.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jmarkstar on 26/08/2017.
 */

public class SelectedItemParcelable implements Parcelable {

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
        return "SelectedItemParcelable{" +
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

    public SelectedItemParcelable() {
    }

    protected SelectedItemParcelable(Parcel in) {
        this.number = in.readString();
        this.name = in.readString();
    }

    public static final Creator<SelectedItemParcelable> CREATOR = new Creator<SelectedItemParcelable>() {
        @Override
        public SelectedItemParcelable createFromParcel(Parcel source) {
            return new SelectedItemParcelable(source);
        }

        @Override
        public SelectedItemParcelable[] newArray(int size) {
            return new SelectedItemParcelable[size];
        }
    };
}
