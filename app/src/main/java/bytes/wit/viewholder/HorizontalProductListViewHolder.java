package bytes.wit.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import bytes.wit.models.ProductModel;
import bytes.wit.showcasing.R;

/**
 * Created by Md. Sifat-Ul Haque on 12/27/2016.
 */

public class HorizontalProductListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mTvProductName;
    private ImageView mIvProductImage;
    private onProductSelectedListener mOnProductSelectedListener;

    public HorizontalProductListViewHolder(View itemView) {
        super(itemView);
        initViews(itemView);
        initListeners();
    }

    private void initViews(View itemView) {
        mTvProductName = (TextView) itemView.findViewById(R.id.tv_horizontal_product_name);
        mIvProductImage = (ImageView) itemView.findViewById(R.id.iv_horizontal_product_image);
    }

    private void initListeners() {
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mOnProductSelectedListener.onProductSelected(getAdapterPosition());
    }

    public void setOnProductSelectedListener(onProductSelectedListener mOnProductSelectedListener) {
        this.mOnProductSelectedListener = mOnProductSelectedListener;
    }

    public void bindTo(ProductModel productModel) {
        mTvProductName.setText(productModel.getProduct_name());
        Picasso.with(mIvProductImage.getContext())
                .load(productModel.getPoster().getContent_url())
                .into(mIvProductImage);
    }

    public interface onProductSelectedListener {
        void onProductSelected(int position);
    }
}
