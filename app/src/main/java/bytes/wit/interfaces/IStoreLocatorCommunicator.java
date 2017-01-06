package bytes.wit.interfaces;

import java.util.ArrayList;

import bytes.wit.models.StoreLocatorModel;

/**
 * Created by Md. Sifat-Ul Haque on 1/6/2017.
 */

public interface IStoreLocatorCommunicator {
    void updateListData(ArrayList<StoreLocatorModel> storeLocatorModels);
}
