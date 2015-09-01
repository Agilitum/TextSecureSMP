package org.thoughtcrime.SMP.mms;

import android.content.Context;

import org.thoughtcrime.SMP.recipients.Recipients;

import ws.com.google.android.mms.pdu.PduBody;

public class OutgoingSecureMediaMessage extends OutgoingMediaMessage {

  public OutgoingSecureMediaMessage(Context context, Recipients recipients, PduBody body,
                                    String message, int distributionType)
  {
    super(context, recipients, body, message, distributionType);
  }

  public OutgoingSecureMediaMessage(OutgoingMediaMessage base) {
    super(base);
  }

  @Override
  public boolean isSecure() {
    return true;
  }
}
