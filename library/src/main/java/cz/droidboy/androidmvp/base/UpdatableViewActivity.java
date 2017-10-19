package cz.droidboy.androidmvp.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cz.droidboy.androidmvp.Presenter;
import cz.droidboy.androidmvp.PresenterBindingDelegate;

/**
 * @author Jonáš Ševčík
 */

public abstract class UpdatableViewActivity<P extends Presenter> extends AppCompatActivity implements
    PresenterBindingDelegate.PresenterBindingCallback<P> {

    private PresenterBindingDelegate<P> mDelegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getBindingDelegate().onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        getBindingDelegate().onStart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getBindingDelegate().onSaveInstanceState(outState);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return getBindingDelegate().onRetainCustomNonConfigurationInstance();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        getBindingDelegate().onDestroyView();
    }

    public P getPresenter() {
        return getBindingDelegate().getPresenter();
    }

    @NonNull
    private PresenterBindingDelegate<P> getBindingDelegate() {
        if (mDelegate == null) {
            mDelegate = new PresenterBindingDelegate<>(this, this);
        }
        return mDelegate;
    }
}