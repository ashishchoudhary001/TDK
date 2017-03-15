package model;

/**
 * Created by ParmeshMahore on 3/14/17.
 */

public class NewAddressRequestModel {
    private String landmark;

    private String pinCode;

    private String name;

    private String userId;

    private String mobileNumber;

    private String addressLine2;

    private String addressLine1;

    private String addressId;

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "NewAddressRequestModel [landmark = " + landmark + ", pinCode = " + pinCode + ", name = " + name + ", userId = " + userId + ", mobileNumber = " + mobileNumber + ", addressLine2 = " + addressLine2 + ", addressLine1 = " + addressLine1 + ", addressId = " + addressId + "]";
    }
}
