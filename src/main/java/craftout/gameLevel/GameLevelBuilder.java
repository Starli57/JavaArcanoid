package craftout.gameLevel;

import craftout.components.paddle.PaddleInput;
import craftout.di.DependencyContainer;
import craftout.gameObjects.*;
import craftout.gameObjects.interfaces.IBallListener;
import craftout.physics.ColliderLayer;
import craftout.settings.GameWindowSettings;

import java.util.ArrayList;
import java.util.List;

public class GameLevelBuilder {

    public GameLevelBuilder(DependencyContainer dependencyContainer){

        _dependencyContainer = dependencyContainer;
        _windowSettings = _dependencyContainer.getComponent(GameWindowSettings.class.getSimpleName());

        _levelData = new GameLevelData();
    }

    public List<Wall> spawnWalls(){
        float wallSmallSide = 4;
        float offset = 10;

        Wall leftWall = new Wall(_dependencyContainer,
                ColliderLayer.wall, wallSmallSide, _windowSettings.getWindowHeight() + offset * 2,
                -wallSmallSide , -offset);

        Wall topWall = new Wall(_dependencyContainer,
                ColliderLayer.wall, _windowSettings.getWindowWidth() + offset * 2, wallSmallSide,
                -offset,-wallSmallSide);

        Wall rightWall = new Wall(_dependencyContainer,
                ColliderLayer.wall, wallSmallSide, _windowSettings.getWindowHeight() + offset * 2,
                _windowSettings.getWindowWidth(), -offset);

        Wall bottomWall = new Wall(_dependencyContainer,
                ColliderLayer.bottomLine, _windowSettings.getWindowWidth() + offset * 2, wallSmallSide,
                -offset,_windowSettings.getWindowHeight());

        List<Wall> walls = new ArrayList<>();
        walls.add(leftWall);
        walls.add(topWall);
        walls.add(rightWall);
        walls.add(bottomWall);
        return walls;
    }

    public List<Brick> spawnBricks(){
        List<Brick> bricks = new ArrayList<>();
        for(int i = 0; i < _levelData.brickPositions.size(); i++) {
            bricks.add(new Brick(_dependencyContainer, ColliderLayer.brick, 32, 16,
                    _levelData.brickPositions.get(i).x, _levelData.brickPositions.get(i).y));
        }
        return bricks;
    }

    public Paddle spawnPlayerPaddle(){
        Paddle playerPaddle = new Paddle(_dependencyContainer);
        playerPaddle.addComponent(new PaddleInput(_dependencyContainer, playerPaddle.getInputData()));
        return playerPaddle;
    }

    public Ball spawnBall(float y){
        return new Ball(_dependencyContainer, _windowSettings.getWindowWidth() / 2f, y);
    }

    private DependencyContainer _dependencyContainer;
    private GameWindowSettings _windowSettings;
    private GameLevelData _levelData;
}
