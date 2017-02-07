package com.thekadesikhaana;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by ashishchoudhary on 05/02/17.
 */

public class MenuFragment extends Fragment implements MenuViewPagerActivity.DataUpdateListener {
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String ARG_MENU = "ARG_MENU";

    private int mPage;

    private MenuParcelable menuItems;

    private static RecyclerView.Adapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    private static RecyclerView recyclerView;

    private static ArrayList<MenuItemModel> data;

    MenuViewPagerActivity menuViewPagerActivity;

    @Override
    public void onDataUpdate(int index) {
//        adapter.notifyDataSetChanged();
//        Toast.makeText(getActivity(),""+index,Toast.LENGTH_SHORT).show();

//        for(int i = 0; i < data.size(); i++)
//        {
//            if(index == 0)
//            {
//                if(data.get(i).getId() > 4)
//                {
//                    data.remove(i);
//                }
//            }
//
//            if(index == 1)
//            {
//                if(data.get(i).getId() > 7)
//                {
//                    data.remove(i);
//                }else if(data.get(i).getId()<=4)
//                {
//                    data.remove(i);
//                }
//            }
//
//            if(index == 2)
//            {
//                if(data.get(i).getId()<=7)
//                {
//                    data.remove(i);
//                }
//            }
//
//
//
//        }

        adapter.notifyDataSetChanged();


    }

    public static MenuFragment newInstance(Bundle bundle) {
        MenuFragment fragment = new MenuFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        menuItems = getArguments().getParcelable(ARG_MENU);
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

        data = menuItems.menuItem;

//        Toast.makeText(getActivity(),""+mPage,Toast.LENGTH_SHORT).show();

        adapter = new MenuAdapter(data);
        recyclerView.setAdapter(adapter);

        menuViewPagerActivity.dataUpdated();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);



        if(context instanceof MenuViewPagerActivity){
            menuViewPagerActivity = (MenuViewPagerActivity)context;
            menuViewPagerActivity.registerDataUpdateListener(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MenuViewPagerActivity) getActivity()).unregisterDataUpdateListener(this);
    }
}
