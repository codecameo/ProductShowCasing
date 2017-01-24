package bytes.wit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bytes.wit.adapters.PackageListAdapter;
import bytes.wit.showcasing.R;
import bytes.wit.snap.GravitySnapHelper;
import cameo.code.imageslider.SliderFragment;

/**
 * Created by Md. Sifat-Ul Haque on 1/23/2017.
 */

public class FragmentProductDetail extends Fragment {

    private ArrayList<String> mImagesUrl;
    private SliderFragment mSliderFragment;
    private RecyclerView mRvPackageList;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initVariables();
        setDefaultValues();
        showSlider();
        setupPackageList();
    }

    private void setupPackageList() {
        mRvPackageList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRvPackageList.setAdapter(new PackageListAdapter());
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
    }

    private void showSlider() {
        final FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.product_slider_panel, mSliderFragment);
        transaction.commit();
    }

    private void setDefaultValues() {
        mImagesUrl.add("http://www.dancake.com.bd/images/home-slider/1479303628_th_1.jpg");
        mImagesUrl.add("http://www.dancake.com.bd/images/home-slider/1479303820_th_2.jpg");
        mImagesUrl.add("http://www.dancake.com.bd/images/home-slider/1479303916_th_3.jpg");
    }


    private void initVariables() {
        mImagesUrl = new ArrayList<>();
        mSliderFragment = SliderFragment.createWithPath(mImagesUrl);
    }
}
