package bytes.wit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bytes.wit.showcasing.R;

/**
 * Created by Md. Sifat-Ul Haque on 1/24/2017.
 */

public class PackageListAdapter extends RecyclerView.Adapter<PackageListAdapter.PackageViewHolder> {
    @Override
    public PackageListAdapter.PackageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PackageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_package_list, parent, false));
    }

    @Override
    public void onBindViewHolder(PackageListAdapter.PackageViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class PackageViewHolder extends RecyclerView.ViewHolder {
        public PackageViewHolder(View itemView) {
            super(itemView);
        }
    }
}
