package splines;

import helpers.ExtendedPApplet;
import helpers.FPoint;
import processing.core.PApplet;
import processing.event.KeyEvent;

import java.awt.*;

public class Subdivision extends ExtendedPApplet {

    int bgColor = new Color(255, 248, 246).getRGB();
    int drawColor = new Color(255, 0, 4).getRGB();
    int maxRec = 2;

    @Override
    protected int getHeight() {
        return 800;
    }

    @Override
    protected int getWidth() {
        return 800;
    }


    public static void main(String[] args) {
        PApplet.main("splines.Subdivision");
    }

    public void settings() {
        size(getWidth(), getHeight());
    }

    public void setup() {
        background(bgColor);
        fill(drawColor);
        strokeWeight(5);
        System.out.println("LEFT/RIGHT: change max recursion");
    }

    public void draw() {
        surface.setTitle(round(frameRate) + " fps");

        // clear
        background(bgColor);

        FPoint p0 = new FPoint(100, getHeight() - 100);
        FPoint p1 = new FPoint(100, 100);
        FPoint p2 = new FPoint(getWidth() - 100, 100);
        subdivide(1, p0, p1, p2);
    }

    private void subdivide(int rec, FPoint p0, FPoint p1, FPoint p2) {
        if (rec >= maxRec) {
            line(p0.x, p0.y, p1.x, p1.y);
            line(p1.x, p1.y, p2.x, p2.y);
        } else {
            FPoint p01 = p0.add(p1).div(2);
            FPoint p12 = p1.add(p2).div(2);
            FPoint p = p01.add(p12).div(2);
            subdivide(rec + 1, p0, p01, p);
            subdivide(rec + 1, p, p12, p2);
        }

        // draw points
        stroke(Color.red.getRGB());
        strokeWeight(10);

        point(p0.x, p0.y);
        point(p1.x, p1.y);
        point(p2.x, p2.y);

        strokeWeight(5);
        stroke(Color.black.getRGB());
    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case LEFT: // LEFT
                maxRec--;
                System.out.println("maxRec: " + maxRec);
                break;
            case RIGHT: // RIGHT
                maxRec++;
                System.out.println("maxRec: " + maxRec);
                break;
        }
    }
}
