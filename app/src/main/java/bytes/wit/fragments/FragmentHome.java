package bytes.wit.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import bytes.wit.adapters.HomeCategorizedListAdapter;
import bytes.wit.factories.ProviderFactory;
import bytes.wit.interfaces.IProductProvider;
import bytes.wit.models.CategoryModel;
import bytes.wit.models.ProductModel;
import bytes.wit.receivers.ProductResultReceiver;
import bytes.wit.showcasing.ProductDetailActivity;
import bytes.wit.showcasing.R;
import bytes.wit.showcasing.ShowProductListActivity;
import bytes.wit.utils.Constant;
import bytes.wit.viewholder.CategoryViewHolder;
import bytes.wit.wrappers.ProductProviderAdapter;
import cameo.code.imageslider.SliderFragment;

import static bytes.wit.showcasing.ShowProductListActivity.KEY_PRODUCT_LIST;

/**
 * Created by Md. Sifat-Ul Haque on 12/28/2016.
 */

public class FragmentHome extends Fragment implements ProductResultReceiver.Receiver,
        CategoryViewHolder.OnViewAllProductList {

    private ArrayList<String> mImagesUrl;
    private SliderFragment mSliderFragment;
    private RecyclerView mHomeList;
    private HomeCategorizedListAdapter mHomeCategorizedListAdapter;
    private ProviderFactory mProviderFactory;
    private IProductProvider mIProductProvider;
    private ProductResultReceiver mProductResultReceiver;
    private Handler mHandler;
    private CategoryModel mCategoryModel;
    private ProgressBar mProgressBar;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initVariables();
        setDefaultValues();
        showSlider();
        setupListAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);
        return view;
    }

    private void setupListAdapter() {
        mHomeList.setAdapter(mHomeCategorizedListAdapter);
        mHomeList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void showSlider() {
        final FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.slider_panel, mSliderFragment);
        transaction.commit();
    }

    private void setDefaultValues() {

        /*mImagesUrl.add("http://buyersguide.caranddriver.com/media/assets/submodel/7757.jpg");
        mImagesUrl.add("http://buyersguide.caranddriver.com/media/assets/submodel/6956.jpg");
        mImagesUrl.add("https://buyersguide.caranddriver.com/media/assets/submodel/1470.jpg");*/

        mImagesUrl.add("http://www.dancake.com.bd/images/home-slider/1479303628_th_1.jpg");
        mImagesUrl.add("http://www.dancake.com.bd/images/home-slider/1479303820_th_2.jpg");
        mImagesUrl.add("http://www.dancake.com.bd/images/home-slider/1479303916_th_3.jpg");

        mProductResultReceiver.setReceiver(this);
        mIProductProvider.getCategorizedProductList();

    }


    private void initVariables() {
        mImagesUrl = new ArrayList<>();
        mSliderFragment = SliderFragment.createWithPath(mImagesUrl);
        mHomeCategorizedListAdapter = new HomeCategorizedListAdapter(this);
        mProviderFactory = ProviderFactory.getProviderInstance();
        mHandler = new Handler();
        mProductResultReceiver = new ProductResultReceiver(mHandler);
        mIProductProvider = mProviderFactory.getProductProvider(getActivity(), mProductResultReceiver);
    }

    private void initViews(View view) {
        mHomeList = (RecyclerView) view.findViewById(R.id.rv_home_product_list);
        mProgressBar = (ProgressBar) view.findViewById(R.id.loader);
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        if (Constant.API_RESPONSE_SUCCESSFUL == resultCode) {
            ArrayList<CategoryModel> categoryModels = (ArrayList<CategoryModel>) resultData.getSerializable(ProductProviderAdapter.KEY_CATEGORIZED_PRODUCT);
            mHomeCategorizedListAdapter.updateData(categoryModels);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onViewAllSelected(CategoryModel categoryModel) {
        Intent intent = new Intent(getActivity(), ShowProductListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_PRODUCT_LIST, categoryModel);
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }

    @Override
    public void onProductSelected(ProductModel productModel) {

        /***
         * To Do provide necessary info to show corresponding product detail
         * */
        getActivity().startActivity(new Intent(getActivity(), ProductDetailActivity.class));
    }
}
