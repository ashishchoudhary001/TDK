package com.thekadesikhaana.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.thekadesikhaana.R;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.Inflater;

import model.AddressData;
import model.AddressResponseModel;

/**
 * Created by ParmeshMahore on 3/5/17.
 */

public class AddressListAdapter extends BaseAdapter {

    private Context mContext;
    private List<AddressData> mAddressList;

    public AddressListAdapter(Context context) {
        mContext = context;
        mAddressList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mAddressList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAddressList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.address_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AddressData data = mAddressList.get(position);
        String address = data.getName() + " " + data.getAddressLine1() +" " +data.getAddressLine2()
                +" " +data.getLandmark() +" " + data.getPinCode();
        holder.addressTextView.setText(address);

        return convertView;
    }

    public void setData(List<AddressData> data) {
        mAddressList = data;
        notifyDataSetChanged();
    }

    private class ViewHolder {
        TextView addressTextView;

        ViewHolder(View view) {
            addressTextView = (TextView) view.findViewById(R.id.address_text_view);
        }
    }
}
