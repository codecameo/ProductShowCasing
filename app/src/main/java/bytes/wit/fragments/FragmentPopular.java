package bytes.wit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bytes.wit.showcasing.R;

/**
 * Created by Md. Sifat-Ul Haque on 12/29/2016.
 */

public class FragmentPopular extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        //initViews(view);
        return view;
    }
}
