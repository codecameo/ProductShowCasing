package bytes.wit.fragments;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import bytes.wit.adapters.StoreLocatorAdapter;
import bytes.wit.interfaces.IStoreLocatorCommunicator;
import bytes.wit.interfaces.OnLocationFetchedListener;
import bytes.wit.models.StoreLocatorModel;
import bytes.wit.showcasing.R;
import bytes.wit.showcasing.StoreLocatorActivity;
import bytes.wit.utils.Constant;

/**
 * Created by Md. Sifat-Ul Haque on 1/4/2017.
 */

public class FragmentStoreList extends Fragment implements IStoreLocatorCommunicator,
        OnLocationFetchedListener {

    private RecyclerView mRvStoreList;
    private FragmentStoreList.OnListFragmentInteractionListener mListener;
    private StoreLocatorAdapter mStoreLocatorAdapter;
    private List<StoreLocatorModel> mStoreLocatorModels = new ArrayList<>();
    private LatLng mLastSeenLocation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListeners();
    }

    private void initListeners() {
        ((StoreLocatorActivity) getActivity()).addLocationFetchedListener(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_list, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initVariable();
        setupAdapter();
    }

    private void initVariable() {
        mStoreLocatorAdapter = new StoreLocatorAdapter(mStoreLocatorModels, mListener);
    }

    private void setupAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRvStoreList.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                linearLayoutManager.getOrientation());
        mRvStoreList.addItemDecoration(dividerItemDecoration);
        mRvStoreList.setAdapter(mStoreLocatorAdapter);
    }

    private void initViews(View view) {
        mRvStoreList = (RecyclerView) view.findViewById(R.id.rv_store_list);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentStoreList.OnListFragmentInteractionListener) {
            mListener = (FragmentStoreList.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void updateListData(ArrayList<StoreLocatorModel> storeLocatorModels) {
        if (storeLocatorModels != null) {
            mStoreLocatorModels.clear();
            mStoreLocatorModels.addAll(storeLocatorModels);
            if (mLastSeenLocation != null) {
                calculateDistances();
            }
            mStoreLocatorAdapter.update(mStoreLocatorModels);
        }
    }

    @Override
    public void onLocationFetched(LatLng latLng) {
        mLastSeenLocation = latLng;
        if (!mStoreLocatorModels.isEmpty()) {
            calculateDistances();
            mStoreLocatorAdapter.notifyDataSetChanged();
        }
    }

    private void calculateDistances() {
        for (int i = 0; i < mStoreLocatorModels.size(); i++) {
            updateDistance(mStoreLocatorModels.get(i));
        }
    }

    private void updateDistance(StoreLocatorModel storeLocatorModel) {

        float[] results = new float[1];

        Location.distanceBetween(mLastSeenLocation.latitude, mLastSeenLocation.longitude,
                storeLocatorModel.getLatitude(), storeLocatorModel.getLongitude(),
                results);

        if (results[0] >= 1000) {
            storeLocatorModel.setDistance((double) Math.round((results[0] / 1000.0) * 100) / (100.0));
            storeLocatorModel.setDistance_unit(Constant.DISTANCE_UNIT_KILOMETER);
        } else {
            storeLocatorModel.setDistance((double) Math.round(results[0] * 100) / 100);
            storeLocatorModel.setDistance_unit(Constant.DISTANCE_UNIT_METER);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ((StoreLocatorActivity) getActivity()).removeLocationFetchedListener(this);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(int position);
    }

}
