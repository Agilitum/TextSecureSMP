/**
 * Copyright (C) 2011 Whisper Systems
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
package org.thoughtcrime.SMP;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import org.thoughtcrime.SMP.ConversationFragment.SelectionClickListener;
import org.thoughtcrime.SMP.crypto.MasterSecret;
import org.thoughtcrime.SMP.database.DatabaseFactory;
import org.thoughtcrime.SMP.database.MmsSmsColumns;
import org.thoughtcrime.SMP.database.MmsSmsDatabase;
import org.thoughtcrime.SMP.database.SmsDatabase;
import org.thoughtcrime.SMP.database.model.MessageRecord;
import org.thoughtcrime.SMP.util.LRUCache;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * A cursor adapter for a conversation thread.  Ultimately
 * used by ComposeMessageActivity to display a conversation
 * thread in a ListActivity.
 *
 * @author Moxie Marlinspike
 *
 */
public class ConversationAdapter extends CursorAdapter implements AbsListView.RecyclerListener {

  private static final String TAG = ConversationAdapter.class.getSimpleName();

  private static final int MAX_CACHE_SIZE = 40;
  private final Map<String,SoftReference<MessageRecord>> messageRecordCache =
      Collections.synchronizedMap(new LRUCache<String, SoftReference<MessageRecord>>(MAX_CACHE_SIZE));

  public static final int MESSAGE_TYPE_OUTGOING = 0;
  public static final int MESSAGE_TYPE_INCOMING = 1;
  public static final int MESSAGE_TYPE_GROUP_ACTION = 2;

  private final Set<MessageRecord> batchSelected = Collections.synchronizedSet(new HashSet<MessageRecord>());

  private final SelectionClickListener selectionClickListener;
  private final Context                context;
  private final MasterSecret           masterSecret;
  private final Locale                 locale;
  private final boolean                groupThread;
  private final boolean                pushDestination;
  private final LayoutInflater         inflater;

  public ConversationAdapter(Context context, MasterSecret masterSecret, Locale locale,
                             SelectionClickListener selectionClickListener, boolean groupThread,
                             boolean pushDestination)
  {
    super(context, null, 0);
    this.context                = context;
    this.masterSecret           = masterSecret;
    this.locale                 = locale;
    this.selectionClickListener = selectionClickListener;
    this.groupThread            = groupThread;
    this.pushDestination        = pushDestination;
    this.inflater               = LayoutInflater.from(context);

  }

  @Override
  public void bindView(View view, Context context, Cursor cursor) {
    ConversationItem item       = (ConversationItem)view;
    long id                     = cursor.getLong(cursor.getColumnIndexOrThrow(SmsDatabase.ID));
    String type                 = cursor.getString(cursor.getColumnIndexOrThrow(MmsSmsDatabase.TRANSPORT));
    MessageRecord messageRecord = getMessageRecord(id, cursor, type);


    item.set(masterSecret, messageRecord, locale, batchSelected, selectionClickListener,
             groupThread, pushDestination);
  }


  @Override
  public void changeCursor(Cursor cursor) {
    messageRecordCache.clear();
    super.changeCursor(cursor);
  }

  @Override
  public View newView(Context context, Cursor cursor, ViewGroup parent) {
    View view;

    int type = getItemViewType(cursor);

    switch (type) {
      case ConversationAdapter.MESSAGE_TYPE_OUTGOING:
        view = inflater.inflate(R.layout.conversation_item_sent, parent, false);
        break;
      case ConversationAdapter.MESSAGE_TYPE_INCOMING:
        view = inflater.inflate(R.layout.conversation_item_received, parent, false);
        break;
      case ConversationAdapter.MESSAGE_TYPE_GROUP_ACTION:
        view = inflater.inflate(R.layout.conversation_item_activity, parent, false);
        break;
      default: throw new IllegalArgumentException("unsupported item view type given to ConversationAdapter");
    }

    return view;
  }

  @Override
  public int getViewTypeCount() {
    return 3;
  }

  @Override
  public int getItemViewType(int position) {
    Cursor cursor = (Cursor)getItem(position);
    return getItemViewType(cursor);
  }

  private int getItemViewType(Cursor cursor) {
    long id                     = cursor.getLong(cursor.getColumnIndexOrThrow(MmsSmsColumns.ID));
    String type                 = cursor.getString(cursor.getColumnIndexOrThrow(MmsSmsDatabase.TRANSPORT));
    MessageRecord messageRecord = getMessageRecord(id, cursor, type);

    if      (messageRecord.isGroupAction()) return MESSAGE_TYPE_GROUP_ACTION;
    else if (messageRecord.isOutgoing())    return MESSAGE_TYPE_OUTGOING;
    else                                    return MESSAGE_TYPE_INCOMING;
  }

  private MessageRecord getMessageRecord(long messageId, Cursor cursor, String type) {
    SoftReference<MessageRecord> reference = messageRecordCache.get(type + messageId);

    if (reference != null) {
      MessageRecord record = reference.get();

      if (record != null)
        return record;
    }


    MmsSmsDatabase.Reader reader = DatabaseFactory.getMmsSmsDatabase(context)
                                                  .readerFor(cursor, masterSecret);

    MessageRecord messageRecord = reader.getCurrent();
/*
    Log.d(TAG, "messageRecord.isSMPMessage(): " + messageRecord.isSMPMessage());
    if (messageRecord.isSMPMessage()) {
      MessageRecord newMessageRecord = new SmsMessageRecord(context, messageRecord.getId(), new
        DisplayRecord.Body("SMP_" + messageRecord.getId(), true), messageRecord.getRecipients(),
        messageRecord
        .getIndividualRecipient(),
        messageRecord.getRecipientDeviceId(), messageRecord.getDateSent(), messageRecord
        .getDateReceived(), 0, messageRecord.getType(), messageRecord.getThreadId(), messageRecord
        .getDeliveryStatus(), null);
      Log.d(TAG, "messageRecord: " + newMessageRecord.getDisplayBody().toString());
      return newMessageRecord;
    }
*/
    messageRecordCache.put(type + messageId, new SoftReference<MessageRecord>(messageRecord));

    return messageRecord;
  }

  public void close() {
    this.getCursor().close();
  }

  public void toggleBatchSelected(MessageRecord messageRecord) {
    if (batchSelected.contains(messageRecord)) {
      batchSelected.remove(messageRecord);
    } else {
      batchSelected.add(messageRecord);
    }
  }

  public Set<MessageRecord> getBatchSelected() {
    return batchSelected;
  }

  @Override
  public void onMovedToScrapHeap(View view) {
    ((ConversationItem)view).unbind();
  }
}
