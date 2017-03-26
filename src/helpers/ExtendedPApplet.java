package helpers;

import processing.core.PApplet;

import java.awt.*;

public abstract class ExtendedPApplet extends PApplet {
    protected int _pointDiameter = 5;
    private float _strokeWeight = 0;
    private int _arrowWeight = 5;
    private double _arrowAngle = 45;

    public final Point middle = new Point(getWidth() / 2, getHeight() / 2);

    protected abstract int getHeight();

    protected abstract int getWidth();

    public ExtendedPApplet() {
        MidCoord.setMiddle(middle);
    }

    /**
     * To be able to save stroke weight
     */
    @Override
    public void strokeWeight(float weight) {
        _strokeWeight = weight;
        super.strokeWeight(weight);
    }

    public float getStrokeWeight() {
        return _strokeWeight;
    }


    /**
     * allow java.awt.Color as parameter
     */
    public void stroke(Color color) {
        super.stroke(color.getRGB());
    }

    public void background(Color color) {
        super.background(color.getRGB());
    }

    public void fill(Color color) {
        super.fill(color.getRGB());
    }


    public void pointWeight(int weight) {
        _pointDiameter = weight;
    }

    protected void point(Point point) {
        super.strokeWeight(0); // bypass saving 0 as stroke weight
        ellipse(point.x, point.y, _pointDiameter, _pointDiameter);
        strokeWeight(_strokeWeight);
    }

    protected void line(Line l) {
        line(l.start.x, l.start.y, l.end.x, l.end.y);
    }

    protected void line(Point p1, Point p2) {
        line(new Line(p1, p2));
    }

    protected void arrowWeight(int weight) {
        _arrowWeight = weight;
    }

    protected void arrow(Line l) {
        line(l);
        line(new PolarLine(l.end, _arrowWeight, l.getAngle() + 180 + _arrowAngle));
        line(new PolarLine(l.end, _arrowWeight, l.getAngle() + 180 - _arrowAngle));
        // Note: _arrowAngle deg arrow angle (can be parametrized)
    }

    protected void arrow(Point p1, Point p2) {
        arrow(new Line(p1, p2));
    }
}
