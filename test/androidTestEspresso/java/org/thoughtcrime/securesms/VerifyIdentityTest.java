import android.support.test.espresso.matcher.ViewMatchers;
import android.test.suitebuilder.annotation.LargeTest;

import org.thoughtcrime.SMP.VerifyIdentityActivity;

import static android.support.test.espresso.Espresso.onView;

@LargeTest
public class VerifyIdentityTest extends TextSecureEspressoTestCase<VerifyIdentityActivity> {

	public VerifyIdentityTest() {
		super(VerifyIdentityActivity.class);
	}

	private void clickSMPStartButton() throws Exception {

		onView(ViewMatcher.withId(R.id.smp_start_button)).perform(ViewActions.click());

		// assert spinner + text is activated & shown
		onView(ViewMatcher.withId(R.id.verification_progress)).check(ViewAssertions.matches
			(ViewMatchers.isCompletelyDisplayed()));

		onView(ViewMatcher.withId(R.id.verification_progress_text)).check(ViewAssertions.matches
			(ViewMatchers.isCompletelyDisplayed()));

		// assert correct EditText is set
		onView(ViewMatcher.with(R.id.entity_verified)).check(ViewAssertions.matches(ViewMatchers
			.withText("Entity")));
	}
}