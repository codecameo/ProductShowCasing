package bytes.wit.showcasing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import bytes.wit.fragments.FragmentStoreList;
import bytes.wit.fragments.FragmentStoreLocatorMap;
import bytes.wit.models.StoreLocatorModel;

/**
 * Created by Md. Sifat-Ul Haque on 1/4/2017.
 */

public class StoreLocatorActivity extends BaseActivity implements FragmentStoreList.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_locator);

        setupToolbar();
        addStoreLocatorFragment();
    }

    private void addStoreLocatorFragment() {
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.add(R.id.store_locator_content, new FragmentStoreList());
        // Complete the changes added above
        ft.commit();
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
    public void onListFragmentInteraction(StoreLocatorModel storeLocatorModel) {
        showStoreMap(storeLocatorModel);
    }

    private void showStoreMap(StoreLocatorModel storeLocatorModel) {
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.store_locator_content, new FragmentStoreLocatorMap());
        // Complete the changes added above
        ft.addToBackStack(null);
        ft.commit();
    }
}
