package bytes.wit.view.fragment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import bytes.wit.showcasing.R;

import static com.google.android.gms.wearable.DataMap.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FullScreenVideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class FullScreenVideoFragment extends Fragment implements MediaPlayer.OnPreparedListener {

    private static final String VIDEO_URL_PARAM = "video_url";

    private String mVideoUrl;

    public VideoView mVideoView;
    private MediaController mMediaController;
    private ProgressBar mPbVideo;

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
        return inflater.inflate(R.layout.fragment_sull_screen_video, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view) {
        mVideoView = (VideoView) view.findViewById(R.id.video_content);
        mPbVideo = (ProgressBar) view.findViewById(R.id.pb_video);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser){
            startVideo();
        }else{
            stopVideo();
        }
    }

    private void stopVideo() {
        if (mVideoView != null){
            mVideoView.stopPlayback();
        }
        if (mMediaController != null){
            mMediaController = null;
        }
    }

    private void startVideo() {
        mMediaController = new MediaController(getActivity());
        mMediaController.setAnchorView(mVideoView);
        mPbVideo.setVisibility(View.VISIBLE);
        mVideoView.setVideoURI(Uri.parse(mVideoUrl));
        mVideoView.setMediaController(mMediaController);
        mVideoView.start();
        mVideoView.setOnPreparedListener(this);
    }

    private void initializeVideo() {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.d(TAG, "video prepared");
        mPbVideo.setVisibility(View.GONE);
    }
}
