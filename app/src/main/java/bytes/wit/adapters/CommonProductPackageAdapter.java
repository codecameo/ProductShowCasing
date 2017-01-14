package bytes.wit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bytes.wit.showcasing.R;

/**
 * Created by Md. Sifat-Ul Haque on 1/14/2017.
 */

public class CommonProductPackageAdapter extends RecyclerView.Adapter<CommonProductPackageAdapter.ProductPackageViewholder> {


    @Override
    public CommonProductPackageAdapter.ProductPackageViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductPackageViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_common_product_package_list, parent, false));
    }

    @Override
    public void onBindViewHolder(CommonProductPackageAdapter.ProductPackageViewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ProductPackageViewholder extends RecyclerView.ViewHolder {
        public ProductPackageViewholder(View itemView) {
            super(itemView);
        }
    }
}
