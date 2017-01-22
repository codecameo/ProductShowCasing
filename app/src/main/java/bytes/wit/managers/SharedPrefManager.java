package bytes.wit.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Md. Sifat-Ul Haque on 1/22/2017.
 */

public class SharedPrefManager {

    private static final String PREF_LOCALITY = "locality";
    private static final String PREF_COUNTRY = "country";
    public static final String PREF_DEFAULT_LOCALITY = "na";

    public static String getLocality(Context context) {
        SharedPreferences localityPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return localityPrefs.getString(PREF_LOCALITY, PREF_DEFAULT_LOCALITY);
    }

    public static String getCountry(Context context) {
        SharedPreferences localityPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return localityPrefs.getString(PREF_COUNTRY, PREF_DEFAULT_LOCALITY);
    }

    public static void setLocalityAndCountry(Context context, String locality, String country) {
        SharedPreferences localityPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = localityPrefs.edit();
        editor.putString(PREF_LOCALITY, locality);
        editor.putString(PREF_COUNTRY, country);
        editor.apply();
    }
}
