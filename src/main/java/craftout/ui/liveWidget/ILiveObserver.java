package craftout.ui.liveWidget;

public interface ILiveObserver {
    public void addListener(ILiveListener listener);
    public void removeListener(ILiveListener listener);
}
