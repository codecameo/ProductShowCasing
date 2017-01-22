package bytes.wit.application;

import android.app.Application;
import android.content.ContextWrapper;

import com.squareup.leakcanary.LeakCanary;

import bytes.wit.managers.LocalityManger;
import bytes.wit.wrappers.LanguageContextWrapper;


/**
 * Created by Md. Sifat-Ul Haque on 12/28/2016.
 */

public class ShowCasingApplication extends Application {

    private LocalityManger mLocalityManger;
    private ContextWrapper mLanguageContextWrapper;
    private static ShowCasingApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
        application = this;
        mLocalityManger = LocalityManger.getInstance(getBaseContext());
        updateContextWrapper();
    }

    public static ShowCasingApplication getInstance() {
        return application;
    }

    public ContextWrapper getLanguageContextWrapper() {
        return mLanguageContextWrapper;
    }

    public void updateContextWrapper() {
        mLanguageContextWrapper = LanguageContextWrapper.wrap(getBaseContext(), mLocalityManger.getLocality());
    }
}
