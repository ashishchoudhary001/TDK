package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ParmeshMahore on 2/15/17.
 */

public class UserProfileModel implements Parcelable{

    private String phone;

    private AccessTokens accessTokens;

    private String email;

    private String dob;

    private String referralCode;

    private String name;

    private String gender;

    private String refereeCode;

    private Wallet wallet;

    public UserProfileModel() {

    }

    protected UserProfileModel(Parcel in) {
        phone = in.readString();
        email = in.readString();
        dob = in.readString();
        referralCode = in.readString();
        name = in.readString();
        gender = in.readString();
        refereeCode = in.readString();
        wallet = (Wallet) in.readSerializable();
    }

    public static final Creator<UserProfileModel> CREATOR = new Creator<UserProfileModel>() {
        @Override
        public UserProfileModel createFromParcel(Parcel in) {
            return new UserProfileModel(in);
        }

        @Override
        public UserProfileModel[] newArray(int size) {
            return new UserProfileModel[size];
        }
    };

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AccessTokens getAccessTokens() {
        return accessTokens;
    }

    public void setAccessTokens(AccessTokens accessTokens) {
        this.accessTokens = accessTokens;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRefereeCode() {
        return refereeCode;
    }

    public void setRefereeCode(String refereeCode) {
        this.refereeCode = refereeCode;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "UserProfileModel [phone = " + phone + ", accessTokens = " + accessTokens + ", email = " + email + ", dob = " + dob + ", referralCode = " + referralCode + ", name = " + name + ", gender = " + gender + ", refereeCode = " + refereeCode + ", wallet = " + wallet + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(dob);
        dest.writeString(referralCode);
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeString(refereeCode);
        dest.writeSerializable(wallet);
    }
}
