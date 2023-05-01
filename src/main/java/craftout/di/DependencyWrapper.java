package craftout.di;

public class DependencyWrapper<T> {

    public T getComponent() { return _component; }

    public DependencyWrapper(T component){
        _component = component;
    }

    private T _component;
}
