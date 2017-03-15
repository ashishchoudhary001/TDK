package api;


import model.AddressData;
import model.AddressResponseModel;
import model.ConfirmOrderResponseModel;
import model.CreateOrderRequestModel;
import model.FoodTypeResponseModel;
import model.NewAddressRequestModel;
import model.OrderDetailModel;
import model.UserAddressResponseModel;
import model.UserProfileModel;
import model.UserRequestModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ParmeshMahore on 2/7/17.
 */
public interface APIInterface {

    @GET("/thekadesi/menu/today/")
    Call<FoodTypeResponseModel> getMenuList();

    @GET("/thekadesi/user/")
    Call<UserProfileModel> getUserProfile(
            @Query("userId") String phoneNumber
    );

    @Headers("Content-Type: application/json")
    @POST("/thekadesi/user")
    Call<UserProfileModel> createUser(
            @Body UserRequestModel user
    );

    @GET("/thekadesi/user/address/")
    Call<AddressResponseModel> getUserAddress(
            @Query("userId") String phoneNumber
    );

    @Headers("Content-Type: application/json")
    @POST("/thekadesi/user/address")
    Call<NewAddressRequestModel> createAddress(@Body NewAddressRequestModel addressData);

    @Headers("Content-Type: application/json")
    @POST("/thekadesi/order")
    Call<ConfirmOrderResponseModel> createOrder(@Body CreateOrderRequestModel createOrder);


    @GET("/thekadesi/order/")
    Call<OrderDetailModel> getOrderSummery(
            @Query("orderId") String orderId
    );

}
