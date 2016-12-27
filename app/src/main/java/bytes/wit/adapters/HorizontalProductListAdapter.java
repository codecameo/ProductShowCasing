package bytes.wit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bytes.wit.showcasing.R;
import bytes.wit.viewholder.CategoryViewHolder;
import bytes.wit.viewholder.HorizontalProductListViewHolder;

/**
 * Created by Md. Sifat-Ul Haque on 12/27/2016.
 */

public class HorizontalProductListAdapter extends RecyclerView.Adapter<HorizontalProductListViewHolder> {

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_home_product_list;
    }

    @Override
    public HorizontalProductListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HorizontalProductListViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false));
    }

    @Override
    public void onBindViewHolder(HorizontalProductListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}