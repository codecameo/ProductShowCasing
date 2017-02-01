package bytes.wit.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
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
import bytes.wit.utils.Constant;

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

        public final TextView tvAddress;
        public final TextView tvPhone;
        public final TextView tvEmail;
        public final TextView tvDistance;
        public final TextView tvDistrict;

        public ViewHolder(View view) {
            super(view);
            tvAddress = (TextView) view.findViewById(R.id.tv_address);
            tvPhone = (TextView) view.findViewById(R.id.tv_phone);
            tvEmail = (TextView) view.findViewById(R.id.tv_email);
            tvDistance = (TextView) view.findViewById(R.id.tv_distance);
            tvDistrict = (TextView) view.findViewById(R.id.tv_district);
            itemView.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvPhone.getText() + "'";
        }

        public void bindTo(StoreLocatorModel storeLocatorModel) {
            tvAddress.setText(storeLocatorModel.getStore_address());
            tvEmail.setText(Constant.EMAIL_TAG + storeLocatorModel.getEmail());
            tvPhone.setText(Constant.PHONE_TAG + storeLocatorModel.getMobile_number());
            tvDistrict.setText(storeLocatorModel.getDistrict());
            tvDistance.setText(getFormattedDistance(storeLocatorModel.getDistance(), storeLocatorModel.getDistance_unit()));
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onListFragmentInteraction(getAdapterPosition());
            }
        }
    }

    private SpannableString getFormattedDistance(double dist, String distance_unit) {
        String distance = dist + "\n" + distance_unit;
        int index = distance.length() - distance_unit.length();
        SpannableString spannableString=  new SpannableString(distance);
        spannableString.setSpan(new RelativeSizeSpan(1.3f), 0, index, 0);
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, index, 0);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, index, 0);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), index, distance.length(), 0);
        return spannableString;
    }
}
