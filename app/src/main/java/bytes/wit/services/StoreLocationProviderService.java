package bytes.wit.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bytes.wit.apiconfig.ApiClient;
import bytes.wit.apiconfig.IApiConfigStoreLocationProvider;
import bytes.wit.models.StoreLocatorModel;
import bytes.wit.utils.Constant;
import bytes.wit.wrappers.StoreLocationProviderAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static bytes.wit.utils.Constant.ACTION;

/**
 * Created by Md. Sifat-Ul Haque on 1/5/2017.
 */

public class StoreLocationProviderService extends IntentService {

    private WeakReference<ResultReceiver> resultReceiver;
    private int action;

    public StoreLocationProviderService() {
        super("StoreLocationProviderService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        resultReceiver = new WeakReference<>((ResultReceiver) intent.getParcelableExtra(StoreLocationProviderAdapter.SHOWROOM_RESULT_RECEIVER));
        action = intent.getIntExtra(ACTION, 0);

        if (action == StoreLocationProviderAdapter.ALL_SHOWROOM_ACTION) {
            fetchAllShowroomLocation();
        }
    }

    private void fetchAllShowroomLocation() {

        IApiConfigStoreLocationProvider apiService = ApiClient.getClient().create(IApiConfigStoreLocationProvider.class);

        Call<ArrayList<StoreLocatorModel>> call = apiService.getAllShowroom();

        call.enqueue(new Callback<ArrayList<StoreLocatorModel>>() {
            @Override
            public void onResponse(Call<ArrayList<StoreLocatorModel>> call, Response<ArrayList<StoreLocatorModel>> response) {

                List<StoreLocatorModel> stores = response.body();
                sendAllStoreData((ArrayList<StoreLocatorModel>) stores);

            }

            @Override
            public void onFailure(Call<ArrayList<StoreLocatorModel>> call, Throwable t) {
                sendAllStoreData((ArrayList<StoreLocatorModel>) Collections.EMPTY_LIST);
            }
        });
    }

    private void sendAllStoreData(ArrayList<StoreLocatorModel> storeLocatorModels) {
        if (resultReceiver.get() != null) {
            Bundle bundle = new Bundle();
            bundle.putInt(ACTION, action);
            bundle.putSerializable(StoreLocationProviderAdapter.KEY_SHOWROOM_INFO, storeLocatorModels);
            resultReceiver.get().send(Constant.API_RESPONSE_SUCCESSFUL, bundle);
        }
        resultReceiver = null;
        stopSelf();
    }
}
