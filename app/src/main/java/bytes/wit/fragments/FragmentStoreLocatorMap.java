package bytes.wit.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bytes.wit.showcasing.R;

/**
 * Created by Sharifur Rahaman on 1/5/2017.
 * Email: sharif.iit.du@gmail.com
 */

public class FragmentStoreLocatorMap extends android.support.v4.app.Fragment {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FragmentStoreLocatorMap() {
    }

    public static FragmentStoreLocatorMap newInstance() {
        FragmentStoreLocatorMap fragment = new FragmentStoreLocatorMap();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_store_locator_detail, container, false);
        return rootView;
    }


}
