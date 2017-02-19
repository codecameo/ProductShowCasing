package bytes.wit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bytes.wit.showcasing.R;

/**
 * Created by Md. Sifat-Ul Haque on 1/24/2017.
 */

public class PackageListAdapter extends RecyclerView.Adapter<PackageListAdapter.PackageViewHolder> {

    private int mSelectedPosition = 0;

    @Override
    public PackageListAdapter.PackageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PackageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_package_list, parent, false));
    }

    @Override
    public void onBindViewHolder(PackageListAdapter.PackageViewHolder holder, int position) {
        holder.bindTo();
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class PackageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public PackageViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() == mSelectedPosition) return;
            updatePreviousSelectionView();
        }

        private void updatePreviousSelectionView() {
            int temp = mSelectedPosition;
            mSelectedPosition = getAdapterPosition();
            notifyItemChanged(temp);
            notifyItemChanged(mSelectedPosition);
        }

        public void bindTo() {

            /**
             * Highlight selected package tag
             * */
            if (getAdapterPosition() == mSelectedPosition) {
                itemView.setSelected(true);
            } else {
                itemView.setSelected(false);
            }

            if (getAdapterPosition() == 0) {
                ((TextView) itemView).setText("Family Pack");
            } else if (getAdapterPosition() == 1) {
                ((TextView) itemView).setText("Regular Pack");
            } else if (getAdapterPosition() == 2) {
                ((TextView) itemView).setText("Pocket Pack");
            } else if (getAdapterPosition() == 3) {
                ((TextView) itemView).setText("Mini Pack");
            }

        }
    }
}
