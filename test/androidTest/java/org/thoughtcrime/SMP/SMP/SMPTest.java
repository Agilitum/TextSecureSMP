package org.thoughtcrime.SMP.SMP;

import android.util.Base64;

import org.thoughtcrime.SMP.TextSecureTestCase;
import org.thoughtcrime.SMP.crypto.SMP.SMP;
import org.thoughtcrime.SMP.crypto.SMP.SMPState;

/**
 * Created by ludwig on 20/08/15.
 */
public class SMPTest extends TextSecureTestCase {

	final SMP smp = new SMP();
	private SMPState aState = new SMPState();
	private SMPState bState = new SMPState();

	String secretString = "secret";
	private byte[] secret = secretString.getBytes();

	int question = 123;

	byte[] step1Actual;
	byte[] step2Actual;
	byte[] step3Actual;
	byte[] step4Actual;

	String step1StringExpected =
		"    AAAABgAAAMDnGRFuM6FbLQLSOqoG4k6ESIuQupw/AOQPQKh84qHba4bP9mHfjdEyjCWo4tBlHg2b\n" +
		"    C5Mt5jgkYemE2LAJK85of6bV3YUFuxrVufCuTUzBymKIlsPEUpctHPr7sXwml01yrBHFKI/MdMQB\n" +
		"    l8IDGQ3EAl9du4CPCRlmHHv2f5Ljdihu/KxG1iYVtZsXSNiwWITD3Cc+IFk8IqDMOPZY4uj4Lkr7\n" +
		"    KrzMWydLTt3wY7iniZ4SKpcELDWTgpeTy87J/ZQAAAAgQKOMj3YoNLlkjwQRBijvWNmN9hXpkrvi\n" +
		"    eNU7VfMd/20AAADATAJ7xESTOsgfY7aiYpbHiKxRPv+m6GWnwQZJi8SIiaQff0TIGMx4ffKjnm2b\n" +
		"    pT6KAN7CqAv2hqqMxs9edEM3TAYvZqHwReFc+NEg1PxNUOum96AT7C3ORDpxF4GEju6Rt8ht0SFs\n" +
		"    mY+OkDIo+QU6eR/nLpCC83KZOiwnfxBP2B953hPWW+meov8GiUXxgbMch1nzCsCUGzqhAtXHGpBM\n" +
		"    exJfgoU+F6711sTK3I4AD32wmYR9zgWkmxO47JUHZ3x8AAAAwNXeufH1ouOTpHX6X2KOLw5EsWMg\n" +
		"    7jocAFtr1mbJyBwuqhHyrxgtFaTQIt4hsQzkpX5AjEIXrcIt+P/+4YyJoF+Dh7pEGfqn+N3SGyk+\n" +
		"    2gttiTyuY1pl6lRUjJlyZcmoHJy5OpGkQYoLKUqgnVmnLwHZjCgjBH1f01UnWUjJTTazcpwCw1Lx\n" +
		"    od4x7QICQqVmiJnaGaCaYsrSkYtGoMTILJ3dk2FZLqseOj+ElV210XtVghlVAyxROK3kDP1pKNng\n" +
		"    HgAAACDlnu3oERXQwQuIrxdq2L7DoW9lImgQC7AKwAn2qHtT3QAAAMALuCjPKkePx165hiZFX4Tc\n" +
		"    BWdKH3jUbt5IJt1Uvgnld9n8l1muEXTbTXhn7DnxFHX9f7LcgUlGkt2i+/9bDERDgpd/5ZDZ71Hi\n" +
		"    RS4ug3OepafHcIDCunnAo9AgoA6w9zTNBPQWmyYbVxW4pMrV1O9/5s1Pz/do6v5kyIyAq6H8Md+G\n" +
		"    vyje1k5H937DJtbIK6OQhMWPpGXcygINjdZBk7sE5MetXSnddYuelOx4AE58uxXYitw6dWCSpIQQ\n" +
		"    iDeXDB4=";
	String step2StringExpected =
		"    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\n" +
		"    7lEulwSiQwqv/bSpmHPlqKB1dNtqI4NTBCBd";
	String step3StringExpected =
		"    AAAACAAAAMBX3UbTCHtzS9SP3Re9pppCMF5sSsBUiQTQfr+MurIwZEyYnQ1uB/YOBlRD9hPgROl2\n" +
		"    Y3KwU7wX9I3olWs4lqF4cihL86toMwlB7Sy9BOJqArCh+9Lx6fOLObVhZRvvYsZBOCZWBfiPEkRc\n" +
		"    nzNgBc17PDGRjdJqX5pkPKUweNjxljckyjwOIxGNzHFyJwt3ypHP+HO1kNSY5ItyWN06lYokEIhp\n" +
		"    r3fv8C1QcARXZ5d2TjVUG1MewEQ6/2urUOWLx6MAAADA0vu1n2/l6981xydz/3+8IkXXK4dRuc9k\n" +
		"    p4dswy7wXzDiWcDGTkGDkzeuY1aHEstnsTl1UHhTQUJi5S32iBLlqj+EuyIlm0iG5oLWmXjKTKfZ\n" +
		"    SWDjL3G41KjpXDr7YKNIgW3y/4UxOxtuIrXGu38+rZu6LBiM09Gm36GJ8ocfRBqFrwgySXy1vXHS\n" +
		"    OKOzVGBo1DZixIH0ujhArcg/CPvuPNJHMIvuRQqDwLrAHRrAQGXkTBpQA0qR55LBWMeiBR4HAAAA\n" +
		"    IMIPrrc9kVT+1EGbBi7qmuAW5lJXoLd5S1F8EB5yi9GFAAAAwFP5TWNVG8Xn5YIXF+LzZMn1WO8B\n" +
		"    x6tqa9wahD2hfTPv9z5Zg01HLxlP7MfhmaRLC94DY6onHBIDOa5HpU3FT5xsYeZYBHvF5e1DejwS\n" +
		"    86TU5NDnqlVJrP9WrMGEofLpSuo5y05uanwPFh7uv0wJYh34msUGiataevhckSrM2AhRFfnY7g5/\n" +
		"    Mr6P2E0op2VQUCiYpMhcscktHkP70OR51tntrl7+2NhaarPAsft5QIKZCwr+A9ppwHbiVStq6G35\n" +
		"    PwAAAMAWVVrS1TyJ2X+CZ4iAauOsbyLlZtdpOaVaCIikQoXsNy6rhwqeFR1sfTgk4wr+qaadXBlU\n" +
		"    BcwFOlfCNITD9JO/IKvSdDIyt05v4ywjgVgeBo4Mdysd6zexsWkt9yIdqcPSy243jswlmXlBaOhL\n" +
		"    6Bs8hgpwaohZIV3RJXnqbl4FwlHx/LBIlcfLRetvpZEEdjlz/MRoTWg7qm6gib4+7GgGmYSN1SQ4\n" +
		"    TBHLP01PugDvIXqDzgEyIwbHzRkyjEKYdD8AAADAv//DYRcLiT9BKIO5TRfOw2/ry+PAqHBpmO5J\n" +
		"    R9GhrPAk3KEDnOAUveUzeKXSySXXaC+lM44OrCLe8QytZMQBPdDZ8e0TBta6+4DoDCVRK3r/Jlvw\n" +
		"    E0iCTGdH47tNQicIxlNdFdmF/nCj405zaU0w7+XOWMqRa8KyEqBVDmr7GT74d7bIzfCf8wOpC3bj\n" +
		"    Li5TkDUl890JItub4xjC2k8k4WDiAl4iI8ajZZfbLz8sZB81q6fR8gGze38zkpydz9vzAAAAIEkC\n" +
		"    HfIbWFbwHPatELcOOBpdg8j8cRXe3y2DfxOsxWdNAAAAwCxBPlisAzFDdTnr8qF1ydPhk689ND8K\n" +
		"    Y/tqFjocR2lzP+3JyKHpG5csJFc40Hn0/UyjW3jbXZXNEIlglogQk1r/pDwXcWfc2vJDWJHIZVSn\n" +
		"    EmSMwWWMt614CutzuV4GviDtJcRwhLNuUop+arPP41Udz7ff0RjofToVBPvWQvdnsqXttHTOfjtx\n" +
		"    Y1s8a+xvnVJXJ4ys3Pw2IAYz4lQAQsKzFaW240AhZNL3Tsfd9d91awmcPRcj3z3OHPgpdztg6A==";

	byte[] step1Expected = decode(step1StringExpected);
	byte[] step2Expected = decode(step2StringExpected);
	byte[] step3Expected = decode(step3StringExpected);

	private void testStep1() throws Exception {
		step1Actual = SMP.step1(aState, secret);

		for(byte b : step1Actual)
			assertNotNull(b);
		assertEquals(step1Expected, step1Actual);
	}

	private void testStep2() throws Exception{
		SMP.step2a(bState,step1Actual,question);
		step2Actual = SMP.step2b(bState, secret);

		for(byte b : step2Actual)
			assertNotNull(b);
		assertEquals(step2Expected, step2Actual);
	}

	private void testStep3() throws Exception{
		step3Actual = SMP.step3(aState, step2Actual);

		for(byte b : step3Actual)
			assertNotNull(b);
		assertEquals(step3Expected, step3Actual);
	}

	private void testStep4() throws Exception{
		step4Actual = SMP.step4(bState, step3Actual);
		assertEquals(1, bState.smProgState);
	}

	private void testStep5() throws Exception{
		SMP.step5(aState, step4Actual);
		assertEquals(1, aState.smProgState);
	}

	private byte[] decode(String input) {
		return  Base64.decode(input.getBytes(), 0);
	}
}
