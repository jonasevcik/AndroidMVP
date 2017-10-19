package cz.droidboy.androidmvp;

/**
 * @author Jonáš Ševčík
 */

public interface Presenter<V extends UpdatableView> {

    void bindView(V view);

    void unbindView();

    boolean isViewBound();

    V getUpdatableView();
}