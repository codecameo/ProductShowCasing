package bytes.wit.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.Locale;

import bytes.wit.application.ShowCasingApplication;
import bytes.wit.managers.LocalityManger;
import bytes.wit.managers.SharedPrefManager;
import bytes.wit.showcasing.R;

/**
 * Created by Md. Sifat-Ul Haque on 1/22/2017.
 */

public class LocalityChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Locale locale = Locale.getDefault();
        String locality = locale.getLanguage();
        String country = locale.getCountry();

        String[] localities = context.getResources().getStringArray(R.array.locality);
        String[] countries = context.getResources().getStringArray(R.array.country);

        if (!Arrays.asList(localities).contains(locality)
                || !Arrays.asList(countries).contains(country)) {
            locality = TextUtils.equals(SharedPrefManager.getLocality(context), "na") ? "en" : SharedPrefManager.getLocality(context);
            country = TextUtils.equals(SharedPrefManager.getCountry(context), "na") ? "US" : SharedPrefManager.getCountry(context);
        }

        LocalityManger.getInstance(context).setLocality(locality, country, false);
        ShowCasingApplication.getInstance().updateContextWrapper();
    }
}
