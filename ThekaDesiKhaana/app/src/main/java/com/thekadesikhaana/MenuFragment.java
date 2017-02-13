package com.thekadesikhaana;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thekadesikhaana.adapter.MenuAdapter;

import java.util.List;

import model.Bengali;
import model.IFoodType;
import model.MenuItems;
import model.NorthIndian;
import model.Odia;
import model.Punjabi;

/**
 * Created by ashishchoudhary on 05/02/17.
 */
public class MenuFragment extends Fragment implements MenuViewPagerActivity.DataUpdateListener {
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String ARG_MENU = "ARG_MENU";
    private static final String TAG = MenuFragment.class.getSimpleName();

    //private int mPage;

    private List<MenuItems> menuItems;

    private static RecyclerView.Adapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView recyclerView;

    //private static ArrayList<MenuItems> data;

    MenuViewPagerActivity menuViewPagerActivity;
    private static IFoodType mFoodStyle;
    private static MenuFragment INSTANCE;

    @Override
    public void onDataUpdate(int index) {
        //adapter.notifyDataSetChanged();
    }

    public static MenuFragment newInstance() {
        //if (null == INSTANCE) {
            INSTANCE = new MenuFragment();
        //}
        return INSTANCE;
    }

    public void setFoodStyle(IFoodType foodStyle) {
        mFoodStyle = foodStyle;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.menu_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //data = menuItems.menuItem;


        if(mFoodStyle instanceof Bengali) {
            Log.d(TAG, "FoodStyle Bengali");
            menuItems = ((Bengali) mFoodStyle).getMenuItems();
        } else if(mFoodStyle instanceof NorthIndian) {
            Log.d(TAG, "FoodStyle North Indian");
            menuItems = ((NorthIndian) mFoodStyle).getMenuItems();
        } else if(mFoodStyle instanceof Odia) {
            Log.d(TAG, "FoodStyle Odia");
            menuItems = ((Odia) mFoodStyle).getMenuItems();
        } else if(mFoodStyle instanceof Punjabi) {
            Log.d(TAG, "FoodStyle Punjabi");
            menuItems = ((Punjabi) mFoodStyle).getMenuItems();
        }

        adapter = new MenuAdapter(menuItems);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.d(TAG, "ON-ATTACH");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //((MenuViewPagerActivity) getActivity()).unregisterDataUpdateListener(this);
    }
}
