package craftout;

import craftout.gameObjects.base.GameObject;

import java.util.ArrayList;
import java.util.List;

public class GameLoop {

    public void addObject(GameObject gameObject){
        _gameObjects.add(gameObject);
    }

    public void update(){
        long frameStartTimeMs = System.currentTimeMillis();

        for(int i = 0; i < _gameObjects.size(); i++) {
            _gameObjects.get(i).update();
            _gameObjects.get(i).debugUpdate();
            _gameObjects.get(i).render();
        }

        for(int i = 0; i < _gameObjects.size(); i++) {
            if (_gameObjects.get(i).canBeDestroyed())
                _gameObjects.get(i).onDestroy();
        }

        _gameObjects.removeIf(x -> x.canBeDestroyed());

        long frameEndTimeMs = System.currentTimeMillis();
        long frameDiffMs = frameEndTimeMs - frameStartTimeMs;
        long chillTimeMs = _targetFrameTimeMs - frameDiffMs;

        if (chillTimeMs > 0) {
            try {
                Thread.sleep(chillTimeMs);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private List<GameObject> _gameObjects = new ArrayList<>();

    private final int _targetFps = 60;//todo: replace to settings
    private final int _targetFrameTimeMs = 1000 / _targetFps;
}
