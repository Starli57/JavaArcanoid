package craftout.settings.Paddle;

import craftout.settings.SegmentSprite.ISegmentSpriteSettings;

public interface IPaddleSettings extends ISegmentSpriteSettings {

    public float getXAspect();
    public float getYAspect();
    public float getMoveSpeed();
}
