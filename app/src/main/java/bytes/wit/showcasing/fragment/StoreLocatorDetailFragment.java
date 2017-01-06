package bytes.wit.showcasing.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bytes.wit.showcasing.R;

/**
 * Created by Sharifur Rahaman on 1/5/2017.
 * Email: sharif.iit.du@gmail.com
 */

public class StoreLocatorDetailFragment extends android.support.v4.app.Fragment {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StoreLocatorDetailFragment() {
    }

    public static StoreLocatorDetailFragment newInstance() {
        StoreLocatorDetailFragment fragment = new StoreLocatorDetailFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_store_locator_detail, container, false);
        return rootView;
    }


}
