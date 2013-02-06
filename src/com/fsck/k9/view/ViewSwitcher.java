package com.fsck.k9.view;

import com.fsck.k9.K9;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ViewAnimator;

/**
 * A {@link ViewAnimator} that animates between two child views using different animations
 * depending on which view is displayed.
 */
public class ViewSwitcher extends ViewAnimator implements AnimationListener {
    private Animation mFirstInAnimation;
    private Animation mFirstOutAnimation;
    private Animation mSecondInAnimation;
    private Animation mSecondOutAnimation;
    private OnAnimationEndListener mListener;


    public ViewSwitcher(Context context) {
        super(context);
    }

    public ViewSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void showFirstView() {
        if (getDisplayedChild() == 0) {
            return;
        }

        setupAnimations(mFirstInAnimation, mFirstOutAnimation);
        setDisplayedChild(0);
    }

    public void showSecondView() {
        if (getDisplayedChild() == 1) {
            return;
        }

        setupAnimations(mSecondInAnimation, mSecondOutAnimation);
        setDisplayedChild(1);
    }

    private void setupAnimations(Animation in, Animation out) {
        if (K9.showAnimations()) {
            setInAnimation(in);
            setOutAnimation(out);
        } else {
            setInAnimation(null);
            setOutAnimation(null);
        }
    }

    public Animation getFirstInAnimation() {
        return mFirstInAnimation;
    }

    public void setFirstInAnimation(Animation inAnimation) {
        this.mFirstInAnimation = inAnimation;
    }

    public Animation getmFirstOutAnimation() {
        return mFirstOutAnimation;
    }

    public void setFirstOutAnimation(Animation outAnimation) {
        mFirstOutAnimation = outAnimation;
    }

    public Animation getSecondInAnimation() {
        return mSecondInAnimation;
    }

    public void setSecondInAnimation(Animation inAnimation) {
        mSecondInAnimation = inAnimation;
    }

    public Animation getSecondOutAnimation() {
        return mSecondOutAnimation;
    }

    public void setSecondOutAnimation(Animation outAnimation) {
        mSecondOutAnimation = outAnimation;
    }

    public void setOnAnimationEndListener(OnAnimationEndListener listener) {
        mListener = listener;
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (mListener != null) {
            mListener.onAnimationEnd(getDisplayedChild());
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // unused
    }

    @Override
    public void onAnimationStart(Animation animation) {
        // unused
    }

    public interface OnAnimationEndListener {
        /**
         * This method will be called after the switch animation has ended.
         *
         * @param displayedChild
         *         Contains the zero-based index of the child view that is now displayed.
         */
        void onAnimationEnd(int displayedChild);
    }
}