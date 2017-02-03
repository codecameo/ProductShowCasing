package bytes.wit.custom;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by Md. Sifat-Ul Haque on 2/2/2017.
 */

public class FabScrollBehaviour extends FloatingActionButton.Behavior {

    private boolean mShowingFloatButton = true;

    public FabScrollBehaviour(Context context, AttributeSet attributeSet) {
        super();
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

        Log.d("FabScrollBehaviour", "conX " + dxConsumed + " conY " + dyConsumed + " unconX " + dxUnconsumed + " unconY " + dyUnconsumed);

        if (dyConsumed > 0)
            child.animate().translationY(child.getHeight()*2).setInterpolator(new AccelerateInterpolator(2)).start();
        else
            child.animate().translationY(/*-Measure.getNavigationBarSize(coordinatorLayout
            .getContext()).y*/0).setInterpolator(new DecelerateInterpolator(2)).start();

        /*if (dyConsumed <= 0 && !mShowingFloatButton) {
            mShowingFloatButton = true;
            child.animate().scaleX(1).setInterpolator(new DecelerateInterpolator(2)).start();
            child.animate().scaleY(1).setInterpolator(new DecelerateInterpolator(2)).start();
        }*/
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    /*@Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        Log.d("FabScrollBehaviour","onStopNestedScroll");
        child.animate().scaleX(1).setInterpolator(new DecelerateInterpolator(2)).start();
        child.animate().scaleY(1).setInterpolator(new DecelerateInterpolator(2)).start();
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        Log.d("FabScrollBehaviour","onNestedPreScroll");
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, float velocityX, float velocityY, boolean consumed) {
        Log.d("FabScrollBehaviour","onNestedFling");
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, float velocityX, float velocityY) {
        Log.d("FabScrollBehaviour","onNestedPreFling");
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }*/

    /*@Override
    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
        Log.d("FabScrollBehaviour", "onNestedScrollAccepted");
        if (mShowingFloatButton) {
            mShowingFloatButton = false;
            child.animate().scaleX(0).setInterpolator(new AccelerateInterpolator(2)).start();
            child.animate().scaleY(0).setInterpolator(new AccelerateInterpolator(2)).start();
        }
    }*/
}
