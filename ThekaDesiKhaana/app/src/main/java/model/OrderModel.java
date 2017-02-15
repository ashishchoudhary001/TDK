package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by parmesh.mahore on 2/15/2017.
 */

public class OrderModel {
    private static List<MenuItems> menuItems;
    private static OrderModel INSTANCE;

    private OrderModel() {
    }

    public static synchronized OrderModel getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new OrderModel();
            menuItems = new ArrayList<>();
        }
        return INSTANCE;
    }

    public void setMenuItems(List<MenuItems> menuItems) {
        this.menuItems = menuItems;
    }

    public List<MenuItems> getMenuItems() {
        return menuItems;
    }

    public void clearMenuItems() {
        if(menuItems != null) {
            menuItems.clear();
        }
    }
}