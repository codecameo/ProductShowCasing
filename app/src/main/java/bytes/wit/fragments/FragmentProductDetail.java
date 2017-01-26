package bytes.wit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bytes.wit.adapters.PackageImageListAdapter;
import bytes.wit.adapters.PackageListAdapter;
import bytes.wit.showcasing.R;
import bytes.wit.snap.GravitySnapHelper;

/**
 * Created by Md. Sifat-Ul Haque on 1/23/2017.
 */

public class FragmentProductDetail extends Fragment {

    private RecyclerView mRvPackageList, mRvPackageImageList;
    private PackageListAdapter mPackageListAdapter;
    private PackageImageListAdapter mPackageImageListAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initVariables();
        setupPackageList();
        setupPackageImageList();
    }

    private void setupPackageImageList() {
        mRvPackageImageList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRvPackageImageList.setAdapter(mPackageImageListAdapter);
        new GravitySnapHelper(Gravity.START).attachToRecyclerView(mRvPackageImageList);
    }

    private void setupPackageList() {
        mRvPackageList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRvPackageList.setAdapter(mPackageListAdapter);
        new GravitySnapHelper(Gravity.START).attachToRecyclerView(mRvPackageList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mRvPackageList = (RecyclerView) view.findViewById(R.id.rv_package_list);
        mRvPackageImageList = (RecyclerView) view.findViewById(R.id.rv_package_image_list);
    }

    private void initVariables() {
        mPackageImageListAdapter = new PackageImageListAdapter((PackageImageListAdapter.OnPackageImageSelectedListener) getActivity());
        mPackageListAdapter = new PackageListAdapter();
    }
}
