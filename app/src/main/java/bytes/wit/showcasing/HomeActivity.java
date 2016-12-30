package bytes.wit.showcasing;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import bytes.wit.fragments.FragmentHome;
import bytes.wit.fragments.FragmentPopular;

public class HomeActivity extends BaseActivity {

    private ViewPager mHomeViewPager;
    private List<String> mFragmentTitleList;
    private List<Fragment> mFragmentList;
    private HomeViewPagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupToolbar();
        initViews();
        initVariables();
        initViewPager();
    }

    private void initVariables() {
        mFragmentList = new ArrayList<>();
        mFragmentTitleList = new ArrayList<>();
        mPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager());
    }

    private void initViews() {
        mHomeViewPager = (ViewPager) findViewById(R.id.vp_home);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
    }

    private void initViewPager() {

        mPagerAdapter.addFragment(new FragmentHome(), getString(R.string.tab_home));
        mPagerAdapter.addFragment(new FragmentPopular(), getString(R.string.tab_popular));
        //mPagerAdapter.addFragment(new FragmentHome(), getString(R.string.tab_brand_new));
        mHomeViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mHomeViewPager);
    }

    private class HomeViewPagerAdapter extends FragmentPagerAdapter {
        public HomeViewPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }


        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
