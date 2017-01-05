package bytes.wit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bytes.wit.interfaces.OnListItemClickListener;
import bytes.wit.models.StoreLocatorModel;
import bytes.wit.showcasing.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link StoreLocatorModel} and makes a call to the
 * specified {@link OnListItemClickListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyStoreLocatorAdapter extends RecyclerView.Adapter<MyStoreLocatorAdapter.ViewHolder> {

    private final List<StoreLocatorModel> mStoreLocatorModels;
    private final OnListItemClickListener mOnListItemClickListener;

    public MyStoreLocatorAdapter(List<StoreLocatorModel> items, OnListItemClickListener onListItemClickListener) {
        mStoreLocatorModels = items;
        mOnListItemClickListener = onListItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_store_locator, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bindTo(mStoreLocatorModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mStoreLocatorModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mIdView;
        public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mOnListItemClickListener) {
                        // Notify the active callbacks interface (the activity, if the
                        // fragment is attached to one) that an item has been selected.
                        mOnListItemClickListener.OnItemClicked(getAdapterPosition());
                    }
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        public void bindTo(StoreLocatorModel storeLocatorModel) {
            mIdView.setText(storeLocatorModel.getMobile_number());
            mContentView.setText(storeLocatorModel.getDistrict());
        }
    }
}
