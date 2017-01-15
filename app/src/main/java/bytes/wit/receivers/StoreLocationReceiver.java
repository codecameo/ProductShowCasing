package bytes.wit.receivers;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import java.lang.ref.WeakReference;

/**
 * Created by Md. Sifat-Ul Haque on 1/15/2017.
 */

public class StoreLocationReceiver extends ResultReceiver {

    private WeakReference<Receiver> mReceiver;

    public StoreLocationReceiver(Handler handler) {
        super(handler);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if (mReceiver.get() != null) {
            mReceiver.get().onReceiveResult(resultCode, resultData);
        }
    }

    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle resultData);
    }

    public void setReceiver(Receiver receiver) {
        mReceiver = new WeakReference<Receiver>(receiver);
    }
}
