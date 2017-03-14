package model;

import android.util.Log;

import com.thekadesikhaana.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by parmesh.mahore on 2/15/2017.
 */

public class OrderModel {
    private static List<MenuItems> mMenuItemsList;
    private static OrderModel INSTANCE;

    private List<MenuItems> mVegBengaliList = new ArrayList<>();
    private List<MenuItems> mVegNorthIndianList = new ArrayList<>();
    private List<MenuItems> mVegOdiaList = new ArrayList<>();
    private List<MenuItems> mVegPunjabiList = new ArrayList<>();

    private List<MenuItems> mNonVegBengaliList = new ArrayList<>();
    private List<MenuItems> mNonVegNorthIndianList = new ArrayList<>();
    private List<MenuItems> mNonVegOdiaList = new ArrayList<>();
    private List<MenuItems> mNonVegPunjabiList = new ArrayList<>();

    private static String TAG = OrderModel.class.getSimpleName();

    private OrderModel() {
    }

    public static synchronized OrderModel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderModel();
            mMenuItemsList = new ArrayList<>();
        }
        return INSTANCE;
    }

    public void setMenuItems(List<MenuItems> menuItems) {
        this.mMenuItemsList = menuItems;
    }

    public List<MenuItems> getMenuItems() {
        return mMenuItemsList;
    }

    public void clearMenuItems() {
        if (mMenuItemsList != null) {
            mMenuItemsList.clear();
        }
    }

    // TODO: 2/25/17 Need to refactor it
    public HashMap<String, List<MenuItems>> getGroupedList(List<MenuItems> menuItemsList) {

        for (MenuItems item : menuItemsList) {
            Cuisine cuisine = item.getCuisine();

            if (cuisine.getName().equalsIgnoreCase(Constant.FOOD_TYPE_BENGALI)) {
                if (cuisine.getType().equalsIgnoreCase("v")) {
                    mVegBengaliList.add(item);
                } else {
                    mNonVegBengaliList.add(item);
                }
            } else if (cuisine.getName().equalsIgnoreCase(Constant.FOOD_TYPE_NORTH_INDIAN)) {
                if (cuisine.getType().equalsIgnoreCase("v")) {
                    mVegNorthIndianList.add(item);
                } else {
                    mNonVegNorthIndianList.add(item);
                }
            } else if (cuisine.getName().equalsIgnoreCase(Constant.FOOD_TYPE_ODIA)) {
                if (cuisine.getType().equalsIgnoreCase("v")) {
                    mVegOdiaList.add(item);
                } else {
                    mNonVegOdiaList.add(item);
                }
            } else if (cuisine.getName().equalsIgnoreCase(Constant.FOOD_TYPE_PUNJABI)) {
                if (cuisine.getType().equalsIgnoreCase("v")) {
                    mVegPunjabiList.add(item);
                } else {
                    mNonVegPunjabiList.add(item);
                }
            }
        }

        HashMap<String, List<MenuItems>> itemList = new HashMap<>();
        if (mVegBengaliList.size() > 0)
            itemList.put(Constant.KEY_BENGALI_VEG_LIST, mVegBengaliList);
        if (mNonVegBengaliList.size() > 0)
            itemList.put(Constant.KEY_BENGALI_NONVEG_LIST, mNonVegBengaliList);
        if (mVegNorthIndianList.size() > 0)
            itemList.put(Constant.KEY_NORTH_VEG_INDIAN_LIST, mVegNorthIndianList);
        if (mNonVegNorthIndianList.size() > 0)
            itemList.put(Constant.KEY_NORTH_NONVEG_INDIAN_LIST, mNonVegNorthIndianList);
        if (mVegOdiaList.size() > 0)
            itemList.put(Constant.KEY_ODIA_VEG_LIST, mVegOdiaList);
        if (mNonVegOdiaList.size() > 0)
            itemList.put(Constant.KEY_ODIA_NONVEG_LIST, mNonVegOdiaList);
        if (mVegPunjabiList.size() > 0)
            itemList.put(Constant.KEY_PUNJABI_VEG_LIST, mVegPunjabiList);
        if (mNonVegPunjabiList.size() > 0)
            itemList.put(Constant.KEY_PUNJABI_NONVEG_LIST, mNonVegPunjabiList);

        return itemList;
    }

    public void addOrUpdate(MenuItems menuItems) {
        if (mMenuItemsList.contains(menuItems)) {
            MenuItems item = getMenuItem(menuItems.getId());
            mMenuItemsList.remove(item);

            if (item != null) {
                item.setCount(menuItems.getCount());
                mMenuItemsList.add(item);
            }

        } else {
            mMenuItemsList.add(menuItems);
        }

        Log.d(TAG, "MENU ITEM: "+menuItems.getId() +" name: "+menuItems.getCuisine().getName());
    }

    private MenuItems getMenuItem(String id) {

        for (MenuItems item : mMenuItemsList) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }
}