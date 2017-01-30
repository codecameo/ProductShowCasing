package bytes.wit.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bytes.wit.showcasing.R;
import bytes.wit.view.customview.TouchImageView;

/**
 * Created by Sharifur Rahaman on 1/30/2017.
 * Email: sharif.iit.du@gmail.com
 */


public class FullScreenImageFragment extends Fragment implements TouchImageView.OnTouchImageViewListener {

    public static final String TAG = "FullScreenImageFragment";
    private TouchImageView mTouchImageView;

    private static final String IMAGE_URL_PARAM = "image_url";

    private String mImageUrl;

    public FullScreenImageFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this FullScreenImageFragment using the video url.
     *
     * @param imageUrl Image url.
     * @return A new instance of fragment FullScreenImageFragment.
     */
    public static FullScreenImageFragment newInstance(String imageUrl) {
        FullScreenImageFragment fragment = new FullScreenImageFragment();
        Bundle args = new Bundle();
        args.putString(IMAGE_URL_PARAM, imageUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImageUrl = getArguments().getString(IMAGE_URL_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_full_screen_image, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTouchImageView = (TouchImageView) view.findViewById(R.id.iv_full);
        mTouchImageView.setOnTouchImageViewListener(this);
    }

    @Override
    public void onMove() {
        Log.d(TAG, "width " + mTouchImageView.getZoomedRect().width());
    }

}