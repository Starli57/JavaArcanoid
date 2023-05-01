package craftout.gameObjects.base;

import craftout.GameLoop;
import craftout.components.base.Component;
import craftout.di.DependencyContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class GameObject {

    public boolean canBeDestroyed() { return _canBeDestroyed; }

    public GameObject(DependencyContainer dependencyContainer) {
        this.dependencyContainer = dependencyContainer;

        getGameLoop().addObject(this);
    }

    public void destroy(){
        _canBeDestroyed = true;
    }

    public void onDestroy(){
        if (_canBeDestroyed == false) return;

        for(int i = 0; i < components.size(); i++)
            components.get(i).onDestroy();

        components.clear();
    }

    public void update(){
        for(int i = 0; i < components.size(); i++)
            components.get(i).update();
    }

    public void debugUpdate(){
        for(int i = 0; i < components.size(); i++)
            components.get(i).debugUpdate();
    }

    public void render(){
        for(int i = 0; i < components.size(); i++)
            components.get(i).render();
    }

    public void addComponent(Component component){

        components.add(component);
    }

    public void removeComponent(Component component){

        components.remove(component);
    }

    //todo: use something better than string parameter
    public Component getComponent(String componentClass){

        return null;
    }

    protected DependencyContainer dependencyContainer;

    protected List<Component> components = new ArrayList<>();

    private boolean _canBeDestroyed = false;

    private GameLoop getGameLoop() {
        if (_gameLoop == null) {
            _gameLoop = this.dependencyContainer.getComponent(
                    GameLoop.class.getSimpleName());
        }
        return _gameLoop;
    }

    private GameLoop _gameLoop;
}
