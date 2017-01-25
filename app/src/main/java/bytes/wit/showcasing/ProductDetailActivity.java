package bytes.wit.showcasing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import java.util.ArrayList;

import cameo.code.imageslider.SliderFragment;

/**
 * Created by Md. Sifat-Ul Haque on 1/23/2017.
 */

public class ProductDetailActivity extends BaseActivity {
    /**
     * The image urls for slider images. This is set slider image and it may change in future.
     */
    private ArrayList<String> mSliderImages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        setupToolbar(R.id.toolbar_product_detail);
        setDefaultValues();
        showSlider();
    }


    /**
     * Initialize slider with image url and attach it the fragment container.
     */
    private void showSlider() {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.slider_fragment_container, SliderFragment.createWithPath(mSliderImages));
        transaction.commit();
    }


    private void setDefaultValues() {
        mSliderImages = new ArrayList<>();
        mSliderImages.add("http://www.dancake.com.bd/images/home-slider/1479303628_th_1.jpg");
        mSliderImages.add("http://www.dancake.com.bd/images/home-slider/1479303820_th_2.jpg");
        mSliderImages.add("http://www.dancake.com.bd/images/home-slider/1479303916_th_3.jpg");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
