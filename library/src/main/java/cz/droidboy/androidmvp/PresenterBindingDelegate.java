package cz.droidboy.androidmvp;

import java.util.UUID;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * @author Jonáš Ševčík
 */

public class PresenterBindingDelegate<P extends Presenter> {

    private static final String PRESENTER_TAG = "presenter_tag";

    private String mPresenterTag;
    private PresenterRetainer mPresenterRetainer;
    private PresenterBindingCallback<P> mBindingCallback;

    /**
     * Intended to be used with UpdatableViewFragments.
     *
     * @param presenterRetainer which is being persisted inside parent Activity
     * @param bindingCallback
     */
    public PresenterBindingDelegate(PresenterRetainer presenterRetainer, PresenterBindingCallback<P> bindingCallback) {
        if (presenterRetainer == null) {
            throw new NullPointerException("presenterRetainer == null");
        }
        if (bindingCallback == null) {
            throw new NullPointerException("bindingCallback == null");
        }

        mPresenterRetainer = presenterRetainer;

        mBindingCallback = bindingCallback;
    }

    /**
     * Intended to be used with UpdatableViewActivity
     *
     * @param activity
     * @param bindingCallback
     */
    public PresenterBindingDelegate(FragmentActivity activity, PresenterBindingCallback<P> bindingCallback){
        if (activity == null) {
            throw new NullPointerException("activity == null");
        }
        if (bindingCallback == null) {
            throw new NullPointerException("bindingCallback == null");
        }

        if (activity.getLastCustomNonConfigurationInstance() == null) {
            mPresenterRetainer = new PresenterRetainer();
        } else {
            mPresenterRetainer = (PresenterRetainer) activity.getLastCustomNonConfigurationInstance();
        }

        mBindingCallback = bindingCallback;
    }

    @SuppressWarnings("unchecked")
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mPresenterTag = UUID.randomUUID().toString();
        } else {
            mPresenterTag = savedInstanceState.getString(PRESENTER_TAG);
            if (mPresenterTag == null) {
                throw new IllegalStateException("Bundle from onSaveInstanceState() didn't contain Fragment's TAG");
            }
        }

        //Check if the presenter is already present (ie if the activity is recreated due
        // to orientation change).
        P presenter = mPresenterRetainer.findPresenter(mPresenterTag);
        if (presenter == null) { //cold start or process was recreated
            //Create, set up and add the presenter.
            presenter = mBindingCallback.createPresenter();
            mPresenterRetainer.addPresenter(presenter, mPresenterTag);
            mBindingCallback = null; //don't hold reference, it might be an Activity object
        }
    }

    public void onStart() {
        if (!getPresenter().isViewBound()) {
            throw new IllegalStateException("UpdatableView is not bound. Bind it in Fragment::onCreateView or in " +
                "Activity::onCreate.");
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putString(PRESENTER_TAG, mPresenterTag);
    }

    public PresenterRetainer onRetainCustomNonConfigurationInstance() {
        return mPresenterRetainer;
    }

    public void onDestroyView() {
        getPresenter().unbindView();
    }

    public P getPresenter() {
        return mPresenterRetainer.findPresenter(mPresenterTag);
    }

    public interface PresenterBindingCallback<P extends Presenter> {
        P createPresenter();
    }
}
