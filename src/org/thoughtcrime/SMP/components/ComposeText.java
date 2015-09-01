package org.thoughtcrime.SMP.components;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;

import org.thoughtcrime.SMP.components.emoji.EmojiEditText;

public class ComposeText extends EmojiEditText {
  public ComposeText(Context context) {
    super(context);
  }

  public ComposeText(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ComposeText(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public void setHint(@NonNull String hint) {
    SpannableString span = new SpannableString(hint);
    span.setSpan(new RelativeSizeSpan(0.8f), 0, hint.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
    super.setHint(span);
  }

  public void appendInvite(String invite) {
    if (!TextUtils.isEmpty(getText()) && !getText().toString().equals(" ")) {
      append(" ");
    }

    append(invite);
  }
}
