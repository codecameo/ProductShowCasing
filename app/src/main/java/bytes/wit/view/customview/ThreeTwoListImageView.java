package bytes.wit.view.customview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Md. Sifat-Ul Haque on 1/14/2017.
 */

public class ThreeTwoListImageView extends ImageView {
    public ThreeTwoListImageView(Context context) {
        super(context);
    }

    public ThreeTwoListImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ThreeTwoListImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ThreeTwoListImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int threeTwoHeight = MeasureSpec.getSize(widthMeasureSpec) / 2;

        int threeTwoHeightSpecs = MeasureSpec.makeMeasureSpec(threeTwoHeight, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, threeTwoHeightSpecs);
    }
}

