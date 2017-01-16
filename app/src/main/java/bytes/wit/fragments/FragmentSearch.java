package bytes.wit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import bytes.wit.showcasing.R;
import bytes.wit.utils.Utils;

/**
 * Created by Md. Sifat-Ul Haque on 1/16/2017.
 */

public class FragmentSearch extends Fragment implements View.OnClickListener {

    private Communicator mCommunicator;
    private ImageView mIvSearchBack;
    private EditText mEtSearchField;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListeners();
        //initDefaultView();
    }

    @Override
    public void onResume() {
        super.onResume();
        initDefaultView();
    }

    private void initDefaultView() {
        Utils.showKeyboard(getActivity(), mEtSearchField);
        mEtSearchField.setFocusableInTouchMode(true);
        mEtSearchField.requestFocus();
    }

    private void initListeners() {
        mIvSearchBack.setOnClickListener(this);
    }

    private void initViews(View view) {
        mIvSearchBack = (ImageView) view.findViewById(R.id.iv_search_back);
        mEtSearchField = (EditText) view.findViewById(R.id.et_search_text);
    }

    public void setCommunicator(Communicator mCommunicator) {
        this.mCommunicator = mCommunicator;
    }

    @Override
    public void onClick(View view) {
        Utils.hideKeyboard(getActivity());
        mCommunicator.onSearchBackPressed();
    }

    public interface Communicator {
        void onSearchBackPressed();
    }
}
