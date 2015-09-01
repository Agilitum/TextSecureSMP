package org.thoughtcrime.SMP.jobs.requirements;

import android.content.Context;

import org.thoughtcrime.SMP.sms.TelephonyServiceState;
import org.whispersystems.jobqueue.dependencies.ContextDependent;
import org.whispersystems.jobqueue.requirements.Requirement;

public class ServiceRequirement implements Requirement, ContextDependent {

  private static final String TAG = ServiceRequirement.class.getSimpleName();

  private transient Context context;

  public ServiceRequirement(Context context) {
    this.context  = context;
  }

  @Override
  public void setContext(Context context) {
    this.context = context;
  }

  @Override
  public boolean isPresent() {
    TelephonyServiceState telephonyServiceState = new TelephonyServiceState();
    return telephonyServiceState.isConnected(context);
  }
}
