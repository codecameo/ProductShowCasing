package bytes.wit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bytes.wit.showcasing.R;
import cameo.code.imageslider.SliderFragment;

/**
 * Created by Md. Sifat-Ul Haque on 1/23/2017.
 */

public class FragmentProductDetail extends Fragment {

    private ArrayList<String> mImagesUrl;
    private SliderFragment mSliderFragment;

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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {

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
