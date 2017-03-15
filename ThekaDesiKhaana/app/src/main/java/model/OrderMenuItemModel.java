package model;

/**
 * Created by ParmeshMahore on 3/14/17.
 */

public class OrderMenuItemModel {

    private String menuItem;

    private String quantity;

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderMenuItemModel [menuItem = " + menuItem + ", quantity = " + quantity + "]";
    }
}
