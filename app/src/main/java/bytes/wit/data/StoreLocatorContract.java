package bytes.wit.data;

import android.net.Uri;
import android.provider.BaseColumns;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sharifur Rahaman on 1/30/2017.
 * Email: sharif.iit.du@gmail.com
 */

public class StoreLocatorContract {

    // The authority, which is how your code knows which Content Provider to access
    public static final String AUTHORITY = "bytes.wit";

    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // Define the possible paths for accessing data in this contract
    // This is the path for the "store locators" directory
    public static final String PATH_STORE_LOCATORS = "store_locators";

    public static final class StoreLocatorEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_STORE_LOCATORS).build();

        // Task table and column names
        public static final String TABLE_NAME = "store_locators";
        public static final String COLUMN_STORE_ADDRESS = "store_address";
        public static final String COLUMN_MOBILE_NUMBER = "mobile_number";
        public static final String COLUMN_DISTRICT = "district";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";

    }
}