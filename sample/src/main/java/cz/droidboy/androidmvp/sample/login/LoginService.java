package cz.droidboy.androidmvp.sample.login;

import android.os.Handler;
import android.util.Log;

public class LoginService {
    public void login(final String username, final String password, final LoginCallback callback) {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Log.e(LoginService.class.getSimpleName(), "Prolonging login failed", e);
                }
                if ("john".equals(username) && "doe".equals(password)) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.loginSuccess();
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.loginFailure();
                        }
                    });
                }
            }
        }).start();
    }

    interface LoginCallback {
        void loginSuccess();

        void loginFailure();
    }
}