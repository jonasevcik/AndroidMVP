package cz.droidboy.androidmvp.sample.login;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import cz.droidboy.androidmvp.Presenter;
import cz.droidboy.androidmvp.UpdatableView;

/**
 * @author Jonáš Ševčík
 */

public interface LoginContract {

    interface View extends UpdatableView {
        void showUsernameError(@StringRes int msg);

        void showPasswordError(@StringRes int msg);

        void showLoginError(@StringRes int msg);

        void startMainActivity();
    }

    interface UserActionsListener extends Presenter<View> {
        void onLoginClicked(@NonNull String username, @NonNull String password);
    }
}