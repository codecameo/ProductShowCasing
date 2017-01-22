package bytes.wit.showcasing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import bytes.wit.fragments.FragmentLanguage;

public class LanguageSelectionActivity extends BaseActivity implements FragmentLanguage.onLanguageChangedListener {

    private FragmentLanguage mFragmentLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);
        setupToolbar(R.id.toolbar_language);
        initVariables();
        initListeners();
        addLanguageFragment();
    }

    @Override
    protected void setupToolbar(int id) {
        super.setupToolbar(id);
        getSupportActionBar().setTitle(R.string.title_activity_language_selection);
    }

    private void initListeners() {
        mFragmentLanguage.setOnLanguageChangedListener(this);
    }

    private void initVariables() {
        mFragmentLanguage = FragmentLanguage.newInstance();
    }

    private void addLanguageFragment() {
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.add(R.id.language_fragment_container, mFragmentLanguage);
        // Complete the changes added above
        ft.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }


    private void reload() {
        //ShowCasingApplication.getInstance().updateContextWrapper();
        Intent intent = new Intent(LanguageSelectionActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onLanguageChanged() {
        reload();
    }
}
