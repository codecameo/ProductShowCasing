package bytes.wit.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bytes.wit.adapters.HorizontalProductListAdapter;
import bytes.wit.adapters.PackageImageListAdapter;
import bytes.wit.adapters.PackageListAdapter;
import bytes.wit.adapters.SpecificationAdapter;
import bytes.wit.models.ProductModel;
import bytes.wit.showcasing.ProductDetailActivity;
import bytes.wit.showcasing.R;
import bytes.wit.snap.GravitySnapHelper;
import bytes.wit.viewholder.CategoryViewHolder;

/**
 * Created by Md. Sifat-Ul Haque on 1/23/2017.
 */

public class FragmentProductDetail extends Fragment implements CategoryViewHolder.OnProductSelectedListener {

    private RecyclerView mRvPackageList, mRvPackageImageList, mRvSpecificationList, mRvSuggestedList;
    private PackageListAdapter mPackageListAdapter;
    private PackageImageListAdapter mPackageImageListAdapter;
    private SpecificationAdapter mSpecificationAdapter;
    private HorizontalProductListAdapter mSuggestedProductListAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initVariables();
        initListeners();
        setupPackageList();
        setupPackageImageList();
        setupSpecificationList();
        setupSuggestedProductList();
    }

    private void initListeners() {
        mSuggestedProductListAdapter.setOnProductSelectedListener(this);
    }

    private void setupSuggestedProductList() {
        mRvSuggestedList.setAdapter(mSuggestedProductListAdapter);
        //mProductList.addItemDecoration(new DividerItemDecoration());
        mRvSuggestedList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        new GravitySnapHelper(Gravity.START).attachToRecyclerView(mRvSuggestedList);
    }

    private void setupSpecificationList() {
        mRvSpecificationList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvSpecificationList.setAdapter(mSpecificationAdapter);
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
        mRvSpecificationList = (RecyclerView) view.findViewById(R.id.rv_specification);
        mRvSuggestedList = (RecyclerView) view.findViewById(R.id.rv_suggested_list);
    }

    private void initVariables() {
        mPackageImageListAdapter = new PackageImageListAdapter((PackageImageListAdapter.OnPackageImageSelectedListener) getActivity());
        mPackageListAdapter = new PackageListAdapter();
        mSpecificationAdapter = new SpecificationAdapter();
        mSuggestedProductListAdapter = new HorizontalProductListAdapter(new ArrayList<ProductModel>());
    }


    @Override
    public void onProductSelected(ProductModel productModel) {
        /***
         * To Do provide necessary info to show corresponding product detail
         * */
        getActivity().startActivity(new Intent(getActivity(), ProductDetailActivity.class));
    }
}
