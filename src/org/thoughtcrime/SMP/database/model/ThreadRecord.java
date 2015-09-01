/**
 * Copyright (C) 2012 Moxie Marlinspike
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.thoughtcrime.SMP.database.model;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;

import org.thoughtcrime.SMP.R;
import org.thoughtcrime.SMP.database.MmsSmsColumns;
import org.thoughtcrime.SMP.database.SmsDatabase;
import org.thoughtcrime.SMP.recipients.Recipients;
import org.thoughtcrime.SMP.util.GroupUtil;

/**
 * The message record model which represents thread heading messages.
 *
 * @author Moxie Marlinspike
 *
 */
public class ThreadRecord extends DisplayRecord {

  private final Context context;
  private final long count;
  private final boolean read;
  private final int distributionType;

  public ThreadRecord(Context context, Body body, Recipients recipients, long date,
                      long count, boolean read, long threadId, long snippetType,
                      int distributionType)
  {
    super(context, body, recipients, date, date, threadId, snippetType);
    this.context          = context.getApplicationContext();
    this.count            = count;
    this.read             = read;
    this.distributionType = distributionType;
  }

  @Override
  public SpannableString getDisplayBody() {
    if (SmsDatabase.Types.isDecryptInProgressType(type)) {
      return emphasisAdded(context.getString(R.string.MessageDisplayHelper_decrypting_please_wait));
    } else if (isGroupUpdate()) {
      return emphasisAdded(GroupUtil.getDescription(context, getBody().getBody()));
    } else if (isGroupQuit()) {
      return emphasisAdded(context.getString(R.string.ThreadRecord_left_the_group));
    } else if (isKeyExchange()) {
      return emphasisAdded(context.getString(R.string.ConversationListItem_key_exchange_message));
    } else if (SmsDatabase.Types.isFailedDecryptType(type)) {
      return emphasisAdded(context.getString(R.string.MessageDisplayHelper_bad_encrypted_message));
    } else if (SmsDatabase.Types.isNoRemoteSessionType(type)) {
      return emphasisAdded(context.getString(R.string.MessageDisplayHelper_message_encrypted_for_non_existing_session));
    } else if (!getBody().isPlaintext()) {
      return emphasisAdded(context.getString(R.string.MessageNotifier_encrypted_message));
    } else if (SmsDatabase.Types.isEndSessionType(type)) {
      return emphasisAdded(context.getString(R.string.TheadRecord_secure_session_ended));
    } else if (MmsSmsColumns.Types.isLegacyType(type)) {
      return emphasisAdded(context.getString(R.string.MessageRecord_message_encrypted_with_a_legacy_protocol_version_that_is_no_longer_supported));
    } else if (MmsSmsColumns.Types.isDraftMessageType(type)) {
      String draftText = context.getString(R.string.ThreadRecord_draft);
      return emphasisAdded(draftText + " " + getBody().getBody(), 0, draftText.length());
    } else {
      if (TextUtils.isEmpty(getBody().getBody())) {
        return new SpannableString(context.getString(R.string.MessageNotifier_no_subject));
      } else {
        return new SpannableString(getBody().getBody());
      }
    }
  }

  private SpannableString emphasisAdded(String sequence) {
    return emphasisAdded(sequence, 0, sequence.length());
  }

  private SpannableString emphasisAdded(String sequence, int start, int end) {
    SpannableString spannable = new SpannableString(sequence);
    spannable.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC),
                      start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    return spannable;
  }

  public long getCount() {
    return count;
  }

  public boolean isRead() {
    return read;
  }

  public long getDate() {
    return getDateReceived();
  }

  public int getDistributionType() {
    return distributionType;
  }
}
