package craftout.physics;

import craftout.components.BoxCollider;

public interface IColladable {
    public void onCollided(BoxCollider otherCollider);
}
