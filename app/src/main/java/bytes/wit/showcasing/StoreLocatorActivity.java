package bytes.wit.showcasing;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

import bytes.wit.factories.ProviderFactory;
import bytes.wit.fragments.FragmentStoreList;
import bytes.wit.fragments.FragmentStoreLocatorMap;
import bytes.wit.interfaces.ILocationProvider;
import bytes.wit.interfaces.IStoreLocatorCommunicator;
import bytes.wit.models.StoreLocatorModel;
import bytes.wit.receivers.StoreLocationReceiver;
import bytes.wit.utils.PermissionHandler;
import bytes.wit.wrappers.StoreLocationProviderAdapter;

/**
 * Created by Md. Sifat-Ul Haque on 1/4/2017.
 */

public class StoreLocatorActivity extends BaseActivity implements
        FragmentStoreList.OnListFragmentInteractionListener,
        StoreLocationReceiver.Receiver,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {


    private StoreLocationReceiver mStoreLocationReceiver;
    private Handler mHandler;
    private ProviderFactory mProviderFactory;
    private ILocationProvider mILocationProvider;
    private ArrayList<StoreLocatorModel> mStoreLocatorModels;
    private FragmentStoreList mFragmentStoreList;
    private ProgressBar mProgressBar;
    private GoogleApiClient mGoogleApiClient;
    private PermissionHandler mPermissionHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_locator);

        setupToolbar();

        initViews();

        initVariables();

        addStoreLocatorFragment();

        getData();
    }

    private void initViews() {
        mProgressBar = (ProgressBar) findViewById(R.id.loader);
    }

    private void getData() {
        mStoreLocationReceiver.setReceiver(this);
        mILocationProvider.getAllShowroom();
    }

    private void initVariables() {
        mFragmentStoreList = new FragmentStoreList();
        mPermissionHandler = new PermissionHandler(this);
        mStoreLocatorModels = new ArrayList<>();
        mProviderFactory = ProviderFactory.getProviderInstance();
        mHandler = new Handler();
        mStoreLocationReceiver = new StoreLocationReceiver(mHandler);
        mILocationProvider = mProviderFactory.getStoreLocationProvider(this, mStoreLocationReceiver);

        //Initializing googleapi client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

    }

    private void addStoreLocatorFragment() {
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.add(R.id.store_locator_content, mFragmentStoreList);
        // Complete the changes added above
        ft.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onListFragmentInteraction(int position) {
        showStoreMap(position);
    }

    private void showStoreMap(int position) {
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.store_locator_content, FragmentStoreLocatorMap.newInstance(mStoreLocatorModels, position));
        // Complete the changes added above
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        mStoreLocatorModels = (ArrayList<StoreLocatorModel>) resultData.getSerializable(StoreLocationProviderAdapter.KEY_SHOWROOM_INFO);
        mProgressBar.setVisibility(View.GONE);
        if (mFragmentStoreList != null) {
            ((IStoreLocatorCommunicator) mFragmentStoreList).updateListData(mStoreLocatorModels);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    //Getting current location
    private void getCurrentLocation() {
        //Creating a location object
        /*if (mPermissionHandler.hasCoarseLocationPermission() && mPermissionHandler.hasFineLocationPermission()){
            Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (location != null) {

            }
        }*/
    }




    /*private class StoreLocationReceiver extends ResultReceiver {

        public StoreLocationReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            mStoreLocatorModels = (ArrayList<StoreLocatorModel>) resultData.getSerializable(StoreLocationProviderAdapter.KEY_SHOWROOM_INFO);
            mProgressBar.setVisibility(View.GONE);
            if (mFragmentStoreList != null) {
                ((IStoreLocatorCommunicator) mFragmentStoreList).updateListData(mStoreLocatorModels);
            }
        }
    }*/
}
