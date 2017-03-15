package model;

/**
 * Created by ParmeshMahore on 3/14/17.
 */

public class ConfirmOrderResponseModel {

    private String paymentUrl;
    private String orderId;

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "ConfirmOrderResponseModel{" +
                "paymentUrl='" + paymentUrl + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
