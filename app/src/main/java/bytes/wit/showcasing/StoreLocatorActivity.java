package bytes.wit.showcasing;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import bytes.wit.factories.ProviderFactory;
import bytes.wit.fragments.FragmentStoreList;
import bytes.wit.fragments.FragmentStoreLocatorMap;
import bytes.wit.interfaces.ILocationProvider;
import bytes.wit.interfaces.IStoreLocatorCommunicator;
import bytes.wit.interfaces.OnLocationFetchedListener;
import bytes.wit.models.StoreLocatorModel;
import bytes.wit.receivers.StoreLocationReceiver;
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
    private FragmentStoreLocatorMap mFragmentStoreLocator;
    private ProgressBar mProgressBar;
    private GoogleApiClient mGoogleApiClient;
    private LatLng mMyLastLocation;
    private boolean isPreviouslyLoaded = false;
    private ArrayList<OnLocationFetchedListener> mOnLocationFetchedListeners = new ArrayList<>();

    private Runnable mLastLocation = new Runnable() {
        @Override
        public void run() {
            if (ActivityCompat.checkSelfPermission(StoreLocatorActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(StoreLocatorActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (mGoogleApiClient.isConnected()) {
                    Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                    if (lastLocation != null) {
                        mMyLastLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                        broadCastLocation();
                    }
                }
            }
        }
    };

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
        mFragmentStoreLocator = FragmentStoreLocatorMap.newInstance(mStoreLocatorModels, position);
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.store_locator_content, mFragmentStoreLocator);
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
        if (!isPreviouslyLoaded) {
            isPreviouslyLoaded = true;
            mHandler.post(mLastLocation);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        connectGoogleApiClient();
    }

    @Override
    protected void onStart() {
        connectGoogleApiClient();
        super.onStart();
    }

    @Override
    protected void onStop() {
        disconnectGoogleApiClient();
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

    private void connectGoogleApiClient() {
        if (!mGoogleApiClient.isConnected() && !mGoogleApiClient.isConnecting()) {
            mGoogleApiClient.connect();
        }
    }

    private void disconnectGoogleApiClient() {
        if (mGoogleApiClient.isConnected() || mGoogleApiClient.isConnecting()) {
            mGoogleApiClient.disconnect();
        }
    }

    private void broadCastLocation() {
        for (int i = 0; i < mOnLocationFetchedListeners.size(); i++) {
            mOnLocationFetchedListeners.get(i).onLocationFetched(mMyLastLocation);
        }
    }

    public void addLocationFetchedListener(OnLocationFetchedListener onLocationFetchedListener) {
        mOnLocationFetchedListeners.add(onLocationFetchedListener);
    }

    public void removeLocationFetchedListener(OnLocationFetchedListener onLocationFetchedListener) {
        mOnLocationFetchedListeners.remove(onLocationFetchedListener);
    }
}
