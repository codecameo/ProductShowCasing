package bytes.wit.showcasing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

/**
 * Created by Md. Sifat-Ul Haque on 1/23/2017.
 */

public class ProductDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        setupToolbar(R.id.toolbar_product_detail);
    }

    @Override
    protected void setupToolbar(int id) {
        super.setupToolbar(id);
        getSupportActionBar().setTitle(R.string.title_activity_product_detail);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
