import helpers.ExtendedPApplet;
import processing.core.PApplet;
import processing.event.KeyEvent;

import java.awt.*;

public class Sphere extends ExtendedPApplet {

    // colors
    int bgColor = new Color(255, 248, 246).getRGB();
    int drawColor = new Color(255, 0, 4).getRGB();

    // sphere radius and number of points
    private int r = getHeight() / 4;
    private int rIncrease = 10;
    private int numPoints = 30;
    private int pointsIncrease = 5;

    @Override
    protected int getHeight() {
        return 400;
    }

    @Override
    protected int getWidth() {
        return 600;
    }


    public static void main(String[] args) {
        PApplet.main("Sphere");
    }

    public void settings() {
        size(getWidth(), getHeight(), P3D);
    }

    public void setup() {
        background(bgColor);
        fill(drawColor);
        stroke(drawColor);
        strokeWeight(2);
        System.out.println("UP/DOWN: radius, LEFT/RIGHT: number of points");
        System.out.println("Press mouse to change sphere color");
    }

    public void draw() {
        // frame rate in title
        surface.setTitle(round(frameRate) + " fps");

        // clear
        background(bgColor);

        // draw sphere
//        drawSphere(getWidth() / 2, getHeight() / 2, 0, numPoints, r);
        drawSphere(mouseX, mouseY, 0, numPoints, r);
    }

    // change sphere color
    @Override
    public void mouseClicked() {
        stroke(new Color(
                Math.round(random(0, 255)),
                Math.round(random(0, 255)),
                Math.round(random(0, 255))).getRGB());
    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case 38: // UP
                r += rIncrease;
                System.out.println("radius: " + r);
                break;
            case 40: // DOWN
                if (r >= rIncrease)
                    r -= rIncrease;
                System.out.println("radius: " + r);
                break;

            case 37: // LEFT
                if (numPoints >= pointsIncrease)
                    numPoints -= pointsIncrease;
                System.out.println("numPoints: " + numPoints);
                break;
            case 39: // RIGHT
                numPoints += pointsIncrease;
                System.out.println("numPoints: " + numPoints);
                break;
        }
    }

    private void drawSphere(float midX, float midY, float midZ, int numPoints, double r) {
        if (r <= 0)
            throw new IllegalArgumentException("r can't be negative.");

        for (int i = 0; i < numPoints; i++) {
            for (int j = 0; j < numPoints; j++) {
                double theta = Math.PI * ((double) i / (double) numPoints);
                double phi = (2 * Math.PI) * ((double) j / (double) numPoints);
                point(
                        midX + (float) (r * Math.sin(theta) * Math.cos(phi)),
                        midY + (float) (r * Math.cos(theta)),
                        midZ + (float) (r * Math.sin(theta) * Math.sin(phi))
                );
            }
        }
    }
}
