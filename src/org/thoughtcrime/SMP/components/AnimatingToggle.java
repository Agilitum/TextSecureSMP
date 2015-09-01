package org.thoughtcrime.SMP.components;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

public class AnimatingToggle extends FrameLayout {

  private static final int SPEED_MILLIS = 200;

  private View current;

  public AnimatingToggle(Context context) {
    super(context);
  }

  public AnimatingToggle(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public AnimatingToggle(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public void addView(@NonNull View child, int index, ViewGroup.LayoutParams params) {
    super.addView(child, index, params);

    if (getChildCount() == 1) {
      current = child;
      child.setVisibility(View.VISIBLE);
    } else {
      child.setVisibility(View.GONE);
    }
  }

  public void display(View view) {
    if (view == current) return;

    int oldViewIndex = getViewIndex(current);
    int newViewIndex = getViewIndex(view);

    int sign;

    if (oldViewIndex < newViewIndex) sign = -1;
    else                             sign = 1;

    TranslateAnimation oldViewAnimation = createTranslation(0.0f, sign * 1.0f);
    TranslateAnimation newViewAnimation = createTranslation(sign * -1.0f, 0.0f);

    animateOut(current, oldViewAnimation);
    animateIn(view, newViewAnimation);

    current = view;
  }

  private void animateOut(final View view, TranslateAnimation animation) {
    animation.setAnimationListener(new Animation.AnimationListener() {
      @Override
      public void onAnimationStart(Animation animation) {
      }

      @Override
      public void onAnimationEnd(Animation animation) {
        view.setVisibility(View.GONE);
      }

      @Override
      public void onAnimationRepeat(Animation animation) {
      }
    });

    view.startAnimation(animation);
  }

  private void animateIn(View view, TranslateAnimation animation) {
    animation.setInterpolator(new FastOutSlowInInterpolator());
    view.setVisibility(View.VISIBLE);
    view.startAnimation(animation);
  }

  private int getViewIndex(View view) {
    for (int i=0;i<getChildCount();i++) {
      if (getChildAt(i) == view) return i;
    }

    throw new IllegalArgumentException("Not a parent of this view.");
  }

  private TranslateAnimation createTranslation(float startY, float endY) {
    TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                                                                   Animation.RELATIVE_TO_SELF, 0.0f,
                                                                   Animation.RELATIVE_TO_SELF, startY,
                                                                   Animation.RELATIVE_TO_SELF, endY);

    translateAnimation.setDuration(SPEED_MILLIS);
    translateAnimation.setInterpolator(new FastOutSlowInInterpolator());

    return translateAnimation;
  }

}
