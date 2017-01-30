package bytes.wit.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bytes.wit.view.fragment.FullScreenImageFragment;
import bytes.wit.view.fragment.FullScreenVideoFragment;

/**
 * Created by Sharifur Rahaman on 1/30/2017.
 * Email: sharif.iit.du@gmail.com
 */

public class FullScreenImagePagerAdapter extends FragmentStatePagerAdapter {

    public static final String TAG = "ImageSliderActivity";

    private final List<Fragment> mFragments = new ArrayList<>();


    public FullScreenImagePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment){
        mFragments.add(fragment);
    }

    /**
     * Set image url and this image url used to show image as full screen.
     * @param url The url to load as fullscreen.
     */
    public void addImage(String url){
        mFragments.add(FullScreenImageFragment.newInstance(url));
    }

    /**
     * Add video to the pager adapter.
     * @param videoUrl The video url.
     */
    public void addVideo(String videoUrl){
        mFragments.add(FullScreenVideoFragment.newInstance(videoUrl));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.d(TAG, "position: " + position);
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
