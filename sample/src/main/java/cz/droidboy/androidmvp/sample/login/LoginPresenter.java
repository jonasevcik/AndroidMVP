package cz.droidboy.androidmvp.sample.login;

import android.support.annotation.NonNull;

import cz.droidboy.androidmvp.base.BasePresenter;
import cz.droidboy.androidmvp.sample.R;

/**
 * @author Jonáš Ševčík
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.UserActionsListener {

    private LoginService service;

    public void setService(LoginService service) {
        this.service = service;
    }

    @Override
    public void onLoginClicked(@NonNull String username, @NonNull String password) {
        if (username.isEmpty()) {
            if (isViewBound()) {
                getUpdatableView().showUsernameError(R.string.username_error);
            }
            return;
        }
        if (password.isEmpty()) {
            if (isViewBound()) {
                getUpdatableView().showPasswordError(R.string.password_error);
            }
            return;
        }

        service.login(username, password, new LoginService.LoginCallback() {
            @Override
            public void loginSuccess() {
                if (isViewBound()) {
                    getUpdatableView().startMainActivity();
                }
            }

            @Override
            public void loginFailure() {
                if (isViewBound()) {
                    getUpdatableView().showLoginError(R.string.login_failed);
                }
            }
        });
    }
}