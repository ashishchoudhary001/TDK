package com.thekadesikhaana;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by ashishchoudhary on 05/02/17.
 */

public class MenuItemModel implements Parcelable{
    String menuContent;
    Boolean menuType;
    int id;
    byte[] image;

    public MenuItemModel(String menuContent, Boolean menuType, int id, byte[] image) {
        this.menuContent = menuContent;
        this.menuType = menuType;
        this.id = id;
        this.image=image;
    }


    public String getMenuContent() {
        return menuContent;
    }


    public Boolean getMenuType() {
        return menuType;
    }

    public int getId() {
        return id;
    }

    public Bitmap getImage() {
        return convert(image);
    }

    public static byte[] convert(Bitmap bitmap) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] array = stream.toByteArray();
        stream.close();
        return array;


    }
    public static Bitmap convert(byte[] array) {
        return BitmapFactory.decodeByteArray(array,0,array.length);
    }

    public MenuItemModel(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(menuContent);
        dest.writeByte((byte) (menuType ? 1 : 0));
        dest.writeInt(id);
        dest.writeInt(image.length);
        dest.writeByteArray(image);
    }

    public void readFromParcel(Parcel in){
        this.menuContent = in.readString();
        this.menuType = in.readByte() != 0;
        this.id = in.readInt();
        this.image = new byte[in.readInt()];
        in.readByteArray(this.image);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MenuItemModel createFromParcel(Parcel in) {
            return new MenuItemModel(in);
        }

        public MenuItemModel[] newArray(int size) {
            return new MenuItemModel[size];
        }
    };
}
