package craftout.utils;

public class MathUtils {
    public static Vector2 reflect(float x, float y, float normalX, float normalY) {

        float velocityDotProduct = dot(x, y, normalX, normalY);
        Vector2 reflectVector = new Vector2(
                x- 2 * velocityDotProduct * normalX,
                y - 2 * velocityDotProduct * normalY);

        return reflectVector;
    }

    public static float dot(float x, float y, float x2, float y2){

        return x * x2 + y * y2;
    }
}
