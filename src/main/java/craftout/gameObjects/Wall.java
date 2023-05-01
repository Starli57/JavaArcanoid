package craftout.gameObjects;

import craftout.components.Transform;
import craftout.di.DependencyContainer;
import craftout.gameObjects.base.GameObject;
import craftout.components.BoxCollider;
import craftout.physics.ColliderLayer;
import craftout.physics.IColladable;

public class Wall extends GameObject implements IColladable {

    public Wall(DependencyContainer dependencyContainer, ColliderLayer layer,
                float width, float height, float x, float y){

        super(dependencyContainer);

        Transform transform = new Transform(dependencyContainer, x, y, width, height);
        addComponent(transform);
        addComponent(new BoxCollider(dependencyContainer, transform, this, layer));

    }

    @Override
    public void onCollided(BoxCollider otherCollider) {

    }
}
