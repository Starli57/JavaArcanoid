package craftout.settings.Paddle;


public class DefaultPaddleSettings implements IPaddleSettings {

    public int getMidSegmentsCount(){
        return _segments;
    }

    public int getSegmentWidth() { return _segmentWidthPx; }

    public int getSegmentHeight() { return _segmentHeightPx; }

    public SpriteType getLeftSprite() { return SpriteType.PADDLE_LEFT; }

    public SpriteType getMidSprite() { return SpriteType.PADDLE_MID; }

    public SpriteType getRightSprite() { return SpriteType.PADDLE_RIGHT; }

    public float getXAspect() { return _startXAspect; }

    public float getYAspect() { return _startYAspect; }

    public float getPivotX() { return _pivotX; }

    public float getMoveSpeed() { return _moveSpeed; }


    private final int _segments = 20;
    private final int _segmentWidthPx = 8;
    private final int _segmentHeightPx = 16;
    private final float _startXAspect = 0.5f;
    private final float _startYAspect = 1;
    private final float _pivotX = 0.0f;
    private final float _moveSpeed = 5f;

}
