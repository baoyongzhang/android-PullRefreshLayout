package com.baoyz.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;

/**
 * Created by baoyz on 14/10/31.
 */
public class WaterDropDrawable extends RefreshDrawable implements Runnable{

    private static final float MAX_LEVEL = 10000;
    private static final float CIRCLE_COUNT = ProgressStates.values().length;
    private static final float MAX_LEVEL_PER_CIRCLE = MAX_LEVEL / CIRCLE_COUNT;

    private int mLevel;
    private Point p1, p2, p3, p4;
    private Paint mPaint;
    private Path mPath;
    private int mHeight;
    private int mWidth;
    private Context mContext;
    private int[] mColorSchemeColors;
    private ProgressStates mCurrentState;
    private Handler mHandler = new Handler();
    private boolean isRunning;

    private enum ProgressStates {
        ONE,
        TWO,
        TREE,
        FOUR
    }

    public WaterDropDrawable(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPath = new Path();
        p1 = new Point();
        p2 = new Point();
        p3 = new Point();
        p4 = new Point();
        mContext = context;
    }

    @Override
    public void draw(Canvas canvas) {
        mPath.reset();;
        mPath.moveTo(p1.x, p1.y);
        mPath.cubicTo(p3.x, p3.y, p4.x, p4.y, p2.x, p2.y);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        mWidth = bounds.width();
        updateBounds();
        super.onBoundsChange(bounds);
    }

    private void updateBounds() {
        int height = mHeight;
        int width = mWidth;
        int offsetX = (int) (height * 2.6f);
        int offsetY = 0;

        if (offsetX > (width / 2)){
            // 平移
            offsetY = (int) ((offsetX - width / 2) / 2.6f);
            offsetX = width / 2;
        }

        p1.set(offsetX, offsetY);
        p2.set(width - offsetX, offsetY);
        p3.set(width / 2 - offsetX / 2, height);
        p4.set(width / 2 + offsetX / 2, height);
    }

    @Override
    public void setColorSchemeColors(int[] colorSchemeColors) {
        mPaint.setColor(colorSchemeColors[0]);
        mColorSchemeColors = colorSchemeColors;
    }

    @Override
    public void setPercent(float percent) {
        mPaint.setColor(evaluate(percent, mColorSchemeColors[0], mColorSchemeColors[1]));
    }

    private void updateLevel(int level){
        int animationLevel = level == MAX_LEVEL ? 0 : level;

        int stateForLevel = (int) (animationLevel / MAX_LEVEL_PER_CIRCLE);
        mCurrentState = ProgressStates.values()[stateForLevel];

        float precent = level % 2500 / 2500f;
        int startColor = mColorSchemeColors[stateForLevel];
        int endColor = mColorSchemeColors[(stateForLevel + 1) % ProgressStates.values().length];
        mPaint.setColor(evaluate(precent, startColor, endColor));
    }

    @Override
    public void offsetTopAndBottom(int offset) {
        mHeight += offset;
        updateBounds();
        invalidateSelf();
    }

    @Override
    public void start() {
        isRunning = true;
        mHandler.postDelayed(this, 20);
    }

    @Override
    public void stop() {
        mHandler.removeCallbacks(this);
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void run() {
        mLevel += 60;
        if (mLevel > MAX_LEVEL)
            mLevel = 0;
        if (isRunning) {
            mHandler.postDelayed(this, 20);
            updateLevel(mLevel);
            invalidateSelf();
        }
    }

    public int evaluate(float fraction, int startValue, int endValue) {
        int startInt = startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return ((startA + (int)(fraction * (endA - startA))) << 24) |
               ((startR + (int)(fraction * (endR - startR))) << 16) |
               ((startG + (int)(fraction * (endG - startG))) << 8) |
               ((startB + (int)(fraction * (endB - startB))));
    }

}