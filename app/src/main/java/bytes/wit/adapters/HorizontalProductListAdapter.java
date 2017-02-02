package bytes.wit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import bytes.wit.models.ProductModel;
import bytes.wit.showcasing.R;
import bytes.wit.viewholder.CategoryViewHolder;
import bytes.wit.viewholder.HorizontalProductListViewHolder;

/**
 * Created by Md. Sifat-Ul Haque on 12/27/2016.
 */

public class HorizontalProductListAdapter extends RecyclerView.Adapter<HorizontalProductListViewHolder> implements HorizontalProductListViewHolder.onProductSelectedListener {


    private CategoryViewHolder.OnProductSelectedListener mOnProductSelectedListener;
    private ArrayList<ProductModel> mProductModels;

    public HorizontalProductListAdapter(ArrayList<ProductModel> productModels) {
        mProductModels = productModels;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_home_product_list;
    }

    @Override
    public HorizontalProductListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        HorizontalProductListViewHolder holder = new HorizontalProductListViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
        holder.setOnProductSelectedListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(HorizontalProductListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public void setOnProductSelectedListener(CategoryViewHolder.OnProductSelectedListener onProductSelectedListener) {
        this.mOnProductSelectedListener = onProductSelectedListener;
    }

    @Override
    public void onProductSelected(int position) {
        //mOnViewAllProductList.onProductSelected(mProductModels.get(position));
        mOnProductSelectedListener.onProductSelected(new ProductModel());
    }
}