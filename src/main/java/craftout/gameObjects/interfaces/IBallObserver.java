package craftout.gameObjects.interfaces;

public interface IBallObserver {
    public void addListener(IBallListener listener);
    public void removeListener(IBallListener listener);
    public void removeAllListeners();
}
