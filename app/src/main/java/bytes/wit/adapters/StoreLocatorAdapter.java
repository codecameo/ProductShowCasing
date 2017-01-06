package bytes.wit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bytes.wit.fragments.FragmentStoreList.OnListFragmentInteractionListener;
import bytes.wit.models.StoreLocatorModel;
import bytes.wit.showcasing.R;

/**
 * {@link RecyclerView.Adapter} that can display a {@link StoreLocatorModel} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class StoreLocatorAdapter extends RecyclerView.Adapter<StoreLocatorAdapter.ViewHolder> {

    private final List<StoreLocatorModel> mStoreLocatorModels;
    private final OnListFragmentInteractionListener mListener;

    public StoreLocatorAdapter(List<StoreLocatorModel> items, OnListFragmentInteractionListener listener) {
        mStoreLocatorModels = items;
        mListener = listener;
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView mIdView;
        public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);

            itemView.setOnClickListener(this);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        public void bindTo(StoreLocatorModel storeLocatorModel) {
            mIdView.setText(storeLocatorModel.getMobile_number());
            mContentView.setText(storeLocatorModel.getDistrict());
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onListFragmentInteraction(mStoreLocatorModels.get(getAdapterPosition()));
            }
        }
    }
}