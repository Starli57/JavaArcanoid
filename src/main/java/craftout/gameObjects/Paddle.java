package craftout.gameObjects;

import craftout.components.*;
import craftout.components.paddle.PaddleInputData;
import craftout.di.DependencyContainer;
import craftout.gameObjects.base.GameObject;
import craftout.physics.ColliderLayer;
import craftout.physics.CollidersManager;
import craftout.physics.IColladable;
import craftout.settings.GameWindowSettings;
import craftout.settings.Paddle.DefaultPaddleSettings;
import craftout.settings.Paddle.IPaddleSettings;

public class Paddle extends GameObject implements IColladable {

    public float getX() { return _transform.getX(); }
    public float getY() { return _transform.getY(); }

    public PaddleInputData getInputData(){ return _inputData; }

    public Paddle(DependencyContainer dependencyContainer) {
        super(dependencyContainer);

        _gameWindow = dependencyContainer.getComponent(Window.class.getSimpleName());

        _windowSettings = dependencyContainer.getComponent(GameWindowSettings.class.getSimpleName());
        _paddleSettings = dependencyContainer.getComponent(DefaultPaddleSettings.class.getSimpleName());

        _collidersManager = dependencyContainer.getComponent(CollidersManager.class.getSimpleName());

        float centerX = _windowSettings.getWindowWidth() * _paddleSettings.getXAspect();
        float width = _paddleSettings.getSegmentWidth() * (2 + _paddleSettings.getMidSegmentsCount());

        float x = centerX - width / 2f;
        float y = _windowSettings.getWindowHeight() * _paddleSettings.getYAspect() - _paddleSettings.getSegmentHeight();

        _transform = new Transform(dependencyContainer, x, y, width, _paddleSettings.getSegmentHeight());
        _collider = new BoxCollider(dependencyContainer, _transform, this, ColliderLayer.paddle);
        _collider.setSilentCollision(true);

        addComponent(_transform);
        addComponent(_collider);
        addComponent(new SegmentedImage(dependencyContainer, _gameWindow, _paddleSettings, _transform));
    }

    @Override
    public void update(){
        super.update();

        updatePosition();
    }

    private Window _gameWindow;
    private GameWindowSettings _windowSettings;
    private IPaddleSettings _paddleSettings;
    private CollidersManager _collidersManager;

    private Transform _transform;
    private BoxCollider _collider;

    private PaddleInputData _inputData = new PaddleInputData();

    private void updatePosition(){
        float xVelocity = _paddleSettings.getMoveSpeed() * _inputData.getXInput();
        if (xVelocity == 0) return;

        float targetX = getX() + xVelocity;
        float targetY = getY();

        BoxCollider otherCollider = _collidersManager.checkCollisionTranslated(
                targetX, targetY, _transform.getWidth(), _transform.getHeight(), _collider);

        if (otherCollider != null && otherCollider.getLayer() == ColliderLayer.wall){
            if (xVelocity > 0) targetX = otherCollider.getLeftEdge() - _transform.getWidth();
            else if (xVelocity < 0) targetX = otherCollider.getRightEdge();
        }

        _transform.setPositionX(targetX);
    }

    @Override
    public void onCollided(BoxCollider otherCollider) {

    }
}
