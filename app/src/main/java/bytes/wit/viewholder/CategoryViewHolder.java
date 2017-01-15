package bytes.wit.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import bytes.wit.adapters.HorizontalProductListAdapter;
import bytes.wit.models.CategoryModel;
import bytes.wit.showcasing.R;
import bytes.wit.snap.GravitySnapHelper;

/**
 * Created by Md. Sifat-Ul Haque on 12/27/2016.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private RecyclerView mProductList;
    private HorizontalProductListAdapter mProductListAdapter;
    private TextView mTvCategoryTitle, mTvViewAll;
    private CategoryModel mCategoryModel;
    private OnViewAllProductList mOnViewAllProductList;

    public CategoryViewHolder(View itemView) {
        super(itemView);

        initViews(itemView);
        initListeners();
        initVariables();
        setupProductList();
    }

    private void initListeners() {
        mTvViewAll.setOnClickListener(this);
    }

    private void setupProductList() {
        mProductList.setAdapter(mProductListAdapter);
        //mProductList.addItemDecoration(new DividerItemDecoration());
        mProductList.setLayoutManager(new LinearLayoutManager(mProductList.getContext(), LinearLayoutManager.HORIZONTAL, false));
        new GravitySnapHelper(Gravity.START).attachToRecyclerView(mProductList);
    }

    private void initVariables() {
        mProductListAdapter = new HorizontalProductListAdapter();
    }

    private void initViews(View view) {
        mTvCategoryTitle = (TextView) view.findViewById(R.id.tv_category_name);
        mProductList = (RecyclerView) view.findViewById(R.id.rv_product_list);
        mTvViewAll = (TextView) view.findViewById(R.id.tv_view_all);
    }

    public void bindTo(CategoryModel categoryModel) {
        mCategoryModel = categoryModel;
        mTvCategoryTitle.setText(categoryModel.getCategory_name());
    }

    @Override
    public void onClick(View view) {
        mOnViewAllProductList.onViewAllSelected(mCategoryModel);
    }

    public void setOnViewAllProductList(OnViewAllProductList onViewAllProductList) {
        mOnViewAllProductList = onViewAllProductList;
    }

    public interface OnViewAllProductList {
        void onViewAllSelected(CategoryModel categoryModel);
    }
}
