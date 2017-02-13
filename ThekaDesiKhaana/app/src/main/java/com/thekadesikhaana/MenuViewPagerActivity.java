package com.thekadesikhaana;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import api.APIClient;
import api.APIInterface;
import model.FoodTypeResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuViewPagerActivity extends AppCompatActivity {

    private static final String TAG = MenuViewPagerActivity.class.getSimpleName();
    HashMap<String, Integer> cart = new HashMap<>();

    private ViewPager viewPager;

    private DrawerLayout drawerLayout;

    private Toolbar toolbar;

    private MenuFragmentPagerAdapter pagerAdapter;

    private List<DataUpdateListener> mListeners;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        setSupportActionBar(toolbar);
        initNavigationDrawer();

        //API CALL
        fetchFoodItemList();

        mListeners = new ArrayList<>();

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//
                //dataUpdated();
                Log.d(TAG, "ON TAB SELECTED");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Used to add item to Cart", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public synchronized void registerDataUpdateListener(DataUpdateListener listener) {
        mListeners.add(listener);
    }

    public synchronized void unregisterDataUpdateListener(DataUpdateListener listener) {
        mListeners.remove(listener);
    }

    public synchronized void dataUpdated() {
        for (DataUpdateListener listener : mListeners) {
            listener.onDataUpdate(tabLayout.getSelectedTabPosition());
        }
    }


    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {
                    case R.id.nav_orders:
                        Toast.makeText(getApplicationContext(), "My Orders", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_account:
                        Toast.makeText(getApplicationContext(), "My Account", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_invite:
                        Toast.makeText(getApplicationContext(), "Invite n Earn", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_feedback:
                        Toast.makeText(getApplicationContext(), "Feedback", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_faqs:
                        Toast.makeText(getApplicationContext(), "FAQs", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_logout:
                        drawerLayout.closeDrawers();
                        finish();

                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
        TextView tv_email = (TextView) header.findViewById(R.id.tv_email);
        tv_email.setText("ankit.nitks@gmail.com");


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    public interface DataUpdateListener {
        void onDataUpdate(int index);
    }


    private void fetchFoodItemList() {
        APIInterface apiService =
                APIClient.getClient().create(APIInterface.class);

        Call<FoodTypeResponseModel> call = apiService.getMenuList();
        call.enqueue(new Callback<FoodTypeResponseModel>() {
            @Override
            public void onResponse(Call<FoodTypeResponseModel> call, Response<FoodTypeResponseModel> response) {
                Log.d(TAG, "" + response.body());
                buildPage(response.body());
            }

            @Override
            public void onFailure(Call<FoodTypeResponseModel> call, Throwable t) {
                Toast.makeText(MenuViewPagerActivity.this, "Network Error, Please Try Again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buildPage(FoodTypeResponseModel responseModel) {
        pagerAdapter = new MenuFragmentPagerAdapter(getSupportFragmentManager(), responseModel);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(pagerAdapter);
    }

}
