package com.thekadesikhaana;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ashishchoudhary on 05/02/17.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder>  {


    private ArrayList<MenuItemModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView menuContent;
        ImageView menuType;
        ImageView menuImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.menuContent = (TextView) itemView.findViewById(R.id.menu_content);
            this.menuType = (ImageView) itemView.findViewById(R.id.menu_type);
            this.menuImage = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public MenuAdapter(ArrayList<MenuItemModel> data) {
        this.dataSet = data;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_card, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView menuContent = holder.menuContent;
        ImageView menuType = holder.menuType;
        ImageView menuImage = holder.menuImage;

        menuContent.setText(dataSet.get(listPosition).getMenuContent());

        int menuTypeImage = 0;
        if(dataSet.get(listPosition).getMenuType()== true)
        {
            menuTypeImage = R.drawable.app_icon;
        }
        else
        {
            menuTypeImage = R.drawable.drawer_icon;
        }

        menuType.setImageResource(menuTypeImage);

        menuImage.setImageBitmap(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
