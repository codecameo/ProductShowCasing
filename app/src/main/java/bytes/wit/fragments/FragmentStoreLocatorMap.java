package bytes.wit.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import bytes.wit.interfaces.OnLocationFetchedListener;
import bytes.wit.models.StoreLocatorModel;
import bytes.wit.showcasing.R;
import bytes.wit.showcasing.StoreLocatorActivity;
import bytes.wit.utils.Constant;
import bytes.wit.utils.PermissionHandler;

import static bytes.wit.utils.PermissionHandler.REQUEST_BOTH_LOCATION_PERMISSION;
import static bytes.wit.utils.PermissionHandler.REQUEST_COARSE_LOCATION;
import static bytes.wit.utils.PermissionHandler.REQUEST_FINE_LOCATION;

/**
 * Created by Sharifur Rahaman on 1/5/2017.
 * Email: sharif.iit.du@gmail.com
 */

public class FragmentStoreLocatorMap extends android.support.v4.app.Fragment implements OnMapReadyCallback,
        OnLocationFetchedListener, GoogleMap.OnMarkerClickListener {

    private static final String KEY_STORE_LOCATION = "key_store_location";
    private static final String KEY_SELECTED_STORE_POSITION = "key_store_selection_position";
    private GoogleMap mGoogleMap;
    private int mSelectedPosition;
    private UiSettings mUiSettings;
    private ArrayList<StoreLocatorModel> mStoreLocatorModels;
    private LatLng mStoreLocation;
    private CameraPosition mCameraPosition;
    private PermissionHandler mPermissionHandler;
    private TextView mTvAddress, mTvEmail, mTvPhone, mTvDistrict, mTvDistance;

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
        initListeners();
        initVariable();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Frag", "Resume 2");
    }

    private void initVariable() {

        mPermissionHandler = new PermissionHandler(getActivity());

        Bundle bundle = getArguments();

        if (bundle.containsKey(KEY_STORE_LOCATION)) {
            mStoreLocatorModels = (ArrayList<StoreLocatorModel>) getArguments().getSerializable(KEY_STORE_LOCATION);
            mSelectedPosition = bundle.getInt(KEY_SELECTED_STORE_POSITION);
            mStoreLocation = new LatLng(mStoreLocatorModels.get(mSelectedPosition).getLatitude(), mStoreLocatorModels.get(mSelectedPosition).getLongitude());
            //Toast.makeText(getContext(),"Lat "+ mStoreLocatorModels.getLatitude(),Toast.LENGTH_SHORT).show();
        }
    }

    private void initListeners() {
        ((StoreLocatorActivity) getActivity()).addLocationFetchedListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_store_locator_detail, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        mTvAddress = (TextView) rootView.findViewById(R.id.tv_map_address);
        mTvDistrict = (TextView) rootView.findViewById(R.id.tv_map_district);
        mTvPhone = (TextView) rootView.findViewById(R.id.tv_map_phone);
        mTvEmail = (TextView) rootView.findViewById(R.id.tv_map_email);
        mTvDistance = (TextView) rootView.findViewById(R.id.tv_map_distance);
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
        mGoogleMap.setOnMarkerClickListener(this);
        mapUiSetting(true);
        setupMarkers();
        locateStore();
        updateBottomInfo();

    }

    private void setupMarkers() {
        mGoogleMap.clear();
        int size = mStoreLocatorModels.size();

        for (int i = 0; i < size; i++) {
            (mGoogleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(mStoreLocatorModels.get(i).getLatitude(), mStoreLocatorModels.get(i).getLongitude()))))
                    .setTag(i);
        }
    }

    private void locateStore() {
        if (mStoreLocation != null) {
            mCameraPosition = new CameraPosition.Builder()
                    .target(mStoreLocation)
                    .zoom(16)
                    .build();
            mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));
        }
    }

    /**
     * Update info about the store in bottom of the map
     */
    private void updateBottomInfo() {
        StoreLocatorModel storeLocatorModel = mStoreLocatorModels.get(mSelectedPosition);
        mTvAddress.setText(storeLocatorModel.getStore_address());
        mTvPhone.setText(Constant.PHONE_TAG + storeLocatorModel.getMobile_number());
        mTvDistrict.setText(storeLocatorModel.getDistrict());
        mTvEmail.setText(Constant.EMAIL_TAG + storeLocatorModel.getEmail());
        if (storeLocatorModel.getDistance_unit() != null) {
            mTvDistance.setVisibility(View.VISIBLE);
            mTvDistance.setText(getFormattedDistance(storeLocatorModel.getDistance(), storeLocatorModel.getDistance_unit()));
        }
    }

    //UI settings of map
    private void mapUiSetting(boolean flag) {

        enableLocation(flag);

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

    private void enableLocation(boolean flag) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mGoogleMap.setMyLocationEnabled(flag);
        } else if (!mPermissionHandler.hasFineLocationPermission()) {
            mPermissionHandler.requestFineLocationPermission();
        } else if (!mPermissionHandler.hasCoarseLocationPermission()) {
            mPermissionHandler.requestCoarseLocationPermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case REQUEST_BOTH_LOCATION_PERMISSION:
            case REQUEST_COARSE_LOCATION:
            case REQUEST_FINE_LOCATION:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Location enbaled", Toast.LENGTH_SHORT).show();
                    enableLocation(true);
                } else {
                    Toast.makeText(getActivity(), getString(R.string.toast_location_permision_denied), Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void onLocationFetched(LatLng latLng) {
        Log.d("Frag_Location", "Lat " + latLng.latitude + " Long " + latLng.longitude);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((StoreLocatorActivity) getActivity()).removeLocationFetchedListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        mSelectedPosition = (int) marker.getTag();
        mStoreLocation = new LatLng(mStoreLocatorModels.get(mSelectedPosition).getLatitude(), mStoreLocatorModels.get(mSelectedPosition).getLongitude());
        locateStore();
        updateBottomInfo();
        return false;
    }

    private SpannableString getFormattedDistance(double dist, String distance_unit) {
        String distance = dist + "\n" + distance_unit;
        int index = distance.length() - distance_unit.length();
        SpannableString spannableString = new SpannableString(distance);
        spannableString.setSpan(new RelativeSizeSpan(1.3f), 0, index, 0);
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, index, 0);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, index, 0);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), index, distance.length(), 0);
        return spannableString;
    }
}
