package bytes.wit.factories;

import android.content.Context;
import android.os.ResultReceiver;

import bytes.wit.interfaces.IProductProvider;
import bytes.wit.wrappers.ProductProviderAdapter;

/**
 * Created by Md. Sifat-Ul Haque on 12/30/2016.
 */

public class ProviderFactory {

    private static volatile ProviderFactory mProviderFactory;

    private ProviderFactory() {
    }

    public static ProviderFactory getProviderInstance() {
        if (mProviderFactory == null) {
            synchronized (ProviderFactory.class) {
                if (mProviderFactory == null)
                    mProviderFactory = new ProviderFactory();
            }
        }

        return mProviderFactory;
    }


    public IProductProvider getProductProvider(Context context, ResultReceiver receiver) {
        return (IProductProvider) new ProductProviderAdapter(context, receiver);
    }

}
