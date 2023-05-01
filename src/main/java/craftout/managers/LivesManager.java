package craftout.managers;

import craftout.components.BoxCollider;
import craftout.di.DependencyContainer;
import craftout.gameLevel.GameLevelManager;
import craftout.gameLevel.ILevelManagerListener;
import craftout.gameObjects.interfaces.IBallListener;
import craftout.physics.ColliderLayer;
import craftout.ui.liveWidget.ILiveListener;
import craftout.ui.liveWidget.ILiveObserver;

import java.util.ArrayList;
import java.util.List;

public class LivesManager implements ILiveObserver, ILevelManagerListener {

    public int getLivesCount() { return _liveCount; }

    public void addLive(){
        setLiveCount(getLivesCount() + 1);
    }

    public void removeLive(){
        setLiveCount(getLivesCount() - 1);
    }

    @Override
    public void addListener(ILiveListener listener) {
        _livesChangeListeners.add(listener);
    }

    @Override
    public void removeListener(ILiveListener listener) {
        _livesChangeListeners.remove(listener);
    }

    public void subscribeToLevelManager(GameLevelManager levelManager){
        levelManager.addListener(this);
    }

    private final int _startLives = 3;
    private int _liveCount = _startLives;

    private List<ILiveListener> _livesChangeListeners = new ArrayList<>();

    private void setLiveCount(int count){
        _liveCount = count;

        for(int i = 0; i < _livesChangeListeners.size(); i++)
            _livesChangeListeners.get(i).onLiveCountChanged(_liveCount);
    }

    @Override
    public void onLevelStarted() {
        setLiveCount(_startLives);
    }

    @Override
    public void onGameOver() {

    }
}
