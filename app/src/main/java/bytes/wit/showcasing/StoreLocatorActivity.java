package bytes.wit.showcasing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import bytes.wit.models.StoreLocatorModel;
import bytes.wit.showcasing.fragment.StoreLocatorFragment;

/**
 * Created by Md. Sifat-Ul Haque on 1/4/2017.
 */

public class StoreLocatorActivity extends BaseActivity implements StoreLocatorFragment.OnListFragmentInteractionListener{

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
        ft.replace(R.id.store_locator_content, StoreLocatorFragment.newInstance(2));
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

    }
}
