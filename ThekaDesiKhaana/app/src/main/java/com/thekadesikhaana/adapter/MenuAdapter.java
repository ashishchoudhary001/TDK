package com.thekadesikhaana.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thekadesikhaana.MenuFragment;
import com.thekadesikhaana.R;

import java.util.List;

import model.MenuItems;
import model.OrderModel;

/**
 * Created by ashishchoudhary on 05/02/17.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {


    private static final String TAG = MenuAdapter.class.getSimpleName();
    private List<MenuItems> dataSet;
    private Context mContext;

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView menuContent;
        ImageView menuType;
        ImageView menuImage;
        TextView menuPrice;
        Button addItemButton;
        Button removeItemButton;
        int counter = 1;
        int totalPrice = 1;
        int selectedItem = 0;

        MyViewHolder(View itemView) {
            super(itemView);
            menuContent = (TextView) itemView.findViewById(R.id.menu_content);
            menuType = (ImageView) itemView.findViewById(R.id.menu_type);
            menuImage = (ImageView) itemView.findViewById(R.id.main_image);
            menuPrice = (TextView) itemView.findViewById(R.id.tv_price);
            addItemButton = (Button) itemView.findViewById(R.id.btn_add);
            removeItemButton = (Button) itemView.findViewById(R.id.btn_remove);
        }
    }

    public MenuAdapter(Context context, List<MenuItems> data) {
        this.dataSet = data;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView menuContent = holder.menuContent;
        ImageView menuType = holder.menuType;
        ImageView menuImage = holder.menuImage;
        final TextView priceTextView = holder.menuPrice;
        Button addItemButton = holder.addItemButton;
        final Button removeItemButton = holder.removeItemButton;

        final MenuItems menuItems = dataSet.get(listPosition);

        Picasso.with(mContext).
                load(menuItems.getUrlMobile())
                .placeholder(R.drawable.app_icon)
                .resize(900, 900)
                .into(menuImage);

        if(menuItems.getCuisine().getType().equalsIgnoreCase("v")) {
            menuType.setImageDrawable(mContext.getResources().getDrawable(R.drawable.vegicon));
        } else {
            menuType.setImageDrawable(mContext.getResources().getDrawable(R.drawable.nonveg_icon));
        }
        menuContent.setText(menuItems.getItems());
        int price = getPrice(Integer.parseInt(menuItems.getPrice()), holder, true);
        String str = price +" Rs";
        priceTextView.setText(str);

        if(holder.selectedItem > 1) {
            removeItemButton.setVisibility(View.VISIBLE);
        }

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int price = getPrice(Integer.parseInt(menuItems.getPrice()), holder, true);
                String str = price +" Rs";
                priceTextView.setText(str);
                holder.selectedItem = holder.selectedItem + 1;
                if(holder.selectedItem >= 1) {
                    removeItemButton.setVisibility(View.VISIBLE);
                }

                OrderModel.getInstance().getMenuItems().add(menuItems);
            }
        });

        removeItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int price = getPrice(Integer.parseInt(menuItems.getPrice()), holder, false);

                String str = price +" Rs";
                priceTextView.setText(str);

                holder.selectedItem -= 1;
                if(holder.selectedItem < 1) {
                    removeItemButton.setVisibility(View.INVISIBLE);
                }

                OrderModel.getInstance().getMenuItems().remove(menuItems);
            }
        });
    }

    private int getPrice(int price, MyViewHolder holder, boolean isAdd) {

        if (isAdd) {
            holder.totalPrice = (price * (holder.counter++));
        } else {
            int counter = (--holder.counter);
            holder.totalPrice = (price * (--counter));
        }

        return holder.totalPrice;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
