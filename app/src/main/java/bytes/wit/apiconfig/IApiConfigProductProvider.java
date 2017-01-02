package bytes.wit.apiconfig;

import bytes.wit.models.CategoryModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Md. Sifat-Ul Haque on 12/30/2016.
 */

public interface IApiConfigProductProvider {

    @GET("movie/top_rated")
    Call<CategoryModel> getProductList(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<CategoryModel> getProductList(@Path("id") int categoryID, @Query("api_key") String apiKey);

    @GET("data/2.5/weather")
    Call<CategoryModel> getCategorizedProductList(@Query("q") String city, @Query("appid") String apiKey);
}
