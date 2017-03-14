package com.thekadesikhaana.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
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
        RelativeLayout itemCountLayout;
        TextView itemCountTV;

        int totalPrice = 0;
        int selectedItem = 0;

        MyViewHolder(View itemView) {
            super(itemView);
            menuContent = (TextView) itemView.findViewById(R.id.menu_content);
            menuType = (ImageView) itemView.findViewById(R.id.menu_type);
            menuImage = (ImageView) itemView.findViewById(R.id.main_image);
            menuPrice = (TextView) itemView.findViewById(R.id.tv_price);
            addItemButton = (Button) itemView.findViewById(R.id.btn_add);
            removeItemButton = (Button) itemView.findViewById(R.id.btn_remove);
            itemCountLayout = (RelativeLayout) itemView.findViewById(R.id.item_count_layout);
            itemCountTV = (TextView) itemView.findViewById(R.id.item_count_tv);
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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final TextView menuContent = holder.menuContent;
        ImageView menuType = holder.menuType;
        ImageView menuImage = holder.menuImage;
        final TextView priceTextView = holder.menuPrice;
        Button addItemButton = holder.addItemButton;
        final Button removeItemButton = holder.removeItemButton;

        final MenuItems menuItems = dataSet.get(position);

        Picasso.with(mContext).
                load(menuItems.getUrlMobile())
                .placeholder(R.drawable.app_icon)
                .resize(900, 900)
                .into(menuImage);

        if (menuItems.getCuisine().getType().equalsIgnoreCase("v")) {
            menuType.setImageDrawable(mContext.getResources().getDrawable(R.drawable.veg_icon));
        } else {
            menuType.setImageDrawable(mContext.getResources().getDrawable(R.drawable.non_veg_icon));
        }
        menuContent.setText(menuItems.getItems());

        String str = "₹ " + Integer.parseInt(menuItems.getPrice());
        priceTextView.setText(str);

        if (holder.selectedItem > 1) {
            removeItemButton.setVisibility(View.VISIBLE);
        }

        if (menuItems.getCount() > 0) {
            removeItemButton.setVisibility(View.VISIBLE);
            holder.itemCountLayout.setVisibility(View.VISIBLE);
            holder.itemCountTV.setText(String.valueOf(menuItems.getCount()));
        }

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemCountLayout.setVisibility(View.VISIBLE);
                Log.d(TAG, "Add item: " + holder.selectedItem);
                menuItems.setCount(menuItems.getCount() + 1);
                holder.itemCountTV.setText(String.valueOf(menuItems.getCount()));
                removeItemButton.setVisibility(View.VISIBLE);

                OrderModel.getInstance().addOrUpdate(menuItems);
            }
        });

        removeItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                menuItems.setCount(menuItems.getCount() - 1);
                holder.itemCountTV.setText(String.valueOf(menuItems.getCount()));

                if (menuItems.getCount() < 1) {
                    holder.itemCountLayout.setVisibility(View.INVISIBLE);
                    removeItemButton.setVisibility(View.GONE);
                }

                OrderModel.getInstance().addOrUpdate(menuItems);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
