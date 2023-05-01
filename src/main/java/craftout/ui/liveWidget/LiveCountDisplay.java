package craftout.ui.liveWidget;

import craftout.di.DependencyContainer;
import craftout.gameLevel.GameLevelManager;
import craftout.managers.LivesManager;

public class LiveCountDisplay implements ILiveListener {
    @Override
    public void onLiveCountChanged(int count) {
        updateLiveDisplay(count);
    }

    public LiveCountDisplay(DependencyContainer dependencyContainer, float x, float y){
        _dependencyContainer = dependencyContainer;

        _window = _dependencyContainer.getComponent(Window.class.getSimpleName());
        _livesManager = dependencyContainer.getComponent(LivesManager.class.getSimpleName());
        _livesManager.addListener(this);

        createLiveDisplay(x,y);
    }

    private int _textId;

    private DependencyContainer _dependencyContainer;
    private Window _window;
    private LivesManager _livesManager;

    private int _size = 20;

    private void createLiveDisplay(float x, float y){
        _textId = _window.createText(formatLivesString(_livesManager.getLivesCount()), _size, x, y);
    }

    private void updateLiveDisplay(int count){
        _window.updateText(_textId, formatLivesString(count));
    }

    private String formatLivesString(int count){
        return "Lives: " + count;
    }
}
