package bytes.wit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bytes.wit.showcasing.R;

/**
 * A simple {@link Fragment} subclass to hold language change related information.
 * Use the {@link FragmentLanguage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLanguage extends Fragment implements View.OnClickListener {

    /** TextView bangla and english to hold the language reference. Selecting each change the language for the app*/
    private TextView mTvBangla, mTvEnglish;

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

    /**
     * Initialize all listeners here.
     */
    private void initListeners() {
        mTvBangla.setOnClickListener(this);
        mTvEnglish.setOnClickListener(this);
    }

    /**
     * Initialize all views here.
     * @param view The view to find other views.
     */
    private void initViews(View view) {
        mTvBangla = (TextView) view.findViewById(R.id.tv_bangla);
        mTvEnglish = (TextView) view.findViewById(R.id.tv_english);
    }

    @Override
    public void onClick(View view) {
        view.setSelected(!view.isSelected());
    }
}
