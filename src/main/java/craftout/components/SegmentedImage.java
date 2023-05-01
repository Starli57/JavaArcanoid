package craftout.components;

import craftout.components.base.Component;
import craftout.di.DependencyContainer;
import craftout.settings.SegmentSprite.ISegmentSpriteSettings;

import java.util.ArrayList;
import java.util.List;

public class SegmentedImage extends Component {

    public void render(){

        updatePosition(_transform.getX(), _transform.getY());
    }

    public SegmentedImage(DependencyContainer dependencyContainer, Window window,
                          ISegmentSpriteSettings segmentSettings, Transform transform){

        super(dependencyContainer);

        _window = window;
        _segmentSettings = segmentSettings;
        _transform = transform;

        float x = getPivotedX(_transform.getX());
        float y = _transform.getY();

        _sprites = new ArrayList<>();
        addSprite(segmentSettings.getLeftSprite(), x + getXOffset(_sprites.size()), y);
        for (int i = 0; i < segmentSettings.getMidSegmentsCount(); i++)
            addSprite(segmentSettings.getMidSprite(), x + getXOffset(_sprites.size()), y);
        addSprite(segmentSettings.getRightSprite(), x + getXOffset(_sprites.size()), y);
    }

    public void onDestroy(){
        for(int i = 0; i < _sprites.size(); i++)
            _window.removeSprite(_sprites.get(i));
        _sprites.clear();
    }

    private List<Integer> _sprites;

    private Window _window;
    private ISegmentSpriteSettings _segmentSettings;
    private Transform _transform;

    private void updatePosition(float x, float y){

        x = getPivotedX(x);

        for (int i = 0; i < _sprites.size(); i++)
            _window.moveSprite(_sprites.get(i), x + getXOffset(i), y);
    }

    private void addSprite(SpriteType spriteType, float x, float y){
        _sprites.add(_window.createSprite(spriteType, x, y));
    }

    private float getPivotedX(float x){
        float offset = -getWidth() * _segmentSettings.getPivotX();
        return x + offset;
    }

    private float getXOffset(int spriteIndex){

        return _segmentSettings.getSegmentWidth() * spriteIndex;
    }

    private float getWidth(){
        int totalSegmentsCount = 2 + _segmentSettings.getMidSegmentsCount();
        return totalSegmentsCount * _segmentSettings.getSegmentWidth();
    }

}
