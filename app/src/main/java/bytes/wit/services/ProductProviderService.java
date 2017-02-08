package bytes.wit.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import bytes.wit.apiconfig.ApiClient;
import bytes.wit.apiconfig.IApiConfigProductProvider;
import bytes.wit.models.CategoryModel;
import bytes.wit.parsing_models.HomeCategoryListModel;
import bytes.wit.utils.Constant;
import bytes.wit.wrappers.ProductProviderAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static bytes.wit.utils.Constant.ACTION;
import static bytes.wit.view.fragment.FullScreenImageFragment.TAG;

/**
 * Created by Md. Sifat-Ul Haque on 12/30/2016.
 */

public class ProductProviderService extends IntentService {

    private ArrayList<CategoryModel> mCategoryModels;
    private WeakReference<ResultReceiver> resultReceiver;
    private int action;

    public ProductProviderService() {
        super("ProductProviderService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("Service", "Fetching data");
        resultReceiver = new WeakReference<>((ResultReceiver) intent.getParcelableExtra(ProductProviderAdapter.PRODUCT_RESULT_RECEIVER));
        action = intent.getIntExtra(ACTION, 0);
        fetchCategorizedProductList();
    }

    private void fetchCategorizedProductList() {

        mCategoryModels = new ArrayList<>();
        /*ArrayList<ProductModel> productModels = new ArrayList<>();

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setCategory_name("Cake");

        ProductModel productModel = new ProductModel();
        //productModel


        mCategoryModels.add(categoryModel);


        categoryModel = new CategoryModel();
        categoryModel.setCategory_name("Car");
        mCategoryModels.add(categoryModel);*/


        IApiConfigProductProvider apiService = ApiClient.getClient().create(IApiConfigProductProvider.class);

        Call<HomeCategoryListModel> call = apiService.getCategorizedProductList();
        call.enqueue(new Callback<HomeCategoryListModel>() {
            @Override
            public void onResponse(Call<HomeCategoryListModel> call, Response<HomeCategoryListModel> response) {
                mCategoryModels = response.body().getCategory();
                Log.d(TAG, "Number of Product received: " + mCategoryModels.get(0).getProducts().size());
                Log.d(TAG, "Number of Product received: " + mCategoryModels.get(0).getProducts().get(0).getPoster().getContent_url());
                Log.d(TAG, "Number of Product received: " + mCategoryModels.get(1).getProducts().size());
                sendProductData();
            }

            @Override
            public void onFailure(Call<HomeCategoryListModel> call, Throwable t) {
                // Log error here since request failed
                //Log.e(TAG, t.toString());
                sendProductData();
            }
        });


        /*Call<CategoryModel> call = apiService.getCategorizedProductList("London,uk", "6050acd5591ada54a7944f58143faacc");

        call.enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                //List<ProductModel> movies = response.body().getProducts();
                sendProductData();
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                // Log error here since request failed
                //Log.e(TAG, t.toString());
                sendProductData();
            }
        });*/
    }

    private void sendProductData() {
        if (resultReceiver.get() != null) {
            Bundle bundle = new Bundle();
            bundle.putInt(ACTION, action);
            bundle.putSerializable(ProductProviderAdapter.KEY_CATEGORIZED_PRODUCT, mCategoryModels);
            resultReceiver.get().send(Constant.API_RESPONSE_SUCCESSFUL, bundle);
        }
        stopSelf();
    }
}
