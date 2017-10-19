package cz.droidboy.androidmvp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import cz.droidboy.androidmvp.helper.ParentActivity;
import cz.droidboy.androidmvp.helper.TestUpdatableViewFragment;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.*;

@RunWith(AndroidJUnit4.class)
public class UpdatableViewFragmentInstrumentedTest {

    private ParentActivity activity;

    @Rule
    public ActivityTestRule<ParentActivity> activityRule = new ActivityTestRule<>(ParentActivity.class);

    @Before
    public void init() {
        activity = activityRule.getActivity();
    }

    @Test
    public void test2PresentersAreCreated() throws Exception {
        TestUpdatableViewFragment fragment1 = (TestUpdatableViewFragment) activity.getSupportFragmentManager().findFragmentByTag
            (ParentActivity.FRAGMENT_1);
        assertNotNull(fragment1);
        assertEquals(ParentActivity.FRAGMENT_1, fragment1.getPresenter().getText());

        TestUpdatableViewFragment fragment2 = (TestUpdatableViewFragment) activity.getSupportFragmentManager()
            .findFragmentByTag
            (ParentActivity.FRAGMENT_2);
        assertNotNull(fragment2);
        assertEquals(ParentActivity.FRAGMENT_2, fragment2.getPresenter().getText());
    }

    @Test
    public void OnePresenterRetainerCreated() throws Exception {
        TestUpdatableViewFragment fragment1 = (TestUpdatableViewFragment) activity.getSupportFragmentManager().findFragmentByTag
            (ParentActivity.FRAGMENT_1);

        TestUpdatableViewFragment fragment2 = (TestUpdatableViewFragment) activity.getSupportFragmentManager()
            .findFragmentByTag
                (ParentActivity.FRAGMENT_2);

        assertSame(fragment1.retainCustomNonConfigurationInstance(), fragment2.retainCustomNonConfigurationInstance());
    }
}