package bytes.wit.adapters;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
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

    private List<StoreLocatorModel> mStoreLocatorModels;
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

    public void update(List<StoreLocatorModel> storeLocatorModels) {
        mStoreLocatorModels = storeLocatorModels;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView mTvAddress;
        public final TextView mTvPhone;
        public final TextView mTvEmail;
        public final TextView tvDistance;

        public ViewHolder(View view) {
            super(view);
            mTvAddress = (TextView) view.findViewById(R.id.tv_address);
            mTvPhone = (TextView) view.findViewById(R.id.tv_phone);
            mTvEmail = (TextView) view.findViewById(R.id.tv_email);
            tvDistance = (TextView) view.findViewById(R.id.tv_distance);
            itemView.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTvPhone.getText() + "'";
        }

        public void bindTo(StoreLocatorModel storeLocatorModel) {
            mTvAddress.setText(storeLocatorModel.getStore_address());
            mTvEmail.setText("Email: sharif.iit.du@gmail.com");
            mTvPhone.setText("Phone: "+storeLocatorModel.getMobile_number());

            tvDistance.setText(getFormattedDistance());
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onListFragmentInteraction(getAdapterPosition());
            }
        }
    }

    private SpannableString getFormattedDistance() {
        String distance= "3.6\nkm";
        SpannableString spannableString=  new SpannableString(distance);
        spannableString.setSpan(new RelativeSizeSpan(1.3f), 0,3, 0);
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0,3,0);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 0,3,0);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 3, distance.length(), 0);
        return spannableString;
    }

}
