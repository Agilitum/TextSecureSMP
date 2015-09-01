package org.thoughtcrime.SMP.crypto.SMP;

import java.math.BigInteger;

/**
 * Created by ludwig on 29/06/15.
 */

public class SMPState {
	BigInteger secret, x2, x3, g1, g2, g3, g3o, p, q, pab, qab;
	public int nextExpected;
	int receivedQuestion;
	public int smProgState;
	public boolean approved;
	public boolean asked;

	// Constructor
	public SMPState(){
		g1 = new BigInteger(1, SMP.GENERATOR_S);
		smProgState = SMP.PROG_OK;
		approved = false;
		asked = false;
	}
}
