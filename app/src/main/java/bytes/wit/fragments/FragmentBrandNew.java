package bytes.wit.fragments;

/**
 * Created by Md. Sifat-Ul Haque on 1/14/2017.
 */

public class FragmentBrandNew extends BaseCommonProductListFragment {

    @Override
    protected void fetchData() {
        super.fetchData();
        mIProductProvider.getNewProductList();
    }
}
