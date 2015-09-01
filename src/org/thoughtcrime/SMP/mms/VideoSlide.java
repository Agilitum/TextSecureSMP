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
package org.thoughtcrime.SMP.mms;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources.Theme;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.DrawableRes;
import android.util.Log;

import org.thoughtcrime.SMP.R;
import org.thoughtcrime.SMP.crypto.MasterSecret;
import org.thoughtcrime.SMP.util.ResUtil;

import java.io.IOException;

import ws.com.google.android.mms.pdu.PduPart;

public class VideoSlide extends Slide {

  public VideoSlide(Context context, Uri uri) throws IOException, MediaTooLargeException {
    super(context, constructPartFromUri(context, uri));
  }

  public VideoSlide(Context context, MasterSecret masterSecret, PduPart part) {
    super(context, masterSecret, part);
  }

  @Override
  public @DrawableRes int getPlaceholderRes(Theme theme) {
    return ResUtil.getDrawableRes(theme, R.attr.conversation_icon_attach_video);
  }

  @Override
  public boolean hasImage() {
    return true;
  }

  @Override
  public boolean hasVideo() {
    return true;
  }

  private static PduPart constructPartFromUri(Context context, Uri uri)
      throws IOException, MediaTooLargeException
  {
    PduPart         part     = new PduPart();
    ContentResolver resolver = context.getContentResolver();
    Cursor          cursor   = null;

    try {
      cursor = resolver.query(uri, new String[] {MediaStore.Video.Media.MIME_TYPE}, null, null, null);
      if (cursor != null && cursor.moveToFirst()) {
        Log.w("VideoSlide", "Setting mime type: " + cursor.getString(0));
        part.setContentType(cursor.getString(0).getBytes());
      }
    } finally {
      if (cursor != null)
        cursor.close();
    }

    assertMediaSize(context, uri);
    part.setDataUri(uri);
    part.setContentId((System.currentTimeMillis()+"").getBytes());
    part.setName(("Video" + System.currentTimeMillis()).getBytes());

    return part;
  }
}
