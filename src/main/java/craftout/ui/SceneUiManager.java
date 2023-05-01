package craftout.ui;

import craftout.di.DependencyContainer;
import craftout.gameLevel.GameLevelManager;
import craftout.gameLevel.ILevelManagerListener;
import craftout.settings.GameWindowSettings;
import craftout.ui.liveWidget.ILiveListener;
import craftout.ui.liveWidget.LiveCountDisplay;
import craftout.ui.scoreWidget.GameScoreDisplay;

public class SceneUiManager implements ILevelManagerListener {
    public SceneUiManager(DependencyContainer dependencyContainer){

        _dependencyContainer = dependencyContainer;

        _window = _dependencyContainer.getComponent(Window.class.getSimpleName());
        _windowSettings = _dependencyContainer.getComponent(GameWindowSettings.class.getSimpleName());

        GameLevelManager levelManager = _dependencyContainer.getComponent(GameLevelManager.class.getSimpleName());
        LiveCountDisplay liveCountDisplay = new LiveCountDisplay(_dependencyContainer, 10, 10);
        GameScoreDisplay scoreDisplay = new GameScoreDisplay(_dependencyContainer, 140, 10);

        levelManager.addListener(this);
    }

    private DependencyContainer _dependencyContainer;
    private Window _window;
    private GameWindowSettings _windowSettings;

    private int _gameOverTextId;
    private final String _gameOverText = "Game over\nPress space for restart";

    private void showGameOver(){
        _gameOverTextId = _window.createText(_gameOverText,
                20, 20, _windowSettings.getWindowHeight() / 2);
    }

    private void hideGameOver(){
        if (_window.isValidText(_gameOverTextId))
            _window.removeText(_gameOverTextId);
    }

    @Override
    public void onLevelStarted() {
        hideGameOver();
    }

    @Override
    public void onGameOver() {
        showGameOver();
    }
}
