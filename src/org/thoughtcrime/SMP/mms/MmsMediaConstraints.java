package org.thoughtcrime.SMP.mms;

import android.content.Context;

import org.thoughtcrime.SMP.util.Util;

public class MmsMediaConstraints extends MediaConstraints {
  private static final int MAX_IMAGE_DIMEN_LOWMEM = 768;
  private static final int MAX_IMAGE_DIMEN        = 1024;
  public  static final int MAX_MESSAGE_SIZE       = 280 * 1024;

  @Override
  public int getImageMaxWidth(Context context) {
    return Util.isLowMemory(context) ? MAX_IMAGE_DIMEN_LOWMEM : MAX_IMAGE_DIMEN;
  }

  @Override
  public int getImageMaxHeight(Context context) {
    return getImageMaxWidth(context);
  }

  @Override
  public int getImageMaxSize() {
    return MAX_MESSAGE_SIZE;
  }

  @Override
  public int getVideoMaxSize() {
    return MAX_MESSAGE_SIZE;
  }

  @Override
  public int getAudioMaxSize() {
    return MAX_MESSAGE_SIZE;
  }
}
