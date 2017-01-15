package bytes.wit.fragments;

/**
 * Created by Md. Sifat-Ul Haque on 1/15/2017.
 */

public class FragmentProductList extends BaseCommonProductListFragment {

    @Override
    protected void fetchData() {
        super.fetchData();
        mIProductProvider.getCategorizedProductList();
    }
}
