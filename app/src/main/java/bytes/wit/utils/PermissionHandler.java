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

import bytes.wit.showcasing.R;


/**
 * Created by Md. Sifat-Ul Haque on 1/17/2017.
 */
public class PermissionHandler {

    private static final int REQUEST_SETTINGS = 444;

    // permission request code for location
    private static final int REQUEST_BOTH_LOCATION_PERMISSION = 331;
    private static final int REQUEST_COARSE_LOCATION = 332;
    private static final int REQUEST_FINE_LOCATION = 333;
    private Activity activity;

    public PermissionHandler(Context context) {
        this.activity = (Activity) context;
    }

    public boolean hasCameraPermission() {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
    }

    public boolean hasExternalStorageWritePermission() {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    public boolean hasExternalStorageReadPermission() {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    public boolean hasRecordAudioPermission() {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_GRANTED;
    }

    public boolean hasContactReadPermission() {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED;
    }

    public boolean hasContactWritePermission() {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_CONTACTS)
                == PackageManager.PERMISSION_GRANTED;
    }

    public boolean hasCoarseLocationPermission() {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    public boolean hasFineLocationPermission() {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    public void showSettingsSnackbar(String message) {

        View view = activity.findViewById(android.R.id.content);
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction(activity.getString(R.string.settings), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent settingsIntent = new Intent();
                        settingsIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        settingsIntent.addCategory(Intent.CATEGORY_DEFAULT);
                        settingsIntent.setData(Uri.parse("package:" + activity.getPackageName()));
                        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        activity.startActivityForResult(settingsIntent, REQUEST_SETTINGS);
                    }
                })
                .setActionTextColor(ContextCompat.getColor(activity, R.color.colorPrimary))
                .show();
    }


    //=== request permission set for location ===//
    public void requestBothLocationPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                && ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.ACCESS_FINE_LOCATION)) {

            showSettingsSnackbar(activity.getResources().getString(R.string.permission_msg_location));

        } else {
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_BOTH_LOCATION_PERMISSION);
        }

    }

    public void requestCoarseLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION)) {

            showSettingsSnackbar(activity.getResources().getString(R.string.permission_msg_location));

        } else {
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_COARSE_LOCATION);
        }
    }

    public void requestFineLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.ACCESS_FINE_LOCATION)) {

            showSettingsSnackbar(activity.getResources().getString(R.string.permission_msg_location));

        } else {
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_FINE_LOCATION);
        }
    }

}

