package org.thoughtcrime.SMP.mms;

import android.content.ContentUris;
import android.net.Uri;

import org.thoughtcrime.SMP.database.PartDatabase;

public class PartUriParser {

  private final Uri uri;

  public PartUriParser(Uri uri) {
    this.uri = uri;
  }

  public PartDatabase.PartId getPartId() {
    return new PartDatabase.PartId(getId(), getUniqueId());
  }

  private long getId() {
    return ContentUris.parseId(uri);
  }

  private long getUniqueId() {
    return Long.parseLong(uri.getPathSegments().get(1));
  }

}
