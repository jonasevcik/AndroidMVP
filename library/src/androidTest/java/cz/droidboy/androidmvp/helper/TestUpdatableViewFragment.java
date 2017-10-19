package cz.droidboy.androidmvp.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cz.droidboy.androidmvp.base.UpdatableViewFragment;


/**
 * @author Jonáš Ševčík
 */

public class TestUpdatableViewFragment extends UpdatableViewFragment<TestUpdatableViewActivity.TestPresenter>
    implements TestContract.TestUpdatableView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getPresenter().bindView(this);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public TestUpdatableViewActivity.TestPresenter createPresenter() {
        TestUpdatableViewActivity.TestPresenter presenter = new TestUpdatableViewActivity.TestPresenter();
        presenter.setText(getTag());
        return presenter;
    }
}