package org.thoughtcrime.SMP.SMP;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import org.thoughtcrime.SMP.TextSecureTestCase;
import org.thoughtcrime.SMP.VerifyIdentityActivity;
import org.thoughtcrime.SMP.crypto.SMP.SMPException;


/**
 * Created by ludwig on 13/07/15.
 */
public class VerifyIdentityActivityTest extends TextSecureTestCase {

	final VerifyIdentityActivity verifyIdentityActivity = new VerifyIdentityActivity();

	public void testSMPSession() throws Exception {

		final Context mockContext     = mock(Context.class);
		final SharedPreferences mockPreferences = mock(SharedPreferences.class);

		final BroadcastReceiver broadcastReceiver = mock(BroadcastReceiver.class);

		/*
		// initiator
		try {
			verifyIdentityActivity.smpSession(1337, true);
		} catch (IllegalStateException | SMPException e) {
			Log.d(getClass().getName(), "smpSession as initiator failed +  " + e.getMessage());
		}

		// verifier
		try {
			verifyIdentityActivity.smpSession(1337, false);
		} catch (IllegalStateException | SMPException e) {
			Log.d(getClass().getName(), "smpSession as verifier failed + " + e.getMessage());
		}
	}
	*/
}
