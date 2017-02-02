package bytes.wit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import bytes.wit.models.ProductSpecificationModel;
import bytes.wit.showcasing.R;

/**
 * Created by Md. Sifat-Ul Haque on 1/27/2017.
 */

public class SpecificationAdapter extends RecyclerView.Adapter<SpecificationAdapter.SpecificationViewHolder> {

    private ArrayList<ProductSpecificationModel> mSpecifications;

    public SpecificationAdapter() {
        mSpecifications = new ArrayList<>();

        ProductSpecificationModel specificationModel = new ProductSpecificationModel();

        specificationModel.setTitle("Weight");
        specificationModel.setDescription("25g");
        mSpecifications.add(specificationModel);

        specificationModel = new ProductSpecificationModel();

        specificationModel.setTitle("Flavor");
        specificationModel.setDescription("Chocolate, Vanilla cream in between chocolate cakes.");
        mSpecifications.add(specificationModel);

        specificationModel = new ProductSpecificationModel();

        specificationModel.setTitle("Shelf Life");
        specificationModel.setDescription("4 Months");
        mSpecifications.add(specificationModel);

        specificationModel = new ProductSpecificationModel();

        specificationModel.setTitle("Retail Cartoon");
        specificationModel.setDescription("Layer Cake 25g- (1 x 48 pcs ) 11.5 inch x 11.5 inch x 10. 7 inch");
        mSpecifications.add(specificationModel);

    }

    @Override
    public SpecificationAdapter.SpecificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_specification, parent, false);
        return new SpecificationAdapter.SpecificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SpecificationAdapter.SpecificationViewHolder holder, int position) {
        holder.bindTo(mSpecifications.get(position));
    }

    @Override
    public int getItemCount() {
        return mSpecifications.size();
    }

    public class SpecificationViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvTitle, mTvDescription;

        public SpecificationViewHolder(View itemView) {
            super(itemView);
            initViews();

        }

        private void initViews() {
            mTvDescription = (TextView) itemView.findViewById(R.id.tv_specification_description);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_specification_title);
        }

        public void bindTo(ProductSpecificationModel productSpecificationModel) {
            mTvTitle.setText(productSpecificationModel.getTitle());
            mTvDescription.setText(productSpecificationModel.getDescription());
        }
    }
}
