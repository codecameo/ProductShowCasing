package bytes.wit.showcasing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bytes.wit.showcasing.fragment.StoreLocatorDetailFragment;

public class StoreLocatorDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_locator_detail);

        setupToolbar();

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_detail_container, StoreLocatorDetailFragment.newInstance())
                    .commit();
        }


    }
}
