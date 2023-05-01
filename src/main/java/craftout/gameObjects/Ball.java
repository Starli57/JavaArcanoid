package craftout.gameObjects;

import craftout.components.Image;
import craftout.components.Transform;
import craftout.di.DependencyContainer;
import craftout.gameObjects.base.GameObject;
import craftout.components.BoxCollider;
import craftout.gameObjects.interfaces.IBallListener;
import craftout.gameObjects.interfaces.IBallObserver;
import craftout.physics.ColliderLayer;
import craftout.physics.CollidersManager;
import craftout.physics.IColladable;
import craftout.settings.Ball.DefaultBallSettings;
import craftout.settings.GameWindowSettings;
import craftout.utils.MathUtils;
import craftout.utils.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Ball extends GameObject implements IColladable, IBallObserver {

    @Override
    public void update() {
        super.update();

        _transform.setPositionX(_transform.getX() + _moveDirectionX * _ballSettings.getMoveSpeed());
        _transform.setPositionY(_transform.getY() + _moveDirectionY * _ballSettings.getMoveSpeed());
    }

    public void onCollided(BoxCollider otherCollider) {

        for(int i = 0; i < _ballListeners.size(); i++)
            _ballListeners.get(i).onCollided(otherCollider);

        Vector2 normals = otherCollider.getNormals(_transform.getX() + _transform.getWidth() / 2,
                _transform.getY() + _transform.getHeight() / 2);

        Vector2 reflectedDir = MathUtils.reflect(_moveDirectionX, _moveDirectionY,
                normals.x, normals.y);

        if (otherCollider.getLayer() == ColliderLayer.paddle) {
            float hitT = (_transform.getX() - otherCollider.getLeftEdge()) / otherCollider.getWidth();
            hitT = hitT * 1.6f - 0.8f; //move value diapason to -0.8 - +0.8

            _moveDirectionX = hitT;
        }
        else {
            _moveDirectionX = reflectedDir.x;
        }

        _moveDirectionY = reflectedDir.y;
    }

    public Ball(DependencyContainer dependencyContainer, float x, float y){

        super(dependencyContainer);

        _gameWindow = dependencyContainer.getComponent(Window.class.getSimpleName());
        _windowSettings = dependencyContainer.getComponent(GameWindowSettings.class.getSimpleName());
        _ballSettings = dependencyContainer.getComponent(DefaultBallSettings.class.getSimpleName());
        _collidersManager = dependencyContainer.getComponent(CollidersManager.class.getSimpleName());

        _transform = new Transform(dependencyContainer, x, y, _ballSettings.getWidth(), _ballSettings.getHeight());
        _collider = new BoxCollider(dependencyContainer, _transform, this, ColliderLayer.ball);

        addComponent(_transform);
        addComponent(_collider);
        addComponent(new Image(dependencyContainer, _transform, SpriteType.BALL));

    }

    private Window _gameWindow;
    private GameWindowSettings _windowSettings;
    private DefaultBallSettings _ballSettings;
    private CollidersManager _collidersManager;

    private float _moveDirectionX;
    private float _moveDirectionY = -1;

    private Transform _transform;
    private BoxCollider _collider;
    private List<IBallListener> _ballListeners = new ArrayList<>();

    @Override
    public void addListener(IBallListener listener) {
        _ballListeners.add(listener);
    }

    @Override
    public void removeListener(IBallListener listener) {
        _ballListeners.remove(listener);
    }

    @Override
    public void removeAllListeners() {
        _ballListeners = new ArrayList<>();
    }

    @Override
    public void destroy() {
        super.destroy();
        removeAllListeners();
    }
}
