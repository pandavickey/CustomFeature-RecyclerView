package com.xiaokun.divider;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by panwankun on 16/7/10.
 */
public class SelectedGridItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int dividerWidth = 5;
    private Paint paint, selectedPaint;

    public SelectedGridItemDecoration(int spanCount) {
        super();
        this.spanCount = spanCount;
        initPaint();
        paint.setColor(Color.rgb(0, 200, 0));
        selectedPaint.setColor(Color.rgb(200, 0, 0));
    }

    public SelectedGridItemDecoration(int spanCount, int dividerWidth) {
        super();
        this.spanCount = spanCount;
        this.dividerWidth = dividerWidth;
        initPaint();
        paint.setColor(Color.rgb(0, 200, 0));
        selectedPaint.setColor(Color.rgb(200, 0, 0));
    }

    public SelectedGridItemDecoration(int spanCount, int dividerWidth, int dividerColor, int selectDividerColor) {
        super();
        this.spanCount = spanCount;
        this.dividerWidth = dividerWidth;
        initPaint();
        paint.setColor(dividerColor);
        selectedPaint.setColor(selectDividerColor);
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(dividerWidth );
        paint.setStyle(Paint.Style.STROKE);

        selectedPaint = new Paint();
        selectedPaint.setAntiAlias(true);
        selectedPaint.setStrokeWidth(dividerWidth);
        selectedPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        for (int i = 0; i < parent.getChildCount(); i++) {
            c.drawRect(getChildRect(parent.getChildAt(i)), paint);
        }
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            Object selected = child.getTag(R.id.recycler_itemview_selected);
            if (selected != null && selected instanceof Boolean && (Boolean) selected) {
                c.drawRect(getChildRect(parent.getChildAt(i)), selectedPaint);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        Object object = view.getTag(R.id.recycler_itemview_position);
        if (object != null && object instanceof Integer) {
            int indexOfParent = (int) object;
            int childCount = parent.getAdapter().getItemCount();
            int left = indexOfLeftView(indexOfParent, spanCount) >= 0 ? dividerWidth : dividerWidth * 2;
            int top = indexOfTopView(indexOfParent, spanCount) >= 0 ? dividerWidth : dividerWidth * 2;
            int right = indexOfRightView(indexOfParent, childCount, spanCount) >= 0 ? dividerWidth : dividerWidth * 2;
            int bottom = indexOfBottomView(indexOfParent, childCount, spanCount) >= 0 ? dividerWidth : dividerWidth * 2;
            outRect.set(left, top, right, bottom);
        }
    }

    private Rect getChildRect(View child) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
        int childTop = child.getTop() + Math.round(ViewCompat.getTranslationY(child));
        int childLeft = child.getLeft() + Math.round(ViewCompat.getTranslationX(child));
        int childRight = childLeft + child.getWidth() + params.rightMargin;
        int childBottom = childTop + child.getHeight() + params.bottomMargin;
        return new Rect(childLeft - dividerWidth, childTop - dividerWidth , childRight + dividerWidth, childBottom + dividerWidth );
    }

    //没有返回-1
    private int indexOfLeftView(int indexOfParent, int spanCount) {
        if (indexOfParent % spanCount != 0) {
            return indexOfParent - 1;
        }
        return -1;
    }

    private int indexOfRightView(int indexOfParent, int childCount, int spanCount) {
        if ((indexOfParent + 1) % spanCount != 0 && indexOfParent != childCount - 1) {
            return indexOfParent + 1;
        }
        return -1;
    }

    private int indexOfTopView(int indexOfParent, int spanCount) {
        if (indexOfParent >= spanCount) {
            return indexOfParent - spanCount;
        }
        return -1;
    }

    private int indexOfBottomView(int indexOfParent, int childCount, int spanCount) {
        if (indexOfParent < childCount - spanCount) {
            return indexOfParent + spanCount;
        }
        return -1;
    }
}

