package api;


import model.FoodTypeResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ParmeshMahore on 2/7/17.
 */
public interface APIInterface {

    @GET("/thekadesi/menu/today/")
    Call<FoodTypeResponseModel> getMenuList();

}
