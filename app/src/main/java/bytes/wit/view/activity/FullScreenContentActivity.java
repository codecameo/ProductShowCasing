package bytes.wit.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;

import bytes.wit.showcasing.BaseActivity;
import bytes.wit.showcasing.R;
import bytes.wit.view.adapter.FullScreenImagePagerAdapter;

import static bytes.wit.showcasing.ProductDetailActivity.CURRENT_SLIDE_IMAGE_POSITION;

/**
 * Created by Sharifur Rahaman on 1/30/2017.
 * Email: sharif.iit.du@gmail.com
 */

public class FullScreenContentActivity extends BaseActivity {

    public static final String TAG = "ImageSliderActivity";

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private FullScreenImagePagerAdapter mPagerAdapter;
    String VideoURL = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
    public static final String IMAGE_URL = "http://www.dancake.com.bd/images/home-slider/1479303628_th_1.jpg";
    private int mCurrentSlidePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_content);
        setupToolbar(R.id.toolbar_full_screen_content);
        setStatusBarTranslucent(true);

        getMessageFromBundle();

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.vp_full_screen_content);
        mPagerAdapter = new FullScreenImagePagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addImage(IMAGE_URL);
        mPagerAdapter.addImage(IMAGE_URL);
        mPagerAdapter.addVideo(VideoURL);
        mPagerAdapter.addImage(IMAGE_URL);
        mPagerAdapter.addVideo(VideoURL);

       // mPager.addOnPageChangeListener(this);

        mPager.setAdapter(mPagerAdapter);
        //Set pager current item with handler because onCreateView called multiple times and Pager recreates ints instance
        mPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPager.setCurrentItem(mCurrentSlidePosition);
            }
        }, 100);


    }

    private void getMessageFromBundle() {
        Intent intent = getIntent();
        if (intent != null){
            mCurrentSlidePosition = intent.getIntExtra(CURRENT_SLIDE_IMAGE_POSITION, 0);
            Log.d(TAG, "current slide position: " + mCurrentSlidePosition);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}
