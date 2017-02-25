package com.thekadesikhaana.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.thekadesikhaana.Constant;
import com.thekadesikhaana.R;

import java.util.HashMap;
import java.util.List;

import model.MenuItems;

/**
 * Created by ParmeshMahore on 2/24/17.
 */
public class ConfirmMenuItemListAdapter extends BaseAdapter {

    private HashMap<String, List<MenuItems>> mMenuItemList;
    private Context mContext;

    public ConfirmMenuItemListAdapter(Context context, HashMap<String, List<MenuItems>> data) {
        mContext = context;
        mMenuItemList = data;
    }

    @Override
    public int getCount() {
        return mMenuItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMenuItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.bengali_order_item_layout, parent, false);

            viewHolder.itemCateogryTV = (TextView) convertView.findViewById(R.id.menu_item_category);
            viewHolder.itemTypeTV = (TextView) convertView.findViewById(R.id.food_type);
            viewHolder.itemTypeIV = (ImageView) convertView.findViewById(R.id.menu_type_image_view);
            //viewHolder.removeItemImageView = (ImageView) convertView.findViewById(R.id.remove_item_iv);
            //viewHolder.addItemImageView = (ImageView) convertView.findViewById(R.id.add_item_iv);
            //viewHolder.itemCountEditText = (EditText) convertView.findViewById(R.id.item_count_et);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        List<MenuItems> menuItem = mMenuItemList.get(Constant.KEY_PUNJABI_VEG_LIST);

        viewHolder.itemCateogryTV.setText(menuItem.get(0).getCuisine().getName());
        String str = menuItem.get(0).getCuisine().getType();
        if(str.equalsIgnoreCase("V")) {
            viewHolder.itemTypeTV.setText(mContext.getString(R.string.item_veg));
            viewHolder.itemTypeIV.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.veg_icon));
        } else {
            viewHolder.itemTypeTV.setText(mContext.getString(R.string.item_non_veg));
            viewHolder.itemTypeIV.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.non_veg_icon));
        }

        return convertView;
    }

    private class ViewHolder {
        TextView itemCateogryTV;
        TextView itemTypeTV;
        ImageView itemTypeIV;
        ImageView removeItemImageView;
        ImageView addItemImageView;
        EditText itemCountEditText;
    }
}
