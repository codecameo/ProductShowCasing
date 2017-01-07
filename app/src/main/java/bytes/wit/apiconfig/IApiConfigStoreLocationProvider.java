package bytes.wit.apiconfig;

import java.util.ArrayList;

import bytes.wit.models.StoreLocatorModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Md. Sifat-Ul Haque on 1/5/2017.
 */

public interface IApiConfigStoreLocationProvider {

    @GET("json.txt")
    Call<ArrayList<StoreLocatorModel>> getAllShowroom();

    @GET("store/{suffix}")
    Call<ArrayList<StoreLocatorModel>> getFilteredShowroom(@Path("suffix") String queryString, @Query("api_key") String apiKey);

    @GET("store/{id}")
    Call<StoreLocatorModel> getShowroom(@Path("id") int showroomID, @Query("appid") String apiKey);

}
