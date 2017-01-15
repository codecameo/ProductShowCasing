package bytes.wit.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bytes.wit.adapters.CommonProductPackageAdapter;
import bytes.wit.factories.ProviderFactory;
import bytes.wit.interfaces.IProductProvider;
import bytes.wit.receivers.ProductResultReceiver;
import bytes.wit.showcasing.R;

/**
 * Created by Md. Sifat-Ul Haque on 1/14/2017.
 */

public class BaseCommonProductListFragment extends Fragment implements
        ProductResultReceiver.Receiver {

    private RecyclerView mRvProductPackageList;
    private CommonProductPackageAdapter mProductPackageAdapter;
    private ProviderFactory mProviderFactory;
    protected IProductProvider mIProductProvider;
    private ProductResultReceiver mProductResultReceiver;
    private Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initVariables();
        setUpListAdapter();
        setDefaultValues();
        fetchData();
    }

    private void setDefaultValues() {
    }

    protected void fetchData() {
        mProductResultReceiver.setReceiver(this);
    }

    private void initVariables() {
        mHandler = new Handler();
        mProductResultReceiver = new ProductResultReceiver(mHandler);
        mProductPackageAdapter = new CommonProductPackageAdapter();
        mProviderFactory = ProviderFactory.getProviderInstance();
        mIProductProvider = mProviderFactory.getProductProvider(getActivity(), mProductResultReceiver);
    }

    private void setUpListAdapter() {
        mRvProductPackageList.setAdapter(mProductPackageAdapter);
        mRvProductPackageList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initViews(View view) {
        mRvProductPackageList = (RecyclerView) view.findViewById(R.id.rv_product_package_list);
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {

    }

    /*private class ProductResultReceiver extends ResultReceiver {

        public ProductResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if (Constant.API_RESPONSE_SUCCESSFUL == resultCode) {
                Toast.makeText(getActivity(), "Got data", Toast.LENGTH_SHORT).show();
                //ArrayList<CategoryModel> categoryModels = (ArrayList<CategoryModel>) resultData.getSerializable(ProductProviderAdapter.KEY_CATEGORIZED_PRODUCT);
                //mHomeCategorizedListAdapter.updateData(categoryModels);
            }
        }
    }*/

}
