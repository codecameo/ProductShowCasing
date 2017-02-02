package bytes.wit.view.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;

import bytes.wit.showcasing.R;
import bytes.wit.view.customview.TouchImageView;

/**
 * Created by Sharifur Rahaman on 1/30/2017.
 * Email: sharif.iit.du@gmail.com
 */


public class FullScreenImageFragment extends Fragment {

    public static final String TAG = "FullScreenImageFragment";
    private TouchImageView mFullImagePreview;

    private static final String IMAGE_URL_PARAM = "image_url";

    private String mImageUrl;

    private ProgressBar mPbPhotoPreview;

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
        mFullImagePreview = (TouchImageView) view.findViewById(R.id.iv_full_image_preview);
        mPbPhotoPreview = (ProgressBar) view.findViewById(R.id.pb_photo_preview);
        loadImage(mImageUrl);
    }

    private void loadImage(String path) {
        if (path.contains("http")) {
            Picasso.with(getActivity())
                    .load(path)
                    .into(mMyTarget);
        } else {
            File imageFile = new File(path);
            Picasso.with(getActivity())
                    .load(imageFile)
                    .into(mMyTarget);
        }
    }

    /**
     * Target class for Picasso. This specially used to show loader when loading an image.
     * We show the loader when image is coming from server or not fully loaded.
     * We hide the loader when image is available
     * TODO When image loading is failed, we should give option to reload the image.
     */
    private Target mMyTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            mPbPhotoPreview.setVisibility(View.GONE);
            mFullImagePreview.setVisibility(View.VISIBLE);
            mFullImagePreview.setImageBitmap(bitmap);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            mFullImagePreview.setVisibility(View.GONE);
            mPbPhotoPreview.setVisibility(View.GONE);
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            mFullImagePreview.setVisibility(View.GONE);
            mPbPhotoPreview.setVisibility(View.VISIBLE);
        }
    };

}