package bytes.wit.showcasing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import bytes.wit.animationhelpers.SearchBarAnimationHelper;
import bytes.wit.fragments.FragmentBrandNew;
import bytes.wit.fragments.FragmentHome;
import bytes.wit.fragments.FragmentPopular;
import bytes.wit.fragments.FragmentSearch;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentSearch.Communicator {

    private ViewPager mHomeViewPager;
    private List<String> mFragmentTitleList;
    private List<Fragment> mFragmentList;
    private HomeViewPagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;
    private DrawerLayout mDrawer;
    private MenuItem mSearchMenuItem;
    private FrameLayout mSearchPanel;
    private SearchBarAnimationHelper mSearchBarAnimationHelper;
    private int mWidth, mHeight;
    private float mRadius;
    private View mParent;
    private FragmentSearch mFragmentSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupToolbar();
        initViews();
        initVariables();
        initListeners();
        initViewPager();

        initNavigationDrawer();
    }

    private void initListeners() {
        mFragmentSearch.setCommunicator(this);
    }

    private void initSearchFragment() {
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        if (getSupportFragmentManager().findFragmentById(R.id.fl_panel) == null) {
            ft.add(R.id.fl_panel, mFragmentSearch, null).commit();
        } else {
            ft.replace(R.id.fl_panel, mFragmentSearch, null).commit();
        }
    }

    private void initNavigationDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private void initVariables() {
        mFragmentList = new ArrayList<>();
        mFragmentTitleList = new ArrayList<>();
        mPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        mSearchBarAnimationHelper = new SearchBarAnimationHelper(mSearchPanel);
        mParent = findViewById(R.id.parent_view);
        mFragmentSearch = new FragmentSearch();
    }

    private void initViews() {
        mHomeViewPager = (ViewPager) findViewById(R.id.vp_home);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mSearchPanel = (FrameLayout) findViewById(R.id.fl_panel);
    }

    private void initViewPager() {

        mPagerAdapter.addFragment(new FragmentHome(), getString(R.string.tab_home));
        mPagerAdapter.addFragment(new FragmentPopular(), getString(R.string.tab_popular));
        mPagerAdapter.addFragment(new FragmentBrandNew(), getString(R.string.tab_brand_new));
        mHomeViewPager.setAdapter(mPagerAdapter);
        mHomeViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mHomeViewPager);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_language) {

        } else if (id == R.id.nav_store_locator) {
            startActivity(new Intent(HomeActivity.this, StoreLocatorActivity.class));
        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSearchBackPressed() {
        mSearchBarAnimationHelper.startAnimation(mWidth - 24, 24, mRadius, 0);
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        /*gNavAnimationXoffset = mSearchMenuItem.get / 2;
        gNavAnimationYoffset = mSearchMenuItem.getHeight() / 2;
        updateGnavPivot();*/

        mWidth = mParent.getWidth();
        mHeight = mParent.getHeight();
        mRadius = (float) Math.sqrt((mWidth * mWidth) + (mHeight * mHeight));
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);

        mSearchMenuItem = menu.findItem(R.id.action_search);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_search) {

            initSearchFragment();
            mSearchBarAnimationHelper.startAnimation(mWidth - 24, 24, mRadius, 0);
        }

        return super.onOptionsItemSelected(item);
    }
}
