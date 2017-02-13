package com.thekadesikhaana.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thekadesikhaana.R;

import java.util.List;

import model.MenuItems;

/**
 * Created by ashishchoudhary on 05/02/17.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {


    private static final String TAG = MenuAdapter.class.getSimpleName();
    private List<MenuItems> dataSet;

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView menuContent;
        ImageView menuType;
        ImageView menuImage;

        MyViewHolder(View itemView) {
            super(itemView);
            this.menuContent = (TextView) itemView.findViewById(R.id.menu_content);
            this.menuType = (ImageView) itemView.findViewById(R.id.menu_type);
            this.menuImage = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public MenuAdapter(List<MenuItems> data) {
        this.dataSet = data;
        Log.d(TAG, "MENU ADAPTER CONSTRUCTOR :"+data);
        //notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_card, parent, false);
        Log.d(TAG, "ON CREATE VIEW HOLDER");
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView menuContent = holder.menuContent;
        ImageView menuType = holder.menuType;
        ImageView menuImage = holder.menuImage;

        menuContent.setText(dataSet.get(listPosition).getItems());

        int menuTypeImage;
        /*if (dataSet.get(listPosition).getMenuType() == true) {
            menuTypeImage = R.drawable.app_icon;
        } else {
            menuTypeImage = R.drawable.drawer_icon;
        }*/
        menuTypeImage = R.drawable.app_icon;

        menuType.setImageResource(menuTypeImage);
        Log.d(TAG, "IMAGE URL:" + dataSet.get(listPosition).getUrlMobile());

        //menuImage.setImageBitmap(dataSet.get(listPosition).getUrlMobile());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
