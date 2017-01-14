package bytes.wit.wrappers;

import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;

import java.lang.ref.WeakReference;

import bytes.wit.interfaces.IProductProvider;
import bytes.wit.services.PopularProductProviderService;
import bytes.wit.services.ProductProviderService;

import static bytes.wit.utils.Constant.ACTION;

/**
 * Created by Md. Sifat-Ul Haque on 12/30/2016.
 */

public class ProductProviderAdapter implements IProductProvider {

    public static final String PRODUCT_RESULT_RECEIVER = "product_result_receiver";
    public static final int CATEGORIZED_PRODUCT_ACTION = 1001;
    public static final int POPULAR_PRODUCT_ACTION = 1002;
    public static final String KEY_CATEGORIZED_PRODUCT = "categorized_product";
    public static final String KEY_POPULAR_PRODUCT = "popular_product";

    private WeakReference<Context> mContextWeakReference;
    private WeakReference<ResultReceiver> mResultReceiverWeakReference;

    public ProductProviderAdapter(Context context, ResultReceiver receiver) {
        mContextWeakReference = new WeakReference<>(context);
        mResultReceiverWeakReference = new WeakReference<>(receiver);
    }

    @Override
    public void getProductList() {
    }

    @Override
    public void getProductList(String categoryID) {
    }

    @Override
    public void getFilteredProductList() {
    }

    @Override
    public void getCategorizedProductList() {
        Intent intent = new Intent(mContextWeakReference.get(), ProductProviderService.class);
        intent.putExtra(PRODUCT_RESULT_RECEIVER, mResultReceiverWeakReference.get());
        intent.putExtra(ACTION, CATEGORIZED_PRODUCT_ACTION);
        mContextWeakReference.get().startService(intent);
    }

    @Override
    public void getPopularProductList() {
        Intent intent = new Intent(mContextWeakReference.get(), PopularProductProviderService.class);
        intent.putExtra(PRODUCT_RESULT_RECEIVER, mResultReceiverWeakReference.get());
        intent.putExtra(ACTION, POPULAR_PRODUCT_ACTION);
        mContextWeakReference.get().startService(intent);
    }

    @Override
    public void getNewProductList() {

    }
}
