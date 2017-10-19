package cz.droidboy.androidmvp.base;

import cz.droidboy.androidmvp.Presenter;
import cz.droidboy.androidmvp.UpdatableView;

/**
 * @author Jonáš Ševčík
 */

public class BasePresenter<V extends UpdatableView> implements Presenter<V> {

    private V mView;

    @Override
    public void bindView(V view) {
        mView = view;
    }

    @Override
    public void unbindView() {
        mView = null;
    }

    @Override
    public boolean isViewBound() {
        return mView != null;
    }

    @Override
    public V getUpdatableView() {
        return mView;
    }
}