package craftout.managers;

import craftout.components.BoxCollider;
import craftout.di.DependencyContainer;
import craftout.gameLevel.GameLevelManager;
import craftout.gameLevel.ILevelManagerListener;
import craftout.gameObjects.interfaces.IBallListener;
import craftout.physics.ColliderLayer;
import craftout.ui.scoreWidget.IScoreListener;
import craftout.ui.scoreWidget.IScoreObserver;

import java.util.ArrayList;
import java.util.List;

public class ScoreManager implements IBallListener, ILevelManagerListener, IScoreObserver {

    public int getScoreCount() { return _score; }

    @Override
    public void onCollided(BoxCollider otherCollider) {
        if (otherCollider.getLayer() == ColliderLayer.brick){
            setScore(getScoreCount() + 1);
        }
    }

    public void subscribeToLevelManager(GameLevelManager levelManager){
        levelManager.addListener(this);
    }

    private int _score;
    private List<IScoreListener> _scoreListeners = new ArrayList<>();

    @Override
    public void addListener(IScoreListener listener) {
        _scoreListeners.add(listener);
    }

    @Override
    public void removeListener(IScoreListener listener) {
        _scoreListeners.remove(listener);
    }

    @Override
    public void removeAllListeners() {
        _scoreListeners = new ArrayList<>();
    }

    private void setScore(int score){
        _score = score;

        for(int i = 0; i < _scoreListeners.size(); i++)
            _scoreListeners.get(i).onScoreChanged(_score);
    }

    @Override
    public void onLevelStarted() {
        setScore(0);
    }

    @Override
    public void onGameOver() {

    }
}
