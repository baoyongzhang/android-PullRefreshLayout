package com.baoyz.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

/**
 * Created by baoyz on 14/10/29.
 */
public abstract class RefreshDrawable extends Drawable implements Drawable.Callback, Animatable {

    private PullRefreshLayout mRefreshLayout;
    private Rect mOriginBounds;
    private int mDirection;

    public RefreshDrawable(Context context, PullRefreshLayout layout) {
        mRefreshLayout = layout;
    }

    public Context getContext(){
        return mRefreshLayout != null ? mRefreshLayout.getContext() : null;
    }

    public PullRefreshLayout getRefreshLayout(){
        return mRefreshLayout;
    }

    public abstract void setPercent(float percent);
    public abstract void setColorSchemeColors(int[] colorSchemeColors);
    public abstract void offsetTopAndBottom(int offset);
    public void setDirection(int direction) {
        mDirection = direction;
    }

    @Override
    public final void draw(Canvas canvas) {
        int save = canvas.save();
        if (mDirection == PullRefreshLayout.DIRECTION_DOWN) {
            canvas.rotate(180, mOriginBounds.centerX(), mOriginBounds.centerY());
        }
        onDraw(canvas);
        canvas.restoreToCount(save);
    }

    public abstract void onDraw(Canvas canvas);

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        mOriginBounds = bounds;
    }

    @Override
    public void invalidateDrawable(Drawable who) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override
    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, what, when);
        }
    }

    @Override
    public void unscheduleDrawable(Drawable who, Runnable what) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, what);
        }
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }
}
