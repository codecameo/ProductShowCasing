package bytes.wit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import bytes.wit.managers.LocalityManger;
import bytes.wit.showcasing.R;

/**
 * A simple {@link Fragment} subclass to hold language change related information.
 * Use the {@link FragmentLanguage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLanguage extends Fragment implements View.OnClickListener {

    /** TextView bangla and english to hold the language reference. Selecting each change the language for the app*/
    private TextView mTvBangla, mTvEnglish;
    private LocalityManger mLocalityManger;
    private String mLocality, mSelectedLocality, mCountry, mSelectedCountry;
    private Button mBtnSave;
    private onLanguageChangedListener mOnLanguageChangedListener;

    public FragmentLanguage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment FragmentLanguage.
     */
    public static FragmentLanguage newInstance() {
        FragmentLanguage fragment = new FragmentLanguage();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_language, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initListeners();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initVariables();
        setDefaultValue();
    }

    private void setDefaultValue() {
        if (TextUtils.equals(mLocality, mLocalityManger.localities[0])) {
            mTvEnglish.setSelected(true);
        } else {
            mTvBangla.setSelected(true);
        }
    }

    private void initVariables() {
        mLocalityManger = LocalityManger.getInstance(getActivity());
        mLocality = mLocalityManger.getLocality();
        mCountry = mLocalityManger.getCountry();
    }

    /**
     * Initialize all listeners here.
     */
    private void initListeners() {
        mTvBangla.setOnClickListener(this);
        mTvEnglish.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
    }

    /**
     * Initialize all views here.
     * @param view The view to find other views.
     */
    private void initViews(View view) {
        mTvBangla = (TextView) view.findViewById(R.id.tv_bangla);
        mTvEnglish = (TextView) view.findViewById(R.id.tv_english);
        mBtnSave = (Button) view.findViewById(R.id.btn_save);
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();

        if (id == R.id.tv_bangla) {
            mSelectedLocality = mLocalityManger.localities[1];
            mSelectedCountry = mLocalityManger.countries[1];
            updateSelectionView(true);
        } else if (id == R.id.tv_english) {
            mSelectedLocality = mLocalityManger.localities[0];
            mSelectedCountry = mLocalityManger.countries[0];
            updateSelectionView(false);
        } else if (id == R.id.btn_save) {
            if (!TextUtils.equals(mLocality, mSelectedLocality)) {
                mLocalityManger.updateLocality(mSelectedLocality, mSelectedCountry, true);
                mOnLanguageChangedListener.onLanguageChanged();
            }
        }
    }

    private void updateSelectionView(boolean showBangla) {
        mTvBangla.setSelected(showBangla);
        mTvEnglish.setSelected(!showBangla);
    }

    public void setOnLanguageChangedListener(onLanguageChangedListener mOnLanguageChangedListener) {
        this.mOnLanguageChangedListener = mOnLanguageChangedListener;
    }

    public interface onLanguageChangedListener {
        void onLanguageChanged();
    }
}
