package bytes.wit.showcasing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import bytes.wit.fragments.FragmentProductList;

/**
 * Created by Md. Sifat-Ul Haque on 1/15/2017.
 */

public class ShowProductListActivity extends ProductSearchActivity {

    public static final String KEY_PRODUCT_LIST = "key_product_list";
    private FragmentProductList mFragmentProductList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        setupToolbar();
        initSearchPanelView(R.id.fl_panel);
        initVariables();
        initListeners();
        addProductListFragment();
        initSearchFragment();
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        mFragmentProductList = new FragmentProductList();
    }

    private void addProductListFragment() {
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.add(R.id.fragment_product_list, mFragmentProductList);
        // Complete the changes added above
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
