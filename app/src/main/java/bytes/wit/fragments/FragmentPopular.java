package bytes.wit.fragments;

/**
 * Created by Md. Sifat-Ul Haque on 12/29/2016.
 */

public class FragmentPopular extends BaseCommonProductListFragment {

    @Override
    protected void fetchData() {
        mIProductProvider.getPopularProductList();
    }
}
