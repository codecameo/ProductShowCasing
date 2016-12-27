package bytes.wit.viewholder;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import bytes.wit.adapters.HomeCategorizedListAdapter;
import bytes.wit.showcasing.R;
import cameo.code.imageslider.SliderFragment;

/**
 * Created by Md. Sifat-Ul Haque on 12/27/2016.
 */

public class SliderViewHolder extends RecyclerView.ViewHolder {

    private ArrayList<String> mImagesUrl;
    private SliderFragment mSliderFragment;

    public SliderViewHolder(View itemView) {
        super(itemView);
        initVariables();
        setDefaultValues();
        showSlider();
    }

    private void showSlider() {
        final FragmentTransaction transaction = ((AppCompatActivity)itemView.getContext()).getSupportFragmentManager().beginTransaction();
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
    }

    public void startSlider(){
        mSliderFragment.startSlider();
    }

    public void stopSlider(){
        mSliderFragment.stopSlider();
    }
}
