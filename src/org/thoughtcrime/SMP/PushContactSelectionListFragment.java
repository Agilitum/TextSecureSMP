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


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import org.thoughtcrime.SMP.contacts.ContactAccessor;
import org.thoughtcrime.SMP.contacts.ContactSelectionListAdapter;
import org.thoughtcrime.SMP.contacts.ContactSelectionListItem;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Fragment for selecting a one or more contacts from a list.
 *
 * @author Moxie Marlinspike
 *
 */

public class PushContactSelectionListFragment extends    Fragment
                                              implements LoaderManager.LoaderCallbacks<Cursor>
{
  private static final String TAG = "ContactSelectFragment";

  private TextView emptyText;

  private Map<Long, String>         selectedContacts;
  private OnContactSelectedListener onContactSelectedListener;
  private StickyListHeadersListView listView;
  private String                    cursorFilter;

  private boolean multi = false;

  @Override
  public void onActivityCreated(Bundle icicle) {
    super.onCreate(icicle);
    initializeResources();
    initializeCursor();
  }

  @Override
  public void onResume() {
    super.onResume();
  }

  @Override
  public void onPause() {
    super.onPause();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.push_contact_selection_list_activity, container, false);
  }

  public List<String> getSelectedContacts() {
    if (selectedContacts == null) return null;

    List<String> selected = new LinkedList<>();
    selected.addAll(selectedContacts.values());

    return selected;
  }

  public void setMultiSelect(boolean multi) {
    this.multi = multi;
  }

  private void initializeCursor() {
    ContactSelectionListAdapter adapter = new ContactSelectionListAdapter(getActivity(), null, multi);
    selectedContacts = adapter.getSelectedContacts();
    listView.setAdapter(adapter);
    this.getLoaderManager().initLoader(0, null, this);
  }

  private void initializeResources() {
    emptyText = (TextView) getView().findViewById(android.R.id.empty);
    listView  = (StickyListHeadersListView) getView().findViewById(android.R.id.list);
    listView.setFocusable(true);
    listView.setFastScrollEnabled(true);
    listView.setDrawingListUnderStickyHeader(false);
    listView.setOnItemClickListener(new ListClickListener());

    EditText filterEditText = (EditText) getView().findViewById(R.id.filter);
    filterEditText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        cursorFilter = charSequence.toString();
        update();
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });
    cursorFilter = null;
  }

  public void update() {
    this.getLoaderManager().restartLoader(0, null, this);
  }

  @Override
  public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    if (getActivity().getIntent().getBooleanExtra(PushContactSelectionActivity.PUSH_ONLY_EXTRA, false)) {
      return ContactAccessor.getInstance().getCursorLoaderForPushContacts(getActivity(), cursorFilter);
    } else {
      return ContactAccessor.getInstance().getCursorLoaderForContacts(getActivity(), cursorFilter);
    }
  }

  @Override
  public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    ((CursorAdapter) listView.getAdapter()).changeCursor(data);
    emptyText.setText(R.string.contact_selection_group_activity__no_contacts);
  }

  @Override
  public void onLoaderReset(Loader<Cursor> loader) {
    ((CursorAdapter) listView.getAdapter()).changeCursor(null);
  }

  private class ListClickListener implements AdapterView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
      ContactSelectionListItem contact = (ContactSelectionListItem)v;

      if (!multi || !selectedContacts.containsKey(contact.getContactId())) {
        selectedContacts.put(contact.getContactId(), contact.getNumber());
        contact.setChecked(true);
        if (onContactSelectedListener != null) onContactSelectedListener.onContactSelected(contact.getNumber());
      } else {
        selectedContacts.remove(contact.getContactId());
        contact.setChecked(false);
      }
    }
  }

  public void setOnContactSelectedListener(OnContactSelectedListener onContactSelectedListener) {
    this.onContactSelectedListener = onContactSelectedListener;
  }

  public interface OnContactSelectedListener {
    public void onContactSelected(String number);
  }
}
