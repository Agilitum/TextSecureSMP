package org.thoughtcrime.SMP.crypto.SMP;

/**
 * Created by ludwig on 29/06/15.
 */

public class SMPException extends Exception {
	private static final long serialVersionUID = 1L;

	public SMPException()
	{
		super("");
	}

	public SMPException(Throwable cause) {
		super(cause);
	}

	public SMPException(String message)
	{
		super(message);
	}
};
