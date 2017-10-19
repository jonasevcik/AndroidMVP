package cz.droidboy.androidmvp.sample.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cz.droidboy.androidmvp.base.UpdatableViewActivity;

import cz.droidboy.androidmvp.sample.R;
import cz.droidboy.androidmvp.sample.main.MainActivity;

import static android.widget.Toast.LENGTH_SHORT;


public class LoginActivity extends UpdatableViewActivity<LoginContract.UserActionsListener> implements LoginContract.View {

    private EditText mUsernameView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUsernameView = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);

        getPresenter().bindView(this);
    }

    @Override
    public LoginContract.UserActionsListener createPresenter() {
        LoginPresenter presenter = new LoginPresenter();
        initPresenter(presenter);
        return presenter;
    }

    private void initPresenter(LoginPresenter presenter) {
        presenter.setService(new LoginService());
    }

    public void onLoginClicked(View view) {
        getPresenter().onLoginClicked(mUsernameView.getText().toString(),
            mPasswordView.getText().toString());
    }

    @Override
    public void showUsernameError(@StringRes int msg) {
        mUsernameView.setError(getString(msg));
    }

    @Override
    public void showPasswordError(@StringRes int msg) {
        mPasswordView.setError(getString(msg));
    }

    @Override
    public void showLoginError(@StringRes int msg) {
        Toast.makeText(this, msg, LENGTH_SHORT).show();
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}