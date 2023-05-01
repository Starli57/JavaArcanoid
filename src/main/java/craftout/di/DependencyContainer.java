package craftout.di;

import java.util.HashMap;

public class DependencyContainer {

    //todo: it would be better to use <status> return type instead of int
    public <T> int addComponent(T component){
        String key = component.getClass().getSimpleName();
        if (_components.containsKey(key)) return -1;

        _components.put(key, new DependencyWrapper(component));
        return 0;
    }

    //todo: it would be better to use other parameter type instead of "String"
    //todo: remove casts in return
    public <T> T getComponent(String classType){
        if (_components.containsKey(classType) == false)
            throw new RuntimeException(classType + " component cant be found in dependency container");

        return (T) _components.get(classType).getComponent();
    }

    private HashMap<String, DependencyWrapper> _components = new HashMap<String, DependencyWrapper>();
}
