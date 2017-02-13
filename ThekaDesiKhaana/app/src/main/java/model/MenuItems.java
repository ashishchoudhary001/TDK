package model;

/**
 * Created by ParmeshMahore on 2/7/17.
 */

public class MenuItems {

    private String id;

    private String price;

    private Cuisine cuisine;

    private String items;

    private String urlTablet;

    private String urlMobile;

    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getUrlTablet() {
        return urlTablet;
    }

    public void setUrlTablet(String urlTablet) {
        this.urlTablet = urlTablet;
    }

    public String getUrlMobile() {
        return urlMobile;
    }

    public void setUrlMobile(String urlMobile) {
        this.urlMobile = urlMobile;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MenuItems [id = " + id + ", price = " + price + ", cuisine = " + cuisine + ", items = " + items + ", urlTablet = " + urlTablet + ", urlMobile = " + urlMobile + ", date = " + date + "]";
    }
}
