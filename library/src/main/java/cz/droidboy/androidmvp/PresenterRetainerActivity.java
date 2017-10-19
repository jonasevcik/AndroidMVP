package cz.droidboy.androidmvp;

/**
 * @author Jonáš Ševčík
 */

public interface PresenterRetainerActivity {

    Object onRetainCustomNonConfigurationInstance();

    PresenterRetainer getPresenterRetainer();
}
