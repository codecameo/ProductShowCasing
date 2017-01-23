package bytes.wit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import bytes.wit.adapters.ProductSearchAdapter;
import bytes.wit.interfaces.SearchFragmentCommunicator;
import bytes.wit.showcasing.HomeActivity;
import bytes.wit.showcasing.R;
import bytes.wit.utils.Utils;

/**
 * Created by Md. Sifat-Ul Haque on 1/16/2017.
 */

public class FragmentSearch extends Fragment implements View.OnClickListener, SearchFragmentCommunicator, TextWatcher {

    private Communicator mCommunicator;
    private ImageView mIvSearchBack, mIvClearField;
    private EditText mEtSearchField;
    private RecyclerView mRvSearchList;
    private ProductSearchAdapter mProductSearchAdapter;

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
        initVariables();
        initListeners();
        initDefaultView();
        setupAdapter();
    }

    private void initVariables() {
        mProductSearchAdapter = new ProductSearchAdapter();
    }

    private void setupAdapter() {
        mRvSearchList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvSearchList.setAdapter(mProductSearchAdapter);
    }

    private void initDefaultView() {
        Utils.showKeyboard(getActivity(), mEtSearchField);
        mEtSearchField.setFocusableInTouchMode(true);
        mEtSearchField.requestFocus();
    }

    private void initListeners() {
        mIvSearchBack.setOnClickListener(this);
        mEtSearchField.addTextChangedListener(this);
        mIvClearField.setOnClickListener(this);
        ((HomeActivity) getActivity()).setSearchFragmentCommunicator(this);
    }

    private void initViews(View view) {
        mIvSearchBack = (ImageView) view.findViewById(R.id.iv_search_back);
        mEtSearchField = (EditText) view.findViewById(R.id.et_search_text);
        mRvSearchList = (RecyclerView) view.findViewById(R.id.rv_search_list);
        mIvClearField = (ImageView) view.findViewById(R.id.iv_clear_field);
    }

    public void setCommunicator(Communicator mCommunicator) {
        this.mCommunicator = mCommunicator;
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();

        if (id == R.id.iv_search_back) {
            Utils.hideKeyboard(getActivity());
            mCommunicator.onSearchBackPressed();
        } else if (id == R.id.iv_clear_field) {
            mEtSearchField.setText("");
        }
    }

    @Override
    public void onSearchBarOpen() {
        initDefaultView();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length() == 0) {
            mIvClearField.setVisibility(View.INVISIBLE);
        } else {
            mIvClearField.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public interface Communicator {
        void onSearchBackPressed();
    }
}
