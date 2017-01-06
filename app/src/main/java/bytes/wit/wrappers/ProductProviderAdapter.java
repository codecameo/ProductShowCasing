package bytes.wit.wrappers;

import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;

import java.lang.ref.WeakReference;

import bytes.wit.interfaces.IProductProvider;
import bytes.wit.services.ProductProviderService;

import static bytes.wit.utils.Constant.ACTION;

/**
 * Created by Md. Sifat-Ul Haque on 12/30/2016.
 */

public class ProductProviderAdapter implements IProductProvider {

    public static final String PRODUCT_RESULT_RECEIVER = "product_result_receiver";
    public static final int CATEGORIZED_PRODUCT_ACTION = 1001;
    public static final String KEY_CATEGORIZED_PRODUCT = "categorized_product";

    private Context mContext;
    private ResultReceiver mReceiver;
    private WeakReference<Context> mContextWeakReference;
    private WeakReference<ResultReceiver> mResultReceiverWeakReference;

    public ProductProviderAdapter(Context context, ResultReceiver receiver) {
        mContextWeakReference = new WeakReference<>(context);
        mResultReceiverWeakReference = new WeakReference<>(receiver);
        mContext = mContextWeakReference.get();
        mReceiver = mResultReceiverWeakReference.get();

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
        Intent intent = new Intent(mContext, ProductProviderService.class);
        intent.putExtra(PRODUCT_RESULT_RECEIVER, mReceiver);
        intent.putExtra(ACTION, CATEGORIZED_PRODUCT_ACTION);
        mContext.startService(intent);
    }
}
