package bytes.wit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bytes.wit.adapters.HomeCategorizedListAdapter;
import bytes.wit.showcasing.R;
import cameo.code.imageslider.SliderFragment;

/**
 * Created by Md. Sifat-Ul Haque on 12/28/2016.
 */

public class FragmentHome extends Fragment {

    private ArrayList<String> mImagesUrl;
    private SliderFragment mSliderFragment;
    private RecyclerView mHomeList;
    private HomeCategorizedListAdapter mHomeCategorizedListAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initVariables();
        setDefaultValues();
        showSlider();
        setupListAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);
        return view;
    }

    private void setupListAdapter() {
        mHomeList.setAdapter(mHomeCategorizedListAdapter);
        mHomeList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void showSlider() {
        final FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.slider_panel, mSliderFragment);
        transaction.commit();
    }

    private void setDefaultValues() {

        /*mImagesUrl.add("http://buyersguide.caranddriver.com/media/assets/submodel/7757.jpg");
        mImagesUrl.add("http://buyersguide.caranddriver.com/media/assets/submodel/6956.jpg");
        mImagesUrl.add("https://buyersguide.caranddriver.com/media/assets/submodel/1470.jpg");*/

        mImagesUrl.add("http://www.dancake.com.bd/images/home-slider/1479303628_th_1.jpg");
        mImagesUrl.add("http://www.dancake.com.bd/images/home-slider/1479303820_th_2.jpg");
        mImagesUrl.add("http://www.dancake.com.bd/images/home-slider/1479303916_th_3.jpg");
    }


    private void initVariables() {
        mImagesUrl = new ArrayList<>();
        mSliderFragment = SliderFragment.createWithPath(mImagesUrl);
        mHomeCategorizedListAdapter = new HomeCategorizedListAdapter();
    }

    private void initViews(View view) {
        mHomeList = (RecyclerView) view.findViewById(R.id.rv_home_product_list);
    }

}
