package bytes.wit.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import java.util.ArrayList;

import bytes.wit.models.CategoryModel;
import bytes.wit.models.ProductModel;
import bytes.wit.utils.Constant;
import bytes.wit.wrappers.ProductProviderAdapter;

/**
 * Created by Md. Sifat-Ul Haque on 12/30/2016.
 */

public class ProductProviderService extends IntentService {

    private ArrayList<CategoryModel> mCategoryModels;
    private ResultReceiver resultReceiver;
    private int action;

    public ProductProviderService() {
        super("ProductProviderService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("Service", "Fetching data");
        resultReceiver = intent.getParcelableExtra(ProductProviderAdapter.PRODUCT_RESULT_RECEIVER);
        action = intent.getIntExtra(ProductProviderAdapter.ACTION, 0);

        fetchCategorizedProductList();
    }

    private void fetchCategorizedProductList() {

        mCategoryModels = new ArrayList<>();
        ArrayList<ProductModel> productModels = new ArrayList<>();

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setCategory_name("Cake");

        ProductModel productModel = new ProductModel();
        //productModel


        mCategoryModels.add(categoryModel);


        categoryModel = new CategoryModel();
        categoryModel.setCategory_name("Car");
        mCategoryModels.add(categoryModel);

        sendProductData();
    }

    private void sendProductData() {
        if (resultReceiver != null) {
            Bundle bundle = new Bundle();
            bundle.putInt(ProductProviderAdapter.ACTION, action);
            bundle.putSerializable(ProductProviderAdapter.KEY_CATEGORIZED_PRODUCT, mCategoryModels);
            resultReceiver.send(Constant.API_RESPONSE_SUCCESSFUL, bundle);
        }
        stopSelf();
    }
}
