package org.thoughtcrime.SMP.crypto.SMP;

/**
 * Created by ludwig on 21/07/15.
 */
public class TextSecureSMPSyncContext {

	private final String destination;
	private final long timestamp;

	public TextSecureSMPSyncContext(String destination, long timestamp) {
		this.destination = destination;
		this.timestamp = timestamp;
	}

	public String getDestination() {
		return this.destination;
	}

	public long getTimestamp() {
		return this.timestamp;
	}
}
