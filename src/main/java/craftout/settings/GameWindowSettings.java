package craftout.settings;

public class GameWindowSettings {

    public int getWindowWidth(){
        return _windowWidth;
    }

    public int getWindowHeight(){
        return _windowHeight;
    }

    public String getWindowTitle(){
        return _windowTitle;
    }

    private final int _windowWidth = 640;
    private final int _windowHeight = 480;
    private final String _windowTitle = "Craftout";
}
