package bytes.wit.showcasing;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

/**
 * Created by Md. Sifat-Ul Haque on 10/5/2016.
 */
public class BaseActivity extends AppCompatActivity {

    /* Primary toolbar*/
    private ProgressDialog progressDialog;
    private boolean isPaused = false;
    public Toolbar toolbar;
    public ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //overridePendingTransition(R.anim.activity_transition_slide_in, R.anim.activity_transition_slide_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPaused = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPaused = true;
    }

    /**
     * Set status bar transparent.
     *
     * @param makeTranslucent Set true or false.
     */
    public void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * Set toolbar into actionbar.
     */
    protected void setupToolbar() {
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
        }

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        }
    }


    /**
     * Initialize the loader for Child class whenever necessary.
     */
    public void initProgressLoader() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
    }

    /**
     * Initialize the loader for Child class whenever necessary.
     */
    public void initProgressLoader(boolean isCancelable) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(isCancelable);
    }


    /**
     * Sets whether this dialog is cancelable with the
     */
    protected void setProgressCancelable(boolean isCancelable) {
        if (progressDialog != null) {
            progressDialog.setCancelable(isCancelable);
        }
    }

    /**
     * Show progress dialog.
     *
     * @param message The message show in the progress dialog initially.
     */
    public void showProgressDialog(String message) {
        if (progressDialog == null)
            initProgressLoader();
        if (progressDialog != null) {
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    /**
     * Cancel progress dialog.
     */
    public void cancelProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    protected void showSnackBar(String message) {
        Snackbar.make(getWindow().getDecorView(), message, Snackbar.LENGTH_SHORT).show();
    }

    /*protected void rebuildThemeBasedUI() {
        mProfileTheme = Utils.getProfileColor(General.getDefaultProfileTheme(this));
        getWindow().getDecorView().setBackgroundResource(mProfileTheme.getBackground());
    }*/



    /*@Override
    public void onBackPressed() {
        if (!isPaused) {
            super.onBackPressed();
            overridePendingTransition(R.anim.activity_transition_slide_back_to_screen, R.anim.activity_transition_slide_back);
        } else {
            finish();
        }
    }

    protected void finishWithAnim() {
        finish();
        overridePendingTransition(R.anim.activity_transition_slide_back_to_screen, R.anim.activity_transition_slide_back);
    }*/
}
