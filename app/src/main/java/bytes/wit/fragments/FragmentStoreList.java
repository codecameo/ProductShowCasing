package bytes.wit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bytes.wit.showcasing.R;

/**
 * Created by Md. Sifat-Ul Haque on 1/4/2017.
 */

public class FragmentStoreList extends Fragment {

    private RecyclerView mRvStoreList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_list, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mRvStoreList = (RecyclerView) view.findViewById(R.id.rv_store_list);
    }
}
