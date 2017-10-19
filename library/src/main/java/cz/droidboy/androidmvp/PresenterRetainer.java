package cz.droidboy.androidmvp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jonáš Ševčík
 */

public class PresenterRetainer {

    private final Map<String, Presenter> mPresenterCache = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <P extends Presenter> P findPresenter(String tag) {
        return (P) mPresenterCache.get(tag);
    }

    public <P extends Presenter> void addPresenter(P presenter, String tag) {
        mPresenterCache.put(tag, presenter);
    }
}