package cz.droidboy.androidmvp.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import cz.droidboy.androidmvp.Presenter;
import cz.droidboy.androidmvp.PresenterBindingDelegate;
import cz.droidboy.androidmvp.PresenterRetainer;
import cz.droidboy.androidmvp.PresenterRetainerActivity;

/**
 * @author Jonáš Ševčík
 */

public abstract class UpdatableViewFragment<P extends Presenter> extends Fragment implements
    PresenterBindingDelegate.PresenterBindingCallback<P> {

    private PresenterBindingDelegate<P> mDelegate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getBindingDelegate().onCreate(savedInstanceState);
    }

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();

        getBindingDelegate().onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getBindingDelegate().onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        getBindingDelegate().onDestroyView();
    }

    public P getPresenter() {
        return getBindingDelegate().getPresenter();
    }

    /**
     * This method must be called by parent Activity for at least one of its UpdatableViewFragments in order to
     * persist them.
     *
     * @return PresenterRetainer to be retained by parent Activity
     */
    public PresenterRetainer retainCustomNonConfigurationInstance() {
        return getBindingDelegate().onRetainCustomNonConfigurationInstance();
    }

    @NonNull
    private PresenterBindingDelegate<P> getBindingDelegate() {
        if (mDelegate == null) {
            if (!(getActivity() instanceof PresenterRetainerActivity)) {
                throw new IllegalStateException("Parent Activity must implement PresenterRetainerActivity contract");
            }
            PresenterRetainerActivity activity = (PresenterRetainerActivity) getActivity();
            mDelegate = new PresenterBindingDelegate<>(activity.getPresenterRetainer(), this);
        }
        return mDelegate;
    }
}
