package api;


import model.FoodTypeResponseModel;
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

    @GET("/thekadesi/user/9916620436")
    Call<UserProfileModel> getUserProfile(
            @Query("9916620436") String ph
    );

    @Headers("Content-Type: application/json")
    @POST("/thekadesi/user")
    Call<UserProfileModel> createUser(
            @Body UserRequestModel user
            );

    @GET("/thekadesi/user/address/9916620436")
    Call<UserProfileModel> getUserAddress();

}
