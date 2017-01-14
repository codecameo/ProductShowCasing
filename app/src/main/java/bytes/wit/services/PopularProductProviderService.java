package bytes.wit.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import bytes.wit.apiconfig.ApiClient;
import bytes.wit.apiconfig.IApiConfigProductProvider;
import bytes.wit.models.ProductModel;
import bytes.wit.utils.Constant;
import bytes.wit.wrappers.ProductProviderAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static bytes.wit.utils.Constant.ACTION;

/**
 * Created by Md. Sifat-Ul Haque on 1/14/2017.
 */

public class PopularProductProviderService extends IntentService {

    private WeakReference<ResultReceiver> resultReceiver;
    private int action;
    private ArrayList<ProductModel> mProductModels;

    public PopularProductProviderService() {
        super("PopularProductProviderService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        resultReceiver = new WeakReference<>((ResultReceiver) intent.getParcelableExtra(ProductProviderAdapter.PRODUCT_RESULT_RECEIVER));
        action = intent.getIntExtra(ACTION, 0);

        fetchPopularProductList();
    }

    private void fetchPopularProductList() {

        mProductModels = new ArrayList<>();

        IApiConfigProductProvider apiService = ApiClient.getClient().create(IApiConfigProductProvider.class);

        Call<ProductModel> call = apiService.getPopularProductList("London,uk", "6050acd5591ada54a7944f58143faacc");

        call.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                //List<ProductModel> movies = response.body().getProducts();

                sendProductData();
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                // Log error here since request failed
                //Log.e(TAG, t.toString());

                sendProductData();

            }
        });
    }

    private void sendProductData() {
        if (resultReceiver.get() != null) {
            Bundle bundle = new Bundle();
            bundle.putInt(ACTION, action);
            bundle.putSerializable(ProductProviderAdapter.KEY_POPULAR_PRODUCT, mProductModels);
            resultReceiver.get().send(Constant.API_RESPONSE_SUCCESSFUL, bundle);
        }
        stopSelf();
    }
}
