package craftout.components;

import craftout.components.base.Component;
import craftout.di.DependencyContainer;
import craftout.physics.ColliderLayer;
import craftout.physics.CollidersManager;
import craftout.physics.IColladable;
import craftout.utils.Vector2;

public class BoxCollider extends Component {
    public float getLeftEdge() { return _transform.getX(); }
    public float getTopEdge() { return _transform.getY(); }
    public float getRightEdge() { return _transform.getX() + getWidth(); }
    public float getBottomEdge() { return _transform.getY() + getHeight(); }

    public float getWidth() { return _transform.getWidth(); }
    public float getHeight() { return _transform.getHeight(); }

    public IColladable getOwner() { return _owner; }
    public ColliderLayer getLayer() { return _layer; }

    //todo: add cache for normals
    //todo: change algorithm to calculate normals (now it works only for rectangles)
    public Vector2 getNormals(float x, float y){
        float distanceLeft = Math.abs(getLeftEdge() - x);
        float distanceTop = Math.abs(getTopEdge() - y);
        float distanceRight = Math.abs(getRightEdge() - x);
        float distanceBottom = Math.abs(getBottomEdge() - y);

        if (distanceLeft < distanceTop && distanceLeft < distanceRight && distanceLeft < distanceBottom) return new Vector2(-1, 0);
        if (distanceTop < distanceLeft && distanceTop < distanceRight && distanceTop < distanceBottom) return new Vector2(0, 1);
        if (distanceRight < distanceLeft && distanceRight < distanceTop && distanceRight < distanceBottom) return new Vector2(1, 0);
        else return new Vector2(0, -1);
    }

    public void setSilentCollision(boolean isSilent){

        _isSilentCollision = isSilent;
    }

    public BoxCollider(DependencyContainer container, Transform transform, IColladable owner, ColliderLayer layer) {

        super(container);

        _window = container.getComponent(Window.class.getSimpleName());
        _collidersManager = container.getComponent(CollidersManager.class.getSimpleName());

        _transform = transform;
        _owner = owner;
        _layer = layer;

        _prevPositionX = _transform.getX();
        _prevPositionY = _transform.getY();

        _collidersManager.addCollider(this);
    }

    public void onDestroy(){
        _collidersManager.removeCollider(this);
    }

    public void update(){

        if (_isSilentCollision == false) CheckCollisionOnPositionChange();
    }

    private void CheckCollisionOnPositionChange(){
        if (_transform.getX() != _prevPositionX || _transform.getY() != _prevPositionY){
            BoxCollider otherCollider = _collidersManager.checkCollision(this);
            if (otherCollider != null) {
                _owner.onCollided(otherCollider);
                otherCollider.getOwner().onCollided(this);
            }

            _prevPositionX = _transform.getX();
            _prevPositionY = _transform.getY();
        }
    }

    public void debugUpdate(){

        drawColliderDebug();
    }

    public void drawColliderDebug(){
        _window.debugDrawLine(getLeftEdge(), getBottomEdge(), getLeftEdge(), getTopEdge());
        _window.debugDrawLine(getLeftEdge(), getBottomEdge(), getRightEdge(), getBottomEdge());
        _window.debugDrawLine(getLeftEdge(), getTopEdge(), getRightEdge(), getTopEdge());
        _window.debugDrawLine(getRightEdge(), getTopEdge(), getRightEdge(), getBottomEdge());
    }

    private IColladable _owner;

    private float _prevPositionX;
    private float _prevPositionY;

    private Window _window;

    private CollidersManager _collidersManager;
    private Transform _transform;

    private ColliderLayer _layer;

    private boolean _isSilentCollision = false;
}
