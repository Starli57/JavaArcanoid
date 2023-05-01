package craftout;

import craftout.di.DependencyContainer;
import craftout.gameLevel.GameLevelManager;
import craftout.managers.LivesManager;
import craftout.managers.ScoreManager;
import craftout.physics.CollidersManager;
import craftout.settings.Ball.DefaultBallSettings;
import craftout.settings.GameWindowSettings;
import craftout.settings.Paddle.DefaultPaddleSettings;
import craftout.ui.SceneUiManager;
import craftout.ui.liveWidget.LiveCountDisplay;
import craftout.ui.scoreWidget.GameScoreDisplay;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            GameLoop gameLoop = new GameLoop();
            DependencyContainer dependencyContainer = new DependencyContainer();

            GameWindowSettings windowSettings = new GameWindowSettings();

            dependencyContainer.addComponent(gameLoop);
            dependencyContainer.addComponent(windowSettings);

            dependencyContainer.addComponent(new DefaultPaddleSettings());
            dependencyContainer.addComponent(new DefaultBallSettings());
            dependencyContainer.addComponent(new CollidersManager());

            LivesManager livesManager = new LivesManager();
            ScoreManager scoreManager = new ScoreManager();

            dependencyContainer.addComponent(livesManager);
            dependencyContainer.addComponent(scoreManager);

            Window window = new Window(
                    windowSettings.getWindowWidth(),
                    windowSettings.getWindowHeight(),
                    windowSettings.getWindowTitle());

            dependencyContainer.addComponent(window);

            GameLevelManager gameLevelManager = new GameLevelManager(dependencyContainer);
            dependencyContainer.addComponent(gameLevelManager);

            livesManager.subscribeToLevelManager(gameLevelManager);
            scoreManager.subscribeToLevelManager(gameLevelManager);

            dependencyContainer.addComponent(new SceneUiManager(dependencyContainer));

            while (window.isOpen()) {
                gameLoop.update();
                window.draw();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
