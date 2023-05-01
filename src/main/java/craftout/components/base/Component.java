package craftout.components.base;

import craftout.di.DependencyContainer;

public class Component {

    public Component(DependencyContainer dependencyContainer){
        this.dependencyContainer = dependencyContainer;
    }

    public void update(){}
    public void debugUpdate(){}
    public void render(){}
    public void onDestroy(){}

    protected DependencyContainer dependencyContainer;
}
