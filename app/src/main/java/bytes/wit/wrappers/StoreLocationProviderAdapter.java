package bytes.wit.wrappers;

import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;

import java.lang.ref.WeakReference;

import bytes.wit.interfaces.ILocationProvider;
import bytes.wit.services.StoreLocationProviderService;

import static bytes.wit.utils.Constant.ACTION;

/**
 * Created by Md. Sifat-Ul Haque on 1/5/2017.
 */

public class StoreLocationProviderAdapter implements ILocationProvider {

    public static final String KEY_SHOWROOM_INFO = "showroom_info";
    public static final int ALL_SHOWROOM_ACTION = 1001;
    public static final String SHOWROOM_RESULT_RECEIVER = "store_locator_result";

    //private Context mContext;
    //private ResultReceiver mReceiver;
    private WeakReference<Context> mContextWeakReference;
    private WeakReference<ResultReceiver> mResultReceiverWeakReference;

    public StoreLocationProviderAdapter(Context context, ResultReceiver receiver) {
        mContextWeakReference = new WeakReference<>(context);
        mResultReceiverWeakReference = new WeakReference<>(receiver);
        //mContext = mContextWeakReference.get();
        //mReceiver = mResultReceiverWeakReference.get();

    }

    @Override
    public void getAllShowroom() {
        Intent intent = new Intent(mContextWeakReference.get(), StoreLocationProviderService.class);
        intent.putExtra(SHOWROOM_RESULT_RECEIVER, mResultReceiverWeakReference.get());
        intent.putExtra(ACTION, ALL_SHOWROOM_ACTION);
        mContextWeakReference.get().startService(intent);
    }

    @Override
    public void getShowRoom(String showroomId) {

    }

    @Override
    public void getFilteredShowRoom(String query) {

    }
}
