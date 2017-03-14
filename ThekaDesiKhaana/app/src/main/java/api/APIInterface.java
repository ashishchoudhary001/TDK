package api;


import model.AddressData;
import model.AddressResponseModel;
import model.FoodTypeResponseModel;
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

    /*@Headers("Content-Type: application/json")
    @POST("/thekadesi/user/address")
    Call<AddressData> createAddress(@Query("userId") String userId,
                                    @Query("addressId") String addressId,
                                    @Query("addressLine1") String addressLine1,
                                    @Query("addressLine2") String addressLine2,
                                    @Query("pinCode") String pinCode,
                                    @Query("landmark") String landmark,
                                    @Query("name") String name,
                                    @Query("mobileNumber") String mobileNumber);
*/
    @Headers("Content-Type: application/json")
    @POST("/thekadesi/user/address")
    Call<AddressData> createAddress(@Body AddressData addressData);

    /*@POST("/services/oauth2/token")
    Call<LoginResponseModel> postAdminLoginRequest(
            @Query("grant_type") String response,
            @Query("client_id") String client_id,
            @Query("client_secret") String client_secret,
            @Query("username") String username,
            @Query("password") String password);*/
}
