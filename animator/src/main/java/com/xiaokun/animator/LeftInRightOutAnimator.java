package com.xiaokun.animator;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.RecyclerView;

/**
 * Created by panwankun on 16/7/5.
 */
public class LeftInRightOutAnimator extends BaseItemAnimator{

    @Override
    protected ViewPropertyAnimatorCompat getAddAnimator(RecyclerView.ViewHolder item) {
        ViewCompat.setTranslationX(item.itemView, -item.itemView.getWidth());
        return ViewCompat.animate(item.itemView).translationX(0);
    }

    @Override
    protected ViewPropertyAnimatorCompat getRemoveAnimator(RecyclerView.ViewHolder item) {
        return ViewCompat.animate(item.itemView).translationX(item.itemView.getWidth());
    }
}
