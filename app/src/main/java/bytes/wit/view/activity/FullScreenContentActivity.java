package bytes.wit.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import bytes.wit.showcasing.BaseActivity;
import bytes.wit.showcasing.R;
import bytes.wit.view.adapter.FullScreenImagePagerAdapter;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_content);

        setupToolbar(R.id.toolbar_full_screen_content);

        setStatusBarTranslucent(true);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.vp_full_screen_content);
        mPagerAdapter = new FullScreenImagePagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addImage("");
        mPagerAdapter.addVideo(VideoURL);
        mPagerAdapter.addImage("");
        mPagerAdapter.addVideo(VideoURL);
        mPagerAdapter.addImage("");
        mPager.setAdapter(mPagerAdapter);

    }
}
