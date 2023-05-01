package craftout.gameLevel;


public interface ILevelManagerObserver {
    public void addListener(ILevelManagerListener listener);
    public void removeListener(ILevelManagerListener listener);
    public void removeAllListeners();
}
