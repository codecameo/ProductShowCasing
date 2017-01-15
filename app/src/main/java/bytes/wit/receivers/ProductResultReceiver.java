package bytes.wit.receivers;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import java.lang.ref.WeakReference;

import bytes.wit.utils.Constant;

/**
 * Created by Md. Sifat-Ul Haque on 1/15/2017.
 */

public class ProductResultReceiver extends ResultReceiver {

    private WeakReference<Receiver> mReceiver;

    public ProductResultReceiver(Handler handler) {
        super(handler);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (Constant.API_RESPONSE_SUCCESSFUL == resultCode) {
            //ArrayList<CategoryModel> categoryModels = (ArrayList<CategoryModel>) resultData.getSerializable(ProductProviderAdapter.KEY_CATEGORIZED_PRODUCT);
            //mHomeCategorizedListAdapter.updateData(categoryModels);

            if (mReceiver.get() != null) {
                mReceiver.get().onReceiveResult(resultCode, resultData);
            }
        }
    }

    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle resultData);
    }

    public void setReceiver(Receiver receiver) {
        mReceiver = new WeakReference<>(receiver);
    }
}
