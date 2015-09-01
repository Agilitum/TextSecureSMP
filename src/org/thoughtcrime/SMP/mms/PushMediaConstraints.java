package org.thoughtcrime.SMP.mms;

import android.content.Context;

import org.thoughtcrime.SMP.util.Util;

public class PushMediaConstraints extends MediaConstraints {
  private static final int MAX_IMAGE_DIMEN_LOWMEM = 768;
  private static final int MAX_IMAGE_DIMEN        = 1280;
  private static final int KB                     = 1024;
  private static final int MB                     = 1024 * KB;

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
    return 420 * KB;
  }

  @Override
  public int getVideoMaxSize() {
    return MmsMediaConstraints.MAX_MESSAGE_SIZE;
  }

  @Override
  public int getAudioMaxSize() {
    return MmsMediaConstraints.MAX_MESSAGE_SIZE;
  }
}
