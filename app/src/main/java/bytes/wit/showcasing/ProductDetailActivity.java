package bytes.wit.showcasing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import bytes.wit.adapters.PackageImageListAdapter;
import bytes.wit.view.activity.FullScreenContentActivity;
import cameo.code.imageslider.SliderFragment;

/**
 * Created by Md. Sifat-Ul Haque on 1/23/2017.
 */

public class ProductDetailActivity extends BaseActivity implements PackageImageListAdapter.OnPackageImageSelectedListener {
    /**
     * The image urls for slider images. This is set slider image and it may change in future.
     */
    private ArrayList<String> mSliderImages;

    public static final String CURRENT_SLIDE_IMAGE_POSITION = "slide_image_position";

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

    @Override
    public void onPackageImageSelected(int position) {
        Intent fullScreenImageIntent = new Intent(this, FullScreenContentActivity.class);
        fullScreenImageIntent.putExtra(CURRENT_SLIDE_IMAGE_POSITION, position);
        startActivity(fullScreenImageIntent);
        Toast.makeText(this, "Selected Position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_detail, menu);
        return true;
    }
}
