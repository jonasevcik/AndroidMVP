package cz.droidboy.androidmvp.helper;

import cz.droidboy.androidmvp.Presenter;
import cz.droidboy.androidmvp.UpdatableView;

/**
 * @author Jonáš Ševčík
 */

interface TestContract {

    interface TestUpdatableView extends UpdatableView {}

    interface TestPresenter extends Presenter<TestUpdatableView> {
        void setText(String text);
        String getText();
    }
}
