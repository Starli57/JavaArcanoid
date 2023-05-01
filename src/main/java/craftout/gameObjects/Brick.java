package craftout.gameObjects;

import craftout.components.Image;
import craftout.components.Transform;
import craftout.di.DependencyContainer;
import craftout.gameObjects.base.GameObject;
import craftout.components.BoxCollider;
import craftout.physics.ColliderLayer;
import craftout.physics.IColladable;
import craftout.settings.GameWindowSettings;

public class Brick extends GameObject implements IColladable {

    public Brick(DependencyContainer dependencyContainer, ColliderLayer layer,
                 float width, float height, float x, float y) {

        super(dependencyContainer);

        _gameWindow = dependencyContainer.getComponent(Window.class.getSimpleName());
        _windowSettings = dependencyContainer.getComponent(GameWindowSettings.class.getSimpleName());

        _transform = new Transform(dependencyContainer, x, y, width, height);
        _collider = new BoxCollider(dependencyContainer, _transform, this, layer);

        addComponent(_transform);
        addComponent(_collider);
        addComponent(new Image(dependencyContainer, _transform, SpriteType.BRICK));
    }

    @Override
    public void onCollided(BoxCollider otherCollider) {
        super.destroy();
    }

    public void onDestroy(){
        super.onDestroy();

        _transform = null;
        _collider = null;
    }

    private Window _gameWindow;
    private GameWindowSettings _windowSettings;

    private Transform _transform;
    private BoxCollider _collider;
}