package craftout.ui.scoreWidget;

import craftout.gameObjects.interfaces.IBallListener;

public interface IScoreObserver {
    public void addListener(IScoreListener listener);
    public void removeListener(IScoreListener listener);
    public void removeAllListeners();
}
