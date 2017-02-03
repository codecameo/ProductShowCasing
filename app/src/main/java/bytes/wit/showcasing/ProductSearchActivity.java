package bytes.wit.showcasing;

import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import bytes.wit.animationhelpers.SearchBarAnimationHelper;
import bytes.wit.fragments.FragmentSearch;
import bytes.wit.interfaces.SearchFragmentCommunicator;

/**
 * Created by Md. Sifat-Ul Haque on 2/2/2017.
 */

public class ProductSearchActivity extends BaseActivity implements
        FragmentSearch.Communicator {

    protected SearchBarAnimationHelper mSearchBarAnimationHelper;
    protected int mWidth, mHeight;
    protected float mRadius;
    protected View mParent;
    protected SearchFragmentCommunicator mSearchFragmentCommunicator;
    protected FrameLayout mSearchPanel;
    protected FragmentSearch mFragmentSearch;
    private int mSearchPanelId;

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

    protected void initVariables() {
        mSearchBarAnimationHelper = new SearchBarAnimationHelper(mSearchPanel);
        mParent = findViewById(R.id.parent_view);
        mFragmentSearch = new FragmentSearch();
    }


    protected void initSearchPanelView(int fl_panel) {
        mSearchPanelId = fl_panel;
        mSearchPanel = (FrameLayout) findViewById(mSearchPanelId);
    }

    protected void initListeners() {
        mFragmentSearch.setCommunicator(this);
    }

    protected void initSearchFragment() {
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.add(mSearchPanelId, mFragmentSearch, null).commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_search) {
            if (mSearchFragmentCommunicator != null) {
                mSearchFragmentCommunicator.onSearchBarOpen();
            }
            toggleSearchBar();
        }

        return super.onOptionsItemSelected(item);
    }

    protected void toggleSearchBar() {
        mSearchBarAnimationHelper.startAnimation(mWidth - 24, 24, mRadius, 0);
    }

    @Override
    public void onSearchBackPressed() {
        mSearchBarAnimationHelper.startAnimation(mWidth - 24, 24, mRadius, 0);
    }

    public void setSearchFragmentCommunicator(SearchFragmentCommunicator mSearchFragmentCommunicator) {
        this.mSearchFragmentCommunicator = mSearchFragmentCommunicator;
    }

    @Override
    public void onBackPressed() {
        if (mSearchBarAnimationHelper.isVisible()) {
            toggleSearchBar();
        } else {
            super.onBackPressed();
        }
    }
}
