package bytes.wit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bytes.wit.showcasing.R;

/**
 * Created by Md. Sifat-Ul Haque on 1/25/2017.
 */

public class PackageImageListAdapter extends RecyclerView.Adapter<PackageImageListAdapter.PackageImageViewHolder> {

    private OnPackageImageSelectedListener mOnPackageImageSelectedListener;

    public PackageImageListAdapter(OnPackageImageSelectedListener onPackageImageSelectedListener) {
        mOnPackageImageSelectedListener = onPackageImageSelectedListener;
    }

    @Override
    public PackageImageListAdapter.PackageImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PackageImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_package_image, parent, false));
    }

    @Override
    public void onBindViewHolder(PackageImageListAdapter.PackageImageViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class PackageImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public PackageImageViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mOnPackageImageSelectedListener.onPackageImageSelected(getAdapterPosition());
        }
    }


    public interface OnPackageImageSelectedListener {
        void onPackageImageSelected(int position);
    }

}
