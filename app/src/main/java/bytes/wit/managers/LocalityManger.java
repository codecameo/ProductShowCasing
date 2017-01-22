package bytes.wit.managers;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.Locale;

import bytes.wit.application.ShowCasingApplication;
import bytes.wit.showcasing.R;
import bytes.wit.utils.Constant;

/**
 * Created by Md. Sifat-Ul Haque on 1/22/2017.
 */

public class LocalityManger {

    public final String[] countries, localities;
    private Context context;
    private static volatile LocalityManger mLocalityManger;
    private String mLocality, mCountry;
    public boolean isLocalityChanged = false;

    private LocalityManger(Context context) {
        this.context = context;

        Locale locale = Locale.getDefault();

        localities = context.getResources().getStringArray(R.array.locality);
        countries = context.getResources().getStringArray(R.array.country);

        String locality = SharedPrefManager.getLocality(context);
        String country = SharedPrefManager.getCountry(context);

        if (TextUtils.equals(locality, SharedPrefManager.PREF_DEFAULT_LOCALITY) && TextUtils.equals(country, SharedPrefManager.PREF_DEFAULT_LOCALITY)) {
            locality = locale.getLanguage();
            country = locale.getCountry();

            if (!Arrays.asList(localities).contains(locality)
                    || !Arrays.asList(countries).contains(country)) {
                locality = Constant.DEFAULT_LOCALITY;
                country = Constant.DEFAULT_COUNTRY;
            }
        } else {
            mLocality = locality;
            mCountry = country;
        }
        setLocality(locality, country, false);
    }

    public static LocalityManger getInstance(Context context) {
        if (mLocalityManger == null) {
            synchronized (LocalityManger.class) {
                if (mLocalityManger == null) {
                    mLocalityManger = new LocalityManger(context);
                }
            }
        }
        return mLocalityManger;
    }

    public void setLocality(String locality, String country, boolean isChanged) {
        SharedPrefManager.setLocalityAndCountry(context, locality, country);
        mLocality = SharedPrefManager.getLocality(context);
        mCountry = SharedPrefManager.getCountry(context);
    }


    public void updateLocality(String language, String country, boolean isChanged) {
        setLocality(language, country, isChanged);
        ShowCasingApplication.getInstance().updateContextWrapper();
    }

    @SuppressWarnings("deprecation")
    public static Locale getSystemLocaleLegacy(Configuration config) {
        return config.locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static Locale getSystemLocale(Configuration config) {
        return config.getLocales().get(0);
    }

    @SuppressWarnings("deprecation")
    public static void setSystemLocaleLegacy(Configuration config, Locale locale) {
        config.locale = locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static void setSystemLocale(Configuration config, Locale locale) {
        config.setLocale(locale);
    }

    public String getLocality() {
        return mLocality;
    }

    public String getCountry() {
        return mCountry;
    }
}