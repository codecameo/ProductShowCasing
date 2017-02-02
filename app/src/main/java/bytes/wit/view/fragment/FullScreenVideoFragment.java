package bytes.wit.view.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import bytes.wit.showcasing.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FullScreenVideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class FullScreenVideoFragment extends Fragment {

    private static final String VIDEO_URL_PARAM = "video_url";

    private String mVideoUrl;

    public VideoView videoContent;
    private MediaController mMediaController;

    public FullScreenVideoFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this FullScreenVideoFragment using the video url.
     *
     * @param videoUrl Video url.
     * @return A new instance of fragment FullScreenVideoFragment.
     */
    public static FullScreenVideoFragment newInstance(String videoUrl) {
        FullScreenVideoFragment fragment = new FullScreenVideoFragment();
        Bundle args = new Bundle();
        args.putString(VIDEO_URL_PARAM, videoUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mVideoUrl = getArguments().getString(VIDEO_URL_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sull_screen_video, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        videoContent = (VideoView) view.findViewById(R.id.video_content);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeVideo();
    }

    private void initializeVideo() {
        mMediaController = new MediaController(getActivity());
        mMediaController.setAnchorView(videoContent);
        videoContent.setVideoURI(Uri.parse(mVideoUrl));
        videoContent.setMediaController(mMediaController);
        videoContent.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMediaController != null) mMediaController = null;
    }
}
