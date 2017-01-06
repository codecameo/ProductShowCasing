package bytes.wit.showcasing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import bytes.wit.adapters.MyStoreLocatorAdapter;
import bytes.wit.interfaces.OnListItemClickListener;
import bytes.wit.models.DummyStoreLocatorContent;

/**
 * Created by Md. Sifat-Ul Haque on 1/4/2017.
 */

public class StoreLocatorListActivity extends BaseActivity implements OnListItemClickListener {

    private boolean mTwoPane;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_locator_list);
        initViews();
        setupToolbar();
        initStoreLocatorListAdapter();
    }

    private void initStoreLocatorListAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyStoreLocatorAdapter(DummyStoreLocatorContent.ITEMS, this));

    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_store_locator_list);
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
    public void OnItemClicked(int position) {
     startStoreLocatorDetailActivity();
    }

    /**
     * Start store locator detail activity when single pan otherwise we load the detail fragment.
     */
    private void startStoreLocatorDetailActivity(){
        Intent intent = new Intent(this, StoreLocatorDetailActivity.class);
        //intent.putExtra(SQLListActivityDetailFragment.ARG_ITEM_ID, holder.mItem.id);
        startActivity(intent);
    }
}
