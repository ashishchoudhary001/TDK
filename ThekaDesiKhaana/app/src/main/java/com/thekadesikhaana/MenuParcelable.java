package com.thekadesikhaana;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by ashishchoudhary on 05/02/17.
 */

public class MenuParcelable implements Parcelable{

    public ArrayList<MenuItemModel> menuItem;

    public MenuParcelable(){
        menuItem= new ArrayList<MenuItemModel>();
    }

    public MenuParcelable(Parcel in) {
        menuItem= new ArrayList<MenuItemModel>();
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(menuItem);
    }

    private void readFromParcel(Parcel in) {
        menuItem = new ArrayList<MenuItemModel>();
        in.readList(menuItem, MenuItemModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<MenuParcelable> CREATOR
            = new Parcelable.Creator<MenuParcelable>() {
        public MenuParcelable createFromParcel(Parcel in) {
            return new MenuParcelable(in);
        }

        public MenuParcelable[] newArray(int size) {
            return new MenuParcelable[size];
        }
    };
}
