package craftout.settings.Ball;

public class DefaultBallSettings implements IBallSettings{

    public float getWidth() { return 16; }
    public float getHeight() { return 16; }

    public float getPivotX() { return 0.5f; }
    public float getPivotY() { return 0.5f; }

    public float getMoveSpeed() { return 7f; }

}
