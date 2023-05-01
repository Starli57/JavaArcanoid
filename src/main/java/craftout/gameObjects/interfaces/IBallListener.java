package craftout.gameObjects.interfaces;

import craftout.components.BoxCollider;

public interface IBallListener {
    public void onCollided(BoxCollider otherCollider);
}
