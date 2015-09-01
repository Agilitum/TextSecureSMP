package org.thoughtcrime.SMP.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;

import org.thoughtcrime.SMP.TransportOption;
import org.thoughtcrime.SMP.TransportOptions;
import org.thoughtcrime.SMP.TransportOptions.OnTransportChangedListener;
import org.thoughtcrime.SMP.TransportOptionsPopup;

public class SendButton extends ImageButton
    implements TransportOptions.OnTransportChangedListener,
               TransportOptionsPopup.SelectedListener,
               View.OnLongClickListener
{

  private final TransportOptions      transportOptions;
  private final TransportOptionsPopup transportOptionsPopup;

  @SuppressWarnings("unused")
  public SendButton(Context context) {
    super(context);
    this.transportOptions      = initializeTransportOptions(false);
    this.transportOptionsPopup = initializeTransportOptionsPopup();
  }

  @SuppressWarnings("unused")
  public SendButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.transportOptions      = initializeTransportOptions(false);
    this.transportOptionsPopup = initializeTransportOptionsPopup();
  }

  @SuppressWarnings("unused")
  public SendButton(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    this.transportOptions      = initializeTransportOptions(false);
    this.transportOptionsPopup = initializeTransportOptionsPopup();
  }

  private TransportOptions initializeTransportOptions(boolean media) {
    TransportOptions transportOptions = new TransportOptions(getContext(), media);
    transportOptions.addOnTransportChangedListener(this);

    setOnLongClickListener(this);

    return transportOptions;
  }

  private TransportOptionsPopup initializeTransportOptionsPopup() {
    return new TransportOptionsPopup(getContext(), this);
  }

  public boolean isManualSelection() {
    return transportOptions.isManualSelection();
  }

  public void addOnTransportChangedListener(OnTransportChangedListener listener) {
    transportOptions.addOnTransportChangedListener(listener);
  }

  public TransportOption getSelectedTransport() {
    return transportOptions.getSelectedTransport();
  }

  public void resetAvailableTransports(boolean isMediaMessage) {
    transportOptions.reset(isMediaMessage);
  }

  public void disableTransport(TransportOption.Type type) {
    transportOptions.disableTransport(type);
  }

  public void setDefaultTransport(TransportOption.Type type) {
    transportOptions.setDefaultTransport(type);
  }

  @Override
  public void onSelected(TransportOption option) {
    transportOptions.setSelectedTransport(option.getType());
    transportOptionsPopup.dismiss();
  }

  @Override
  public void onChange(TransportOption newTransport) {
    setImageResource(newTransport.getDrawable());
    setContentDescription(newTransport.getDescription());
  }

  @Override
  public boolean onLongClick(View v) {
    if (transportOptions.getEnabledTransports().size() > 1) {
      transportOptionsPopup.display(getContext(), SendButton.this, transportOptions.getEnabledTransports());
      return true;
    }

    return false;
  }
}
