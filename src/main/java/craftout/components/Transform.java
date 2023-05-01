package craftout.components;

import craftout.components.base.Component;
import craftout.di.DependencyContainer;

public class Transform extends Component {

    public void setPosition(float x, float y){
        _positionX = x;
        _positionY = y;
    }

    public void setPositionX(float x){ _positionX = x; }
    public float getX(){ return _positionX; }

    public void setPositionY(float y){
        _positionY = y;
    }
    public float getY(){
        return _positionY;
    }

    public float getWidth(){
        return _width;
    }
    public float getHeight(){
        return _height;
    }

    public Transform(DependencyContainer dependencyContainer, float x, float y, float width, float height){

        super(dependencyContainer);

        _positionX = x;
        _positionY = y;

        _width = width;
        _height = height;
    }

    private float _positionX;
    private float _positionY;

    private float _width;
    private float _height;
}
