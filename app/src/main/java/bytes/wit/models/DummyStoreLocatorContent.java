package bytes.wit.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Sharifur Rahaman.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyStoreLocatorContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<StoreLocatorModel> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, StoreLocatorModel> ITEM_MAP = new HashMap<String, StoreLocatorModel>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(StoreLocatorModel storeLocatorModel) {
        ITEMS.add(storeLocatorModel);
        ITEM_MAP.put(storeLocatorModel.getDistrict(), storeLocatorModel);
    }

    private static StoreLocatorModel createDummyItem(int position) {
        StoreLocatorModel storeLocatorModel = new StoreLocatorModel();
        storeLocatorModel.setDistrict(String.valueOf(position));
        storeLocatorModel.setMobile_number(makeDetails(position));
        return storeLocatorModel;
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

}
