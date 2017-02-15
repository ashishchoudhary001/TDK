package api;


import model.FoodTypeResponseModel;
import model.UserProfileModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ParmeshMahore on 2/7/17.
 */
public interface APIInterface {

    @GET("/thekadesi/menu/today/")
    Call<FoodTypeResponseModel> getMenuList();

    @GET("/thekadesi/user/9916620436")
    Call<UserProfileModel> getUserProfile();


}
