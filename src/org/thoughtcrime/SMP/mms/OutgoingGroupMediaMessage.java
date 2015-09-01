package org.thoughtcrime.SMP.mms;

import android.content.Context;

import org.thoughtcrime.SMP.database.ThreadDatabase;
import org.thoughtcrime.SMP.recipients.Recipients;
import org.thoughtcrime.SMP.util.Base64;

import ws.com.google.android.mms.ContentType;
import ws.com.google.android.mms.pdu.PduBody;
import ws.com.google.android.mms.pdu.PduPart;

import static org.whispersystems.textsecure.internal.push.PushMessageProtos.PushMessageContent.GroupContext;

public class OutgoingGroupMediaMessage extends OutgoingSecureMediaMessage {

  private final GroupContext group;

  public OutgoingGroupMediaMessage(Context context, Recipients recipients,
                                   GroupContext group, byte[] avatar)
  {
    super(context, recipients, new PduBody(), Base64.encodeBytes(group.toByteArray()),
          ThreadDatabase.DistributionTypes.CONVERSATION);

    this.group = group;

    if (avatar != null) {
      PduPart part = new PduPart();
      part.setData(avatar);
      part.setContentType(ContentType.IMAGE_PNG.getBytes());
      part.setContentId((System.currentTimeMillis()+"").getBytes());
      part.setName(("Image" + System.currentTimeMillis()).getBytes());
      body.addPart(part);
    }
  }

  @Override
  public boolean isGroup() {
    return true;
  }

  public boolean isGroupUpdate() {
    return group.getType().getNumber() == GroupContext.Type.UPDATE_VALUE;
  }

  public boolean isGroupQuit() {
    return group.getType().getNumber() == GroupContext.Type.QUIT_VALUE;
  }
}
