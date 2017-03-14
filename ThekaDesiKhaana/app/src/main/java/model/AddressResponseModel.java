package model;

import java.util.List;

/**
 * Created by ParmeshMahore on 3/12/17.
 */

public class AddressResponseModel {

    private String message;

    private List<AddressData> data;

    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AddressData> getData() {
        return data;
    }

    public void setData(List<AddressData> data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "AddressResponseModel [message = " + message + ", data = " + data + ", success = " + success + "]";
    }
}
