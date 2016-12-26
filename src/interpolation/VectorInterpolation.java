package interpolation;

import helpers.Line;
import helpers.MidCoord;
import helpers.ExtendedPApplet;
import helpers.PolarLine;
import processing.core.PApplet;

import static java.awt.Color.*;

public class VectorInterpolation extends ExtendedPApplet {
    // fixed
    private final int h = 200;
    private final int normalLength = 75;

    private PolarLine v1;
    private PolarLine v2;

    // animation
    private final int numberOfLines = 20;
    private int x = 1; // \in [1,9]

    @Override
    protected int getHeight() {
        return 600;
    }

    @Override
    protected int getWidth() {
        return 600;
    }

    public static void main(String[] args) {
        PApplet.main("interpolation.VectorInterpolation");
    }

    public void settings() {
        size(getWidth(), getHeight());
    }

    public void setup() {
        v1 = new PolarLine(MidCoord.getWindowCoord(h / 2, 0), normalLength, 45);
        v2 = new PolarLine(MidCoord.getWindowCoord(-h / 2, 0), normalLength, -45);

        background(white);
        fill(black);
        stroke(black);
        strokeWeight(2);
        pointWeight(5);
//        frameRate(4);

        drawPoints();
        drawBase();
    }

    public void draw() {
        if (x < numberOfLines) {
            stroke(red);
            arrow(v1.interpolate((double) x / numberOfLines, v2));

            x += 1;
        }

        if (mousePressed) {
            background(white);
            x = 1;
            drawPoints();
            drawBase();
        }

        drawPoints();
    }

    private void drawBase() {
        line(
                MidCoord.getWindowCoord(-h / 2, 0),
                MidCoord.getWindowCoord(h / 2, 0)
        );
        arrow(v1);
        arrow(v2);
    }

    private void drawPoints() {
        stroke(black);
        point(middle);
        point(MidCoord.getWindowCoord(h / 4, 0));
        point(MidCoord.getWindowCoord(-h / 4, 0));
    }
}
