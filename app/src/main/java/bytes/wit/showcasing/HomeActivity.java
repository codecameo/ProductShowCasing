package bytes.wit.showcasing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import bytes.wit.fragments.FragmentBrandNew;
import bytes.wit.fragments.FragmentHome;
import bytes.wit.fragments.FragmentPopular;

public class HomeActivity extends ProductSearchActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager mHomeViewPager;
    private List<String> mFragmentTitleList;
    private List<Fragment> mFragmentList;
    private HomeViewPagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupToolbar();
        initViews();
        initVariables();
        initSearchFragment();
        initListeners();
        initViewPager();
        initNavigationDrawer();
    }

    private void initNavigationDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    protected void initVariables() {
        super.initVariables();
        mFragmentList = new ArrayList<>();
        mFragmentTitleList = new ArrayList<>();
        mPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager());
    }

    private void initViews() {
        initSearchPanelView(R.id.fl_panel);
        mHomeViewPager = (ViewPager) findViewById(R.id.vp_home);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
            startActivity(new Intent(HomeActivity.this, LanguageSelectionActivity.class));
        } else if (id == R.id.nav_store_locator) {
            startActivity(new Intent(HomeActivity.this, StoreLocatorActivity.class));
        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
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
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
}
