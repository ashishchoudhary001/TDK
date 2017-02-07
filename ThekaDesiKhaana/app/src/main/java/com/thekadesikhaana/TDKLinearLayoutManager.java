package com.thekadesikhaana;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * Created by ashishchoudhary on 06/02/17.
 */

public class TDKLinearLayoutManager extends LinearLayoutManager {

    @Override
    public boolean supportsPredictiveItemAnimations() {
        return false;
    }

    TDKLinearLayoutManager(Context context) {
        super(context);
    }

    TDKLinearLayoutManager(Context context, int orientation, boolean reverseLayout)
    {
        super(context,orientation,reverseLayout);
    }

    TDKLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context,attrs,defStyleAttr,defStyleRes);
    }
}
