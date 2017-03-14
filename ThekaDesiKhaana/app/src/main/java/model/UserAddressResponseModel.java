package model;

import java.util.List;

/**
 * Created by ParmeshMahore on 3/5/17.
 */

public class UserAddressResponseModel {

    List<UserAddressModel> userList;

    public List<UserAddressModel> getUserList() {
        return userList;
    }

    public void setUserList(List<UserAddressModel> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "UserAddressResponseModel{" +
                "userList=" + userList +
                '}';
    }
}
