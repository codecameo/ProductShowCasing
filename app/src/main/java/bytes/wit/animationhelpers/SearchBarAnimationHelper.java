package bytes.wit.animationhelpers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import static android.view.ViewAnimationUtils.createCircularReveal;

/**
 * Created by Md. Sifat-Ul Haque on 1/16/2017.
 */

public class SearchBarAnimationHelper {

    private View mTransparentMask;
    private ObjectAnimator mFadeWheelBackground;
    private ObjectAnimator mTransparentWheelBackground;

    public static final int CIRCULAR_EXPAND_ANIM_DURATION = 200;
    private int mCurrentVisibility = View.GONE;
    private Animator mCircleExpandAnim;
    private Animator mCircleCollapseAnim;

    public SearchBarAnimationHelper(View view) {
        setTransparentMask(view);
    }

    private void initCollapsedAnimator(int cx, int cy, float startRadius, float endRadius) {
        mCircleCollapseAnim = createCircularReveal(mTransparentMask, cx, cy, startRadius, endRadius);
        mCircleCollapseAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        mCircleCollapseAnim.setDuration(CIRCULAR_EXPAND_ANIM_DURATION);

        mCircleCollapseAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mTransparentMask.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void initExpandAnimator(int cx, int cy, float startRadius, float endRadius) {
        mCircleExpandAnim = createCircularReveal(mTransparentMask, cx, cy, startRadius, endRadius);
        mCircleExpandAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        mCircleExpandAnim.setDuration(CIRCULAR_EXPAND_ANIM_DURATION);

        mCircleExpandAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
    }


    public void setTransparentMask(View transparentMask) {
        this.mTransparentMask = transparentMask;
        mTransparentMask.setVisibility(mCurrentVisibility);

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // new g-nav starts here
            mFadeWheelBackground = ObjectAnimator.ofObject(mTransparentMask, "backgroundColor", new ArgbEvaluator(), 0x00FFFFFF, 0xE6D5D5D5);
            mFadeWheelBackground.setDuration(80);

            mTransparentWheelBackground = ObjectAnimator.ofObject(mTransparentMask, "backgroundColor", new ArgbEvaluator(), 0xE6D5D5D5, 0x00FFFFFF);
            mTransparentWheelBackground.setDuration(200);
        }
    }

    /**
     * Start the animation for Gnav RecyclerView.
     */
    public void startAnimation(int cx, int cy, float rootRadius, float buttonRadius) {

        if (isVisible()) {
            hideWheelBackground(cx, cy, rootRadius, buttonRadius);
        } else {
            showWheelBackground(cx, cy, buttonRadius, rootRadius);
        }
    }


    /**
     * @param cx          center X of the circular animation
     * @param cy          Center Y of the circular animation
     * @param startRadius initial radius of the circular animation
     * @param endRadius   target radius of the circular animation
     */
    private void showWheelBackground(int cx, int cy, float startRadius, float endRadius) {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            mTransparentMask.setVisibility(View.VISIBLE);
            initExpandAnimator(cx, cy, startRadius, endRadius);

            if (mCircleCollapseAnim != null && mCircleCollapseAnim.isRunning()) {
                mCircleCollapseAnim.cancel();
            }

            mCircleExpandAnim.start();
        } else {
            mTransparentMask.setVisibility(View.VISIBLE);
            mFadeWheelBackground.start();
            mTransparentWheelBackground.cancel();
        }
    }


    /**
     * @param cx          center X of the circular animation
     * @param cy          Center Y of the circular animation
     * @param startRadius initial radius of the circular animation
     * @param endRadius   target radius of the circular animation
     */
    public void hideWheelBackground(int cx, int cy, float startRadius, float endRadius) {


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            initCollapsedAnimator(cx, cy, startRadius, endRadius);

            if (mCircleExpandAnim != null && mCircleExpandAnim.isRunning()) {
                mCircleExpandAnim.cancel();
            }

            mCircleCollapseAnim.start();
        } else {
            mFadeWheelBackground.cancel();
            mTransparentWheelBackground.start();
        }
    }


    public boolean isVisible() {
        return mTransparentMask.getVisibility() == View.VISIBLE;
    }
}
