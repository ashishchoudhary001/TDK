package model;

import java.util.List;

/**
 * Created by ParmeshMahore on 3/15/17.
 */
public class OrderDetailModel {

    private String status;

    private AddressData address;

    private String orderDate;

    private String specialRequest;

    private List<MenuItems> orderItems;

    private String user;

    private String orderId;

    private String totalPrice;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AddressData getAddress() {
        return address;
    }

    public void setAddress(AddressData address) {
        this.address = address;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    public List<MenuItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<MenuItems> orderItems) {
        this.orderItems = orderItems;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderDetailModel [status = " + status + ", address = " + address + ", orderDate = " + orderDate + ", specialRequest = " + specialRequest + ", orderItems = " + orderItems + ", user = " + user + ", orderId = " + orderId + ", totalPrice = " + totalPrice + "]";
    }
}
