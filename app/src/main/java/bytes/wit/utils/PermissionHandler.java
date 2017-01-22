package bytes.wit.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.lang.ref.WeakReference;

import bytes.wit.showcasing.R;


/**
 * Created by Md. Sifat-Ul Haque on 1/17/2017.
 */
public class PermissionHandler {

    private static final int REQUEST_SETTINGS = 444;

    // permission request code for location
    public static final int REQUEST_BOTH_LOCATION_PERMISSION = 331;
    public static final int REQUEST_COARSE_LOCATION = 332;
    public static final int REQUEST_FINE_LOCATION = 333;
    private WeakReference<Activity> activity;

    public PermissionHandler(Context context) {
        this.activity = new WeakReference<Activity>((Activity) context);
    }

    public boolean hasCameraPermission() {
        if (activity.get() != null) {
            return ContextCompat.checkSelfPermission(activity.get(),
                    Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public boolean hasExternalStorageWritePermission() {
        if (activity.get() != null) {
            return ContextCompat.checkSelfPermission(activity.get(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public boolean hasExternalStorageReadPermission() {
        if (activity.get() != null) {
            return ContextCompat.checkSelfPermission(activity.get(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public boolean hasRecordAudioPermission() {
        if (activity.get() != null) {
            return ContextCompat.checkSelfPermission(activity.get(),
                    Manifest.permission.RECORD_AUDIO)
                    == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public boolean hasContactReadPermission() {
        if (activity.get() != null) {
            return ContextCompat.checkSelfPermission(activity.get(),
                    Manifest.permission.READ_CONTACTS)
                    == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public boolean hasContactWritePermission() {
        if (activity.get() != null) {
            return ContextCompat.checkSelfPermission(activity.get(),
                    Manifest.permission.WRITE_CONTACTS)
                    == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public boolean hasCoarseLocationPermission() {
        if (activity.get() != null) {
            return ContextCompat.checkSelfPermission(activity.get(),
                    Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public boolean hasFineLocationPermission() {
        if (activity.get() != null) {
            return ContextCompat.checkSelfPermission(activity.get(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public void showSettingsSnackbar(String message) {

        if (activity.get() != null) {
            View view = activity.get().findViewById(android.R.id.content);
            Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                    .setAction(activity.get().getString(R.string.settings), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent settingsIntent = new Intent();
                            settingsIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            settingsIntent.addCategory(Intent.CATEGORY_DEFAULT);
                            settingsIntent.setData(Uri.parse("package:" + activity.get().getPackageName()));
                            settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            settingsIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                            activity.get().startActivityForResult(settingsIntent, REQUEST_SETTINGS);
                        }
                    })
                    .setActionTextColor(ContextCompat.getColor(activity.get(), R.color.colorPrimary))
                    .show();
        }
    }


    //=== request permission set for location ===//
    public void requestBothLocationPermission() {

        if (activity.get() != null) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity.get(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
                    && ActivityCompat.shouldShowRequestPermissionRationale(activity.get(), android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                showSettingsSnackbar(activity.get().getResources().getString(R.string.permission_msg_location));

            } else {
                ActivityCompat.requestPermissions(activity.get(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_BOTH_LOCATION_PERMISSION);
            }
        }

    }

    public void requestCoarseLocationPermission() {
        if (activity.get() != null) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity.get(), android.Manifest.permission.ACCESS_COARSE_LOCATION)) {

                showSettingsSnackbar(activity.get().getResources().getString(R.string.permission_msg_location));

            } else {
                ActivityCompat.requestPermissions(activity.get(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_COARSE_LOCATION);
            }
        }
    }

    public void requestFineLocationPermission() {
        if (activity.get() != null) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity.get(), android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                showSettingsSnackbar(activity.get().getResources().getString(R.string.permission_msg_location));

            } else {
                ActivityCompat.requestPermissions(activity.get(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_FINE_LOCATION);
            }
        }
    }

}

