package org.thoughtcrime.SMP.database.loaders;

import android.content.Context;
import android.database.Cursor;

import org.thoughtcrime.SMP.database.DatabaseFactory;
import org.thoughtcrime.SMP.util.AbstractCursorLoader;

public class BlockedContactsLoader extends AbstractCursorLoader {

  public BlockedContactsLoader(Context context) {
    super(context);
  }

  @Override
  public Cursor getCursor() {
    return DatabaseFactory.getRecipientPreferenceDatabase(getContext())
                          .getBlocked();
  }

}
