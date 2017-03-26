package helpers;

public class FPoint implements PointInterface {
    public float x;
    public float y;

    public FPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public FPoint mul(float num) {
        return new FPoint(x * num, y * num);
    }

    public FPoint add(FPoint other) {
        return new FPoint(x + other.x, y + other.y);
    }

    public FPoint div(FPoint other) {
        return new FPoint(x / other.x, y / other.y);
    }

    public FPoint div(float num) {
        return new FPoint(x / num, y / num);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public java.awt.Point toPoint() {
        return new java.awt.Point(Math.round(x), Math.round(y));
    }
}
