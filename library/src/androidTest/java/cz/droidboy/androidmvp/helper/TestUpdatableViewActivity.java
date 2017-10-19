package cz.droidboy.androidmvp.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cz.droidboy.androidmvp.base.BasePresenter;
import cz.droidboy.androidmvp.base.UpdatableViewActivity;

/**
 * @author Jonáš Ševčík
 */

public final class TestUpdatableViewActivity extends UpdatableViewActivity<TestUpdatableViewActivity.TestPresenter>
    implements TestContract.TestUpdatableView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPresenter().bindView(this);
    }

    @Override
    public TestPresenter createPresenter() {
        return new TestPresenter();
    }

    public static class TestPresenter extends BasePresenter<TestContract.TestUpdatableView> implements TestContract
        .TestPresenter {

        private String text;

        @Override
        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String getText() {
            return text;
        }
    }
}