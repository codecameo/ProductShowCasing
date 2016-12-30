package bytes.wit.interfaces;

/**
 * Created by Md. Sifat-Ul Haque on 12/30/2016.
 */

public interface IProductProvider {
    void getProductList();

    void getProductList(String categoryID);

    void getFilteredProductList();

    void getCategorizedProductList();
}
