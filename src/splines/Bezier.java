package splines;

import helpers.ExtendedPApplet;
import helpers.FPoint;
import processing.core.PApplet;
import processing.event.MouseEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.IntStream;

import static java.awt.Color.black;
import static java.awt.Color.white;
import static java.awt.Color.red;
import static java.awt.Color.blue;

public class Bezier extends ExtendedPApplet {
    // colors
    private final Color bgColor = white;
    private final Color ptColor = red;
    private final int factor = 50;
    private final int pointsRes = 100;

    ArrayList<FPoint> pointsSampleCurve = new ArrayList<>();
    ArrayList<FPoint> mousePoints = new ArrayList<>();

    @Override
    protected int getHeight() {
        return 750;
    }

    @Override
    protected int getWidth() {
        return 750;
    }

    public static void main(String[] args) {
        PApplet.main("splines.Bezier");
    }

    public void settings() {
//        noSmooth();
        size(getWidth(), getHeight());
    }

    public void setup() {
        background(bgColor);
        noStroke();

        pointsSampleCurve.add(new FPoint(1, 7));
        pointsSampleCurve.add(new FPoint(3, 10));
        pointsSampleCurve.add(new FPoint(6, 11));
        pointsSampleCurve.add(new FPoint(7, 6));

        System.out.println("LEFT MOUSE to add new point");
        System.out.println("RIGHT MOUSE to delete mouse points");
    }

    public void draw() {
        background(bgColor); // clear bg

        drawGrid();

        if (mousePoints.size() >= 1)
            drawPoints(mousePoints, false);

        if (mousePoints.size() >= 2) {
            FPoint[] mousePointsArray = new FPoint[mousePoints.size()];
            IntStream.range(0, mousePoints.size()).forEach(i -> mousePointsArray[i] = mousePoints.get(i));

            ArrayList<FPoint> drawPoints = new ArrayList<>();
            IntStream.range(0, pointsRes)
                    .forEach(i -> drawPoints.add(de_Casteljau(mousePoints.size(), mousePointsArray, (float) i /
                            pointsRes)));

            curveFromPoints(drawPoints);
        } else {
            drawCurveFromWorldPoints(pointsSampleCurve);
            drawPoints(pointsSampleCurve, true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        switch (event.getButton()) {
            case LEFT:
                mousePoints.add(new FPoint(event.getX(), event.getY()));
                break;
            case RIGHT:
                mousePoints.clear();
                break;
        }
    }

    private void drawCurveFromWorldPoints(ArrayList<FPoint> points) {
        FPoint ps[] = new FPoint[points.size()];
        for (int i = 0; i < points.size(); i++)
            ps[i] = toWorldCoord(points.get(i));

        ArrayList<FPoint> toDrawPoints = new ArrayList<>();
        for (int i = 0; i < pointsRes; i++)
            toDrawPoints.add(de_Casteljau(ps.length, ps, (float) i / pointsRes));
        curveFromPoints(toDrawPoints);
    }

    private void curveFromPoints(ArrayList<FPoint> points) {
        noFill();
        stroke(blue);
        strokeWeight(2);

        for (int i = 0; i < points.size() - 1; i++)
            line(points.get(i).x, points.get(i).y, points.get(i + 1).x, points.get(i + 1).y);
    }

    private FPoint de_Casteljau(int anz, FPoint[] points, float t) {
        if (anz < 2)
            return points[0];
        for (int i = 0; i < anz - 1; i++)
            points[i] = (points[i].mul(1 - t)).add(points[i + 1].mul(t));
        return de_Casteljau(anz - 1, points, t);
    }

    private void drawGrid() {
        boolean[][] rects = new boolean[100][100];

        stroke(black);
        strokeWeight(1);
        for (int r = 0; r < 100; r++) // rows
            for (int c = 0; c < 100; c++) // columns
                rect(r * factor, c * factor, factor, factor);
    }

    private void drawPoints(ArrayList<FPoint> points, boolean worldCoord) {
        int size = 10;
        stroke(ptColor);
        for (FPoint point : points) {
            FPoint p = point;
            if (worldCoord)
                p = toWorldCoord(point);
            ellipse(p.x, p.y, size, size);
        }
    }

    private FPoint toWorldCoord(FPoint p) {
        return new FPoint(p.x * factor, getHeight() - p.y * factor);
    }
}
