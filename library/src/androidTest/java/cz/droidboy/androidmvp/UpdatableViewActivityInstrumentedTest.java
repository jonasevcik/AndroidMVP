package cz.droidboy.androidmvp;

import java.lang.ref.WeakReference;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import cz.droidboy.androidmvp.helper.TestUpdatableViewActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertSame;

@RunWith(AndroidJUnit4.class)
public class UpdatableViewActivityInstrumentedTest {

    private TestUpdatableViewActivity activity;

    @Rule
    public ActivityTestRule<TestUpdatableViewActivity> activityRule = new ActivityTestRule<>(TestUpdatableViewActivity.class);

    @Before
    public void init() {
        activity = activityRule.getActivity();
    }

    @Test
    public void testPresenterSurvivesActivityRotation() throws Exception {
        testRotation(1);
    }

    @Test
    public void testPresenterSurvives10ActivityRotations() throws Exception {
        testRotation(10);
    }

    private void testRotation(int times) {
        //WeakReference allows GC to collect object during Activity recreation if applicable
        WeakReference<TestUpdatableViewActivity.TestPresenter> presenter1Reference = new WeakReference<>(activity.getPresenter());
        assertNotNull(presenter1Reference.get());
        for (int i = 0; i < times; i++) {
            rotateScreen(activity);
        }
        TestUpdatableViewActivity.TestPresenter presenter2 = activity.getPresenter();
        assertNotNull(presenter2);
        assertSame(presenter1Reference.get(), presenter2);
    }

    private void rotateScreen(Activity activity) {
        Context context = InstrumentationRegistry.getTargetContext();
        int orientation = context.getResources().getConfiguration().orientation;

        activity.setRequestedOrientation(
            (orientation == Configuration.ORIENTATION_PORTRAIT) ?
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @TargetApi(11)
    @Test
    public void testPresenterSurvivesActivityRecreation() throws Exception {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            WeakReference<TestUpdatableViewActivity.TestPresenter> presenter1Reference = new WeakReference<>(activity.getPresenter());
            assertNotNull(presenter1Reference.get());
            InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
                @Override
                public void run() {
                    activity.recreate();
                }
            });
            TestUpdatableViewActivity.TestPresenter presenter2 = activity.getPresenter();
            assertNotNull(presenter2);
            assertSame(presenter1Reference.get(), presenter2);
        } else {
            throw new UnsupportedOperationException("This test is supported only on devices running SDK >= 11");
        }
    }

    @Test
    public void testPresenterDataSurvivesActivityRotation() throws Exception {
        activity.getPresenter().setText("text");
        testRotation(1);
        assertEquals("text", activity.getPresenter().getText());
    }
}
