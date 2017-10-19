package cz.droidboy.androidmvp.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cz.droidboy.androidmvp.PresenterRetainer;
import cz.droidboy.androidmvp.PresenterRetainerActivity;

/**
 * @author Jonáš Ševčík`
 */

public class ParentActivity extends AppCompatActivity implements PresenterRetainerActivity {

    public static final String FRAGMENT_1 = "fragment 1";
    public static final String FRAGMENT_2 = "fragment 2";

    private PresenterRetainer mPresenterRetainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenterRetainer = new PresenterRetainer();

        if (getSupportFragmentManager().findFragmentByTag(FRAGMENT_1) == null) {
            getSupportFragmentManager().beginTransaction()
                .add(new TestUpdatableViewFragment(), FRAGMENT_1)
                .commitNow();
        }

        if (getSupportFragmentManager().findFragmentByTag(FRAGMENT_2) == null) {
            getSupportFragmentManager().beginTransaction()
                .add(new TestUpdatableViewFragment(), FRAGMENT_2)
                .commitNow();
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mPresenterRetainer;
    }

    @Override
    public PresenterRetainer getPresenterRetainer() {
        return mPresenterRetainer;
    }
}