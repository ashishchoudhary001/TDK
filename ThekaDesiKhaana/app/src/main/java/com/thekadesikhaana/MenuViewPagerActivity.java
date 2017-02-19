package com.thekadesikhaana;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thekadesikhaana.adapter.CheckOutActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import api.APIClient;
import api.APIInterface;
import model.FoodType;
import model.FoodTypeResponseModel;
import model.OrderModel;
import model.UserProfileModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuViewPagerActivity extends AppCompatActivity {

    private static final String TAG = MenuViewPagerActivity.class.getSimpleName();
    HashMap<String, Integer> cart = new HashMap<>();

    private ViewPager viewPager;

    private DrawerLayout drawerLayout;

    private Toolbar toolbar;

    private TabLayout tabLayout;
    private List<FoodType> mFoodList;

    private TextView tvUserName;
    private TextView tvUserEmailID;
    private UserProfileModel mUserProfile;

    private RelativeLayout mViewPagerContainer;

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

        mViewPagerContainer = (RelativeLayout) findViewById(R.id.tabContainer);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                FoodType foodType = mFoodList.get(position);
                Log.d(TAG, "FOOD TYPE:" + foodType);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //paymentInterface.makePayment(100);
                //Log.d(TAG, "ORDER:"+ OrderModel.getInstance().getMenuItems());

                if (OrderModel.getInstance().getMenuItems().size() > 0) {
                    if(isUserLoggedIn()) {
                        Intent intent = new Intent(MenuViewPagerActivity.this, SignInActivity2.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MenuViewPagerActivity.this, SignInActivity.class);
                        startActivity(intent);
                    }

                } else {
                    Snackbar.make(view, "Please Add Item(s) in Cart.", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    private boolean isUserLoggedIn() {
        //ToDo: write code to check user is logged in or not
        return false;
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
        tvUserName = (TextView) header.findViewById(R.id.tv_username);
        tvUserEmailID = (TextView) header.findViewById(R.id.tv_email_id);


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

    private void fetchUserProfile() {
        APIInterface apiService =
                APIClient.getClient().create(APIInterface.class);

        Call<UserProfileModel> call = apiService.getUserProfile();
        call.enqueue(new Callback<UserProfileModel>() {
            @Override
            public void onResponse(Call<UserProfileModel> call, Response<UserProfileModel> response) {
                Log.d(TAG, "onResponse:" + response.body());
                updateUserProfile(response.body());
            }

            @Override
            public void onFailure(Call<UserProfileModel> call, Throwable t) {
                Toast.makeText(MenuViewPagerActivity.this, "Network Error, Please Try Again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUserProfile(UserProfileModel body) {
        mUserProfile = body;
        tvUserEmailID.setText(mUserProfile.getEmail());
        tvUserName.setText(mUserProfile.getName());
    }

    private void buildPage(FoodTypeResponseModel responseModel) {
        MenuFragmentPagerAdapter pagerAdapter = new MenuFragmentPagerAdapter(getSupportFragmentManager(), responseModel);
        mFoodList = responseModel.getFoodType();
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(pagerAdapter);
    }

}
