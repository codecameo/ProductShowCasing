package bytes.wit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import bytes.wit.models.StoreLocatorModel;
import bytes.wit.showcasing.R;

/**
 * Created by Sharifur Rahaman on 1/5/2017.
 * Email: sharif.iit.du@gmail.com
 */

public class FragmentStoreLocatorMap extends android.support.v4.app.Fragment implements OnMapReadyCallback {

    private static final String KEY_STORE_LOCATION = "key_store_location";
    private static final String KEY_SELECTED_STORE_POSITION = "key_store_selection_position";
    private GoogleMap mGoogleMap;
    private int mSelectedPosition;
    private UiSettings mUiSettings;
    private ArrayList<StoreLocatorModel> mStoreLocatorModels;
    private LatLng mStoreLocation;
    private CameraPosition mCameraPosition;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FragmentStoreLocatorMap() {
    }

    public static FragmentStoreLocatorMap newInstance(ArrayList<StoreLocatorModel> storeLocatorModel, int position) {
        FragmentStoreLocatorMap fragment = new FragmentStoreLocatorMap();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_STORE_LOCATION, storeLocatorModel);
        bundle.putInt(KEY_SELECTED_STORE_POSITION, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    private void initVariable() {

        Bundle bundle = new Bundle();
        bundle = getArguments();

        if (bundle.containsKey(KEY_STORE_LOCATION)) {
            mStoreLocatorModels = (ArrayList<StoreLocatorModel>) getArguments().getSerializable(KEY_STORE_LOCATION);
            mSelectedPosition = bundle.getInt(KEY_SELECTED_STORE_POSITION);
            mStoreLocation = new LatLng(mStoreLocatorModels.get(mSelectedPosition).getLatitude(), mStoreLocatorModels.get(mSelectedPosition).getLongitude());
            //Toast.makeText(getContext(),"Lat "+ mStoreLocatorModels.getLatitude(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_store_locator_detail, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mUiSettings = mGoogleMap.getUiSettings();
        mapUiSetting(true);
        locateStore();

    }

    private void locateStore() {
        if (mStoreLocation != null) {
            mCameraPosition = new CameraPosition.Builder()
                    .target(mStoreLocation)
                    .zoom(16)
                    .build();

            mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));

            mGoogleMap.clear();

            int size = mStoreLocatorModels.size();

            for (int i = 0; i < size; i++) {
                mGoogleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(mStoreLocatorModels.get(i).getLatitude(), mStoreLocatorModels.get(i).getLongitude()))
                        .title(mStoreLocatorModels.get(i).getStore_address()));
            }
        }
    }

    //UI settings of map
    private void mapUiSetting(boolean flag) {

        //mGoogleMap.setMyLocationEnabled(flag);
        mGoogleMap.setBuildingsEnabled(flag);
        mUiSettings.setZoomControlsEnabled(flag);
        mUiSettings.setCompassEnabled(flag);
        mUiSettings.setMyLocationButtonEnabled(flag);
        mUiSettings.setScrollGesturesEnabled(flag);
        mUiSettings.setZoomGesturesEnabled(flag);
        mUiSettings.setTiltGesturesEnabled(flag);
        mUiSettings.setRotateGesturesEnabled(flag);

        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}
