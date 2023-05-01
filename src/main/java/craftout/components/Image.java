package craftout.components;

import craftout.components.base.Component;
import craftout.di.DependencyContainer;

public class Image extends Component {

    public Image(DependencyContainer dependencyContainer, Transform transform, SpriteType spriteType){

        super(dependencyContainer);

        _transform = transform;
        _window = dependencyContainer.getComponent(Window.class.getSimpleName());

        createSprite(spriteType, _transform.getX(), _transform.getY());
    }

    public void onDestroy(){

        removeSprite();
    }

    public void render(){

        moveSprite(_transform.getX(), _transform.getY());
    }

    private Transform _transform;
    private Window _window;

    private int _spriteId;

    private void createSprite(SpriteType spriteType, float x, float y){

        _spriteId = _window.createSprite(spriteType, x, y);
    }

    private void removeSprite(){

        _window.removeSprite(_spriteId);
    }

    private void moveSprite(float x, float y){

        _window.moveSprite(_spriteId, x, y);
    }
}
