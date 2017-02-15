package model;

/**
 * Created by ParmeshMahore on 2/15/17.
 */

public class Wallet {

    private String walletBalance;

    private String promotionalBalance;

    public String getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(String walletBalance) {
        this.walletBalance = walletBalance;
    }

    public String getPromotionalBalance() {
        return promotionalBalance;
    }

    public void setPromotionalBalance(String promotionalBalance) {
        this.promotionalBalance = promotionalBalance;
    }

    @Override
    public String toString() {
        return "Wallet [walletBalance = " + walletBalance + ", promotionalBalance = " + promotionalBalance + "]";
    }
}
