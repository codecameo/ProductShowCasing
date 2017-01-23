package bytes.wit.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Md. Sifat-Ul Haque on 12/27/2016.
 */

public class HorizontalProductListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private onProductSelectedListener mOnProductSelectedListener;

    public HorizontalProductListViewHolder(View itemView) {
        super(itemView);
        initListeners();
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

    public interface onProductSelectedListener {
        void onProductSelected(int position);
    }
}
