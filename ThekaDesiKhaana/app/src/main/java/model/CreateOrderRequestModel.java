package model;

import java.util.List;

/**
 * Created by ParmeshMahore on 3/14/17.
 */

public class CreateOrderRequestModel {

    private String userId;

    private String walletCashCut;

    private String isCOD;

    private List<OrderMenuItemModel> menuItems;

    private String addressId;

    private String promotionalWalletCut;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWalletCashCut() {
        return walletCashCut;
    }

    public void setWalletCashCut(String walletCashCut) {
        this.walletCashCut = walletCashCut;
    }

    public String getIsCOD() {
        return isCOD;
    }

    public void setIsCOD(String isCOD) {
        this.isCOD = isCOD;
    }

    public List<OrderMenuItemModel> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<OrderMenuItemModel> menuItems) {
        this.menuItems = menuItems;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getPromotionalWalletCut() {
        return promotionalWalletCut;
    }

    public void setPromotionalWalletCut(String promotionalWalletCut) {
        this.promotionalWalletCut = promotionalWalletCut;
    }

    @Override
    public String toString() {
        return "CreateOrderRequestModel [userId = " + userId + ", walletCashCut = " + walletCashCut + ", isCOD = " + isCOD + ", menuItems = " + menuItems + ", addressId = " + addressId + ", promotionalWalletCut = " + promotionalWalletCut + "]";
    }
}
