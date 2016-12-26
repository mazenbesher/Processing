package helpers;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;

public class Line {
    public Point start;
    public Point end;

    // aliases
    public int x1;
    public int x2;
    public int y1;
    public int y2;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public int norm() {
        initAliases();
        return (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public Line normalize() {
        int endX = start.x + ((end.x - start.x) / norm());
        int endY = start.y + ((end.y - start.y) / norm());
        return new Line(start, new Point(endX, endY));
    }

    /**
     * Angle is being calculated starting from the y-Axis<br>
     * e.g.<br>
     *  +90 is the x-Axis<br>
     *  -45 is the function y = -x pointing up-left<br>
     * @return angle in degrees
     */
    public double getAngle() {
        initAliases();
        if (x2 - x1 == 0) {
            if (y2 > y1)
                return 180;
            else
                return 0;
        }
        return -Math.toDegrees(Math.atan((y2 - y1) / (x2 - x1)));
    }

    private void initAliases() { // TODO: find better way to init aliases!
        x1 = start.x;
        x2 = end.x;
        y1 = start.y;
        y2 = end.y;
    }
}
