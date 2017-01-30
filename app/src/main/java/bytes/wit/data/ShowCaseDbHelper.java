package bytes.wit.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sharifur Rahaman on 1/30/2017.
 * Email: sharif.iit.du@gmail.com
 */

public class ShowCaseDbHelper extends SQLiteOpenHelper{

    //The name of the database for our store.
    private static final String DATABASE_NAME = "showcaseDb.db";

    //To upgrade database we must maintain versioning for our database
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public ShowCaseDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the tasks database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create store locators table (careful to follow SQL formatting rules)
        final String CREATE_STORE_LOCATOR_TABLE = "CREATE TABLE "  + StoreLocatorContract.StoreLocatorEntry.TABLE_NAME + " (" +
                StoreLocatorContract.StoreLocatorEntry._ID                + " INTEGER PRIMARY KEY, " +
                StoreLocatorContract.StoreLocatorEntry.COLUMN_STORE_ADDRESS + " TEXT NOT NULL, " +
                StoreLocatorContract.StoreLocatorEntry.COLUMN_MOBILE_NUMBER + " TEXT NOT NULL, " +
                StoreLocatorContract.StoreLocatorEntry.COLUMN_DISTRICT + " TEXT NOT NULL, " +
                StoreLocatorContract.StoreLocatorEntry.COLUMN_LATITUDE + " TEXT NOT NULL, " +
                StoreLocatorContract.StoreLocatorEntry.COLUMN_LONGITUDE    + " INTEGER NOT NULL);";


        db.execSQL(CREATE_STORE_LOCATOR_TABLE);
    }


    /**
     * This method discards the old table of data and calls onCreate to recreate a new one.
     * This only occurs when the version number for this database (DATABASE_VERSION) is incremented.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + StoreLocatorContract.StoreLocatorEntry.TABLE_NAME);
        onCreate(db);
    }
}
