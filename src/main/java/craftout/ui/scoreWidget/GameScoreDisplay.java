package craftout.ui.scoreWidget;

import craftout.di.DependencyContainer;
import craftout.gameLevel.GameLevelManager;
import craftout.managers.ScoreManager;

public class GameScoreDisplay implements IScoreListener{

    public GameScoreDisplay(DependencyContainer dependencyContainer, float x, float y){
        _dependencyContainer = dependencyContainer;

        _window = _dependencyContainer.getComponent(Window.class.getSimpleName());
        _scoreManager = dependencyContainer.getComponent(ScoreManager.class.getSimpleName());
        _scoreManager.addListener(this);

        createScoreDisplay(x,y);
    }

    private int _textId;

    private DependencyContainer _dependencyContainer;
    private Window _window;
    private ScoreManager _scoreManager;

    private int _size = 20;

    private void createScoreDisplay(float x, float y){
        _textId = _window.createText(formatScoreString(_scoreManager.getScoreCount()), _size, x, y);
    }

    private void updateScoreDisplay(int count){

        _window.updateText(_textId, formatScoreString(count));
    }

    private String formatScoreString(int count){
        return "Score: " + count;
    }

    @Override
    public void onScoreChanged(int score) {
        updateScoreDisplay(score);
    }
}
