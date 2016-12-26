package helpers;

import java.awt.*;

public class PolarLine extends Line {
    public final int length;
    public final double angle;

    private Point calEnd() {
        double angle = Math.toRadians(this.angle - 90); // -90 deg to suit my system
        return new Point(
                (int) (this.start.x + length * Math.cos(angle)),
                (int) (this.start.y + length * Math.sin(angle))
        );
    }

    public PolarLine(Point start, int length, double angle) {
        super(start, null);

        this.length = length;
        this.angle = angle;
        this.end = calEnd();
    }

    public PolarLine(int x, int y, int length, double angle) {
        this(new Point(x, y), length, angle);
    }

    @Override
    public double getAngle() {
        return angle;
    }

    /**
     * @param lamda \in [0, 1] (0 => this, 1 => other)
     */
    public PolarLine interpolate(double lamda, PolarLine other) {
        double x = lamda * this.start.x + (1 - lamda) * other.start.x;
        double y = lamda * this.start.y + (1 - lamda) * other.start.y;
        double length = lamda * this.length + (1 - lamda) * other.length;
        double angle = lamda * this.angle + (1 - lamda) * other.angle;
        return new PolarLine((int) x, (int) y, (int) length, angle);
    }
}
