package com.ljaymori.swipelayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TouchRecyclerView extends RecyclerView {
    public TouchRecyclerView(Context context) {
        super(context);
    }

    public TouchRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private boolean isScrollable = true;
    public void setScrollable(Boolean scrollable) {
        isScrollable = scrollable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if(isScrollable){
            return super.onTouchEvent(e);
        } else {
            if(e.getAction() == MotionEvent.ACTION_HOVER_MOVE) {
                return false;
            } else {
                return super.onTouchEvent(e);
            }
        }


    }
}
