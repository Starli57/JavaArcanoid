package craftout.settings.SegmentSprite;

public interface ISegmentSpriteSettings {
    public SpriteType getLeftSprite();
    public SpriteType getMidSprite();
    public SpriteType getRightSprite();

    public int getMidSegmentsCount();
    public int getSegmentWidth();
    public int getSegmentHeight();

    public float getPivotX();
}
