package craftout.gameLevel;

import craftout.components.BoxCollider;
import craftout.di.DependencyContainer;
import craftout.gameObjects.*;
import craftout.gameObjects.base.GameObject;
import craftout.gameObjects.interfaces.IBallListener;
import craftout.managers.LivesManager;
import craftout.managers.ScoreManager;
import craftout.physics.ColliderLayer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GameLevelManager extends GameObject implements IBallListener, ILevelManagerObserver{

    @Override
    public void onCollided(BoxCollider otherCollider) {
        if (otherCollider.getLayer() == ColliderLayer.bottomLine) {
            _ball.destroy();
            _livesManager.removeLive();

            if (isGameover()) overTheGame();
            else spawnBall(true);
        }
    }

    @Override
    public void update() {
        super.update();

        if (_isGameInProgress == false && Input.isButtonPressed(Input.Button.FIRE))
            startLevel();
    }

    public GameLevelManager(DependencyContainer dependencyContainer){
        super(dependencyContainer);

        _dependencyContainer = dependencyContainer;

        _livesManager = dependencyContainer.getComponent(LivesManager.class.getSimpleName());
        _scoreManager = dependencyContainer.getComponent(ScoreManager.class.getSimpleName());

        startLevel();
    }

    private DependencyContainer _dependencyContainer;
    private GameLevelBuilder _builder;
    private LivesManager _livesManager;
    private ScoreManager _scoreManager;

    private List<Wall> _walls = new ArrayList<>();
    private List<Brick> _bricks = new ArrayList<>();
    private Ball _ball;
    private Paddle _paddle;

    private List<ILevelManagerListener> _eventListeners = new ArrayList<>();
    private boolean _isGameInProgress;

    private void startLevel(){

        buildLevel();
        for(int i = 0; i < _eventListeners.size(); i++)
            _eventListeners.get(i).onLevelStarted();

        _isGameInProgress = true;
    }

    private void buildLevel(){
        _builder = new GameLevelBuilder(_dependencyContainer);
        _walls = _builder.spawnWalls();
        _bricks = _builder.spawnBricks();
        _paddle = _builder.spawnPlayerPaddle();
        spawnBall(true);
    }

    private void spawnBall(boolean attach){
        _ball = _builder.spawnBall(_paddle.getY() - 20);
        _ball.addListener(this);
        _ball.addListener(_scoreManager);
    }

    private void overTheGame(){
        _isGameInProgress = false;

        clearLevel();
        for(int i = 0; i < _eventListeners.size(); i++)
            _eventListeners.get(i).onGameOver();
    }

    private void clearLevel(){
        for(int i = 0; i < _walls.size(); i++)
            _walls.get(i).destroy();

        for(int i = 0; i < _bricks.size(); i++)
            _bricks.get(i).destroy();

        _paddle.destroy();
    }

    private boolean isGameover(){
        return _livesManager.getLivesCount() <= 0;
    }

    @Override
    public void addListener(ILevelManagerListener listener) {
        _eventListeners.add(listener);
    }

    @Override
    public void removeListener(ILevelManagerListener listener) {
        _eventListeners.remove(listener);
    }

    @Override
    public void removeAllListeners() {
        _eventListeners = new ArrayList<>();
    }
}
