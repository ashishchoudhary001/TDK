package api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ParmeshMahore on 2/7/17.
 */
public class APIClient {

    //http://107.23.59.43:8787/thekadesi/menu/today/
    public static final String BASE_URL = "http://107.23.59.43:8787/thekadesi/menu/today/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if(retrofit == null) {
            retrofit = new Retrofit
                    .Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
