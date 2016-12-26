package helpers;

import exceptions.NotSetException;

import java.awt.*;
import java.util.Optional;

public final class MidCoord {
    private static Optional<Point> mid = Optional.empty();

    /**
     * Don't let anyone instantiate this class.
     */
    private MidCoord() {
    }

    public static void setMiddle(Point middle){
        mid = Optional.of(middle);
    }

    public static Point getWindowCoord(Point p) throws NotSetException {
        if(mid.isPresent())
            return new Point(mid.get().x + p.x, mid.get().y + p.y);
        throw new NotSetException("middle point has not been set");
    }

    public static Point getWindowCoord(int x, int y) throws NotSetException {
        if(mid.isPresent())
            return new Point(mid.get().x + x, mid.get().y + y);
        throw new NotSetException("middle point has not been set");
    }
}
