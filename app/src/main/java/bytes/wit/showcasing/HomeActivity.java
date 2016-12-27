package bytes.wit.showcasing;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import bytes.wit.adapters.HomeCategorizedListAdapter;
import cameo.code.imageslider.SliderFragment;

public class HomeActivity extends AppCompatActivity {

    private ArrayList<String> mImagesUrl;
    private SliderFragment mSliderFragment;
    private RecyclerView mHomeList;
    private HomeCategorizedListAdapter mHomeCategorizedListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        initVariables();
        setDefaultValues();
        showSlider();
        setupListAdapter();
    }

    private void setupListAdapter() {
        mHomeList.setAdapter(mHomeCategorizedListAdapter);
        mHomeList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void showSlider() {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
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

    private void initViews() {
        mHomeList = (RecyclerView) findViewById(R.id.rv_home_product_list);
    }
}
