package craftout.physics;

import craftout.components.BoxCollider;

import java.util.ArrayList;
import java.util.List;

public class CollidersManager {
    public BoxCollider checkCollision(BoxCollider collider){
        for(int i = 0; i < _colliders.size(); i++){
            if (_colliders.get(i) == collider) continue;

            if (isOverlap(collider, _colliders.get(i)))
                return _colliders.get(i);
        }

        return null;
    }

    public BoxCollider checkCollisionTranslated(float x, float y, float width, float height, BoxCollider collider){
        for(int i = 0; i < _colliders.size(); i++){
            if (_colliders.get(i) == collider) continue;

            if (isOverlap(x, x + width, y, y + height, _colliders.get(i)))
                return _colliders.get(i);
        }

        return null;
    }

    //todo: add physics raycast

    public void addCollider(BoxCollider collider){
        _colliders.add(collider);
    }

    public void removeCollider(BoxCollider collider){
        _colliders.remove(collider);
    }

    private List<BoxCollider> _colliders = new ArrayList<BoxCollider>();

    private boolean isOverlap(BoxCollider a, BoxCollider b){

        if (a.getRightEdge() < b.getLeftEdge()) return false;
        if (a.getLeftEdge() > b.getRightEdge()) return false;
        if (a.getTopEdge() > b.getBottomEdge()) return false;
        if (a.getBottomEdge() < b.getTopEdge()) return false;

        return true;
    }

    private boolean isOverlap(float leftX, float rightX, float topY, float bottomY, BoxCollider collider){

        if (rightX < collider.getLeftEdge()) return false;
        if (leftX > collider.getRightEdge()) return false;
        if (topY > collider.getBottomEdge()) return false;
        if (bottomY < collider.getTopEdge()) return false;

        return true;
    }
}
