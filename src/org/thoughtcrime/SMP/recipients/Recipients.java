/**
 * Copyright (C) 2015 Open Whisper Systems
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
package org.thoughtcrime.SMP.recipients;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Patterns;

import org.thoughtcrime.SMP.database.RecipientPreferenceDatabase.RecipientsPreferences;
import org.thoughtcrime.SMP.database.RecipientPreferenceDatabase.VibrateState;
import org.thoughtcrime.SMP.recipients.Recipient.RecipientModifiedListener;
import org.thoughtcrime.SMP.util.FutureTaskListener;
import org.thoughtcrime.SMP.util.GroupUtil;
import org.thoughtcrime.SMP.util.ListenableFutureTask;
import org.thoughtcrime.SMP.util.NumberUtil;
import org.thoughtcrime.SMP.util.Util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

public class Recipients implements Iterable<Recipient>, RecipientModifiedListener {

  private static final String TAG = Recipients.class.getSimpleName();

  private final Set<RecipientsModifiedListener> listeners = Collections.newSetFromMap(new WeakHashMap<RecipientsModifiedListener, Boolean>());
  private final List<Recipient> recipients;

  private Uri          ringtone          = null;
  private long         mutedUntil        = 0;
  private boolean      blocked           = false;
  private VibrateState vibrate           = VibrateState.DEFAULT;

  Recipients() {
    this(new LinkedList<Recipient>(), (RecipientsPreferences)null);
  }

  Recipients(List<Recipient> recipients, @Nullable RecipientsPreferences preferences) {
    this.recipients = recipients;

    if (preferences != null) {
      ringtone   = preferences.getRingtone();
      mutedUntil = preferences.getMuteUntil();
      vibrate    = preferences.getVibrateState();
      blocked    = preferences.isBlocked();
    }
  }

  Recipients(List<Recipient> recipients, ListenableFutureTask<RecipientsPreferences> preferences) {
    this.recipients = recipients;

    preferences.addListener(new FutureTaskListener<RecipientsPreferences>() {
      @Override
      public void onSuccess(RecipientsPreferences result) {
        if (result != null) {

          Set<RecipientsModifiedListener> localListeners;

          synchronized (Recipients.this) {
            ringtone   = result.getRingtone();
            mutedUntil = result.getMuteUntil();
            vibrate    = result.getVibrateState();
            blocked    = result.isBlocked();

            localListeners = new HashSet<>(listeners);
          }

          for (RecipientsModifiedListener listener : localListeners) {
            listener.onModified(Recipients.this);
          }
        }
      }

      @Override
      public void onFailure(Throwable error) {
        Log.w(TAG, error);
      }
    });
  }

  public synchronized @Nullable Uri getRingtone() {
    return ringtone;
  }

  public void setRingtone(Uri ringtone) {
    synchronized (this) {
      this.ringtone = ringtone;
    }

    notifyListeners();
  }

  public synchronized boolean isMuted() {
    return System.currentTimeMillis() <= mutedUntil;
  }

  public void setMuted(long mutedUntil) {
    synchronized (this) {
      this.mutedUntil = mutedUntil;
    }

    notifyListeners();
  }

  public synchronized boolean isBlocked() {
    return blocked;
  }

  public void setBlocked(boolean blocked) {
    synchronized (this) {
      this.blocked = blocked;
    }

    notifyListeners();
  }

  public synchronized VibrateState getVibrate() {
    return vibrate;
  }

  public void setVibrate(VibrateState vibrate) {
    synchronized (this) {
      this.vibrate = vibrate;
    }

    notifyListeners();
  }

  public synchronized void addListener(RecipientsModifiedListener listener) {
    if (listeners.isEmpty()) {
      for (Recipient recipient : recipients) {
        recipient.addListener(this);
      }
    }

    synchronized (this) {
      listeners.add(listener);
    }
  }

  public synchronized void removeListener(RecipientsModifiedListener listener) {
    listeners.remove(listener);

    if (listeners.isEmpty()) {
      for (Recipient recipient : recipients) {
        recipient.removeListener(this);
      }
    }
  }

  public boolean isEmailRecipient() {
    for (Recipient recipient : recipients) {
      if (NumberUtil.isValidEmail(recipient.getNumber()))
        return true;
    }

    return false;
  }

  public boolean isGroupRecipient() {
    return isSingleRecipient() && GroupUtil.isEncodedGroup(recipients.get(0).getNumber());
  }

  public boolean isEmpty() {
    return this.recipients.isEmpty();
  }

  public boolean isSingleRecipient() {
    return this.recipients.size() == 1;
  }

  public @Nullable Recipient getPrimaryRecipient() {
    if (!isEmpty())
      return this.recipients.get(0);
    else
      return null;
  }

  public List<Recipient> getRecipientsList() {
    return this.recipients;
  }

  public long[] getIds() {
    long[] ids = new long[recipients.size()];
    for (int i=0; i<recipients.size(); i++) {
      ids[i] = recipients.get(i).getRecipientId();
    }
    return ids;
  }

  public String getSortedIdsString() {
    Set<Long> recipientSet  = new HashSet<>();

    for (Recipient recipient : this.recipients) {
      recipientSet.add(recipient.getRecipientId());
    }

    long[] recipientArray = new long[recipientSet.size()];
    int i                 = 0;

    for (Long recipientId : recipientSet) {
      recipientArray[i++] = recipientId;
    }

    Arrays.sort(recipientArray);

    return Util.join(recipientArray, " ");
  }

  public String[] toNumberStringArray(boolean scrub) {
    String[] recipientsArray     = new String[recipients.size()];
    Iterator<Recipient> iterator = recipients.iterator();
    int i                        = 0;

    while (iterator.hasNext()) {
      String number = iterator.next().getNumber();

      if (scrub && number != null &&
          !Patterns.EMAIL_ADDRESS.matcher(number).matches() &&
          !GroupUtil.isEncodedGroup(number))
      {
        number = number.replaceAll("[^0-9+]", "");
      }

      recipientsArray[i++] = number;
    }

    return recipientsArray;
  }

  public String toShortString() {
    String fromString = "";

    for (int i=0;i<recipients.size();i++) {
      fromString += recipients.get(i).toShortString();

      if (i != recipients.size() -1 )
        fromString += ", ";
    }

    return fromString;
  }

  @Override
  public Iterator<Recipient> iterator() {
    return recipients.iterator();
  }

  @Override
  public void onModified(Recipient recipient) {
    notifyListeners();
  }

  private void notifyListeners() {
    Set<RecipientsModifiedListener> localListeners;

    synchronized (this) {
      localListeners = new HashSet<>(listeners);
    }

    for (RecipientsModifiedListener listener : localListeners) {
      listener.onModified(this);
    }
  }


  public interface RecipientsModifiedListener {
    public void onModified(Recipients recipient);
  }

}
