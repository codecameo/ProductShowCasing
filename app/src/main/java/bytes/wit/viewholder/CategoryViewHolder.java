package bytes.wit.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import bytes.wit.adapters.HorizontalProductListAdapter;
import bytes.wit.showcasing.R;

/**
 * Created by Md. Sifat-Ul Haque on 12/27/2016.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private RecyclerView mProductList;
    private HorizontalProductListAdapter mProductListAdapter;

    public CategoryViewHolder(View itemView) {
        super(itemView);

        initViews(itemView);
        initVariables();
        setupProductList();
    }

    private void setupProductList() {
        mProductList.setAdapter(mProductListAdapter);
        mProductList.setLayoutManager(new LinearLayoutManager(mProductList.getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void initVariables() {
        mProductListAdapter = new HorizontalProductListAdapter();
    }

    private void initViews(View view) {
        mProductList = (RecyclerView) view.findViewById(R.id.rv_product_list);
    }
}
