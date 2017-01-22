package bytes.wit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;

import bytes.wit.models.ProductModel;
import bytes.wit.showcasing.R;

/**
 * Created by Md. Sifat-Ul Haque on 1/17/2017.
 */

public class ProductSearchAdapter extends RecyclerView.Adapter<ProductSearchAdapter.SearchedProductViewHolder> {

    private ArrayList<ProductModel> mProductModels;

    public ProductSearchAdapter() {
        mProductModels = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if (mProductModels.size() == 0)
            return R.layout.item_no_search_result;
        else
            return R.layout.item_store_locator;
    }

    @Override
    public ProductSearchAdapter.SearchedProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);
        return new ProductSearchAdapter.SearchedProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductSearchAdapter.SearchedProductViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return Math.max(1, mProductModels.size());
    }

    public class SearchedProductViewHolder extends RecyclerView.ViewHolder {
        public SearchedProductViewHolder(View itemView) {
            super(itemView);

            if ((itemView instanceof FrameLayout)) {

            }
        }
    }
}
