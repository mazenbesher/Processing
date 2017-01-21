package texture;

import helpers.ExtendedPApplet;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;

import static java.awt.Color.black;
import static java.awt.Color.white;
import static java.awt.Color.red;

public class Texture extends ExtendedPApplet {
    private PImage tex;
    private int textSize = 12;

    // colors
    private Color textColor = black;
    private Color bgColor = white;
    private Color strockColor = red;

    @Override
    protected int getHeight() {
        return 650;
    }

    @Override
    protected int getWidth() {
        return 600;
    }

    public static void main(String[] args) {
        PApplet.main("texture.Texture");
    }

    public void settings() {
        size(getWidth(), getHeight(), P2D);
    }

    public void setup() {
        background(bgColor);
        noStroke();

        tex = loadImage(this.getClass().getResource("/cross.png").getFile());
        textureMode(NORMAL);
        textureWrap(REPEAT);
    }

    public void draw() {
        clear();
        background(bgColor);

        int margin = textSize * 2;
        interactiveWrap();
        originalTex(20, 20);
        aTex(20 + 100 + margin, 20);
        bTex(20 + 100 + margin + 100 + margin, 20);
        cTex(20 + 100 + margin, 20 + 100 + margin);
        dTex(20 + 100 + margin, 20 + 100 + margin + 200 + margin);
    }

    private void interactiveWrap() {
        int margin = 10;
        int x = margin;
        int y = getHeight() - textSize - margin;

        if(mousePressed){
            textureWrap(CLAMP);
            text(x, y, "Wrap: Clamp");
        } else {
            textureWrap(REPEAT);
            text(x, y, "Wrap: Repeat");
        }
    }

    private void dTex(int x, int y) {
        int height = 200;
        int margin = textSize / 2;
        text(x, y - margin, "d)");

        stroke(red);
        strokeWeight(1);
        beginShape();
        texture(tex);
        vertex(x, y, 0, 1);
        vertex(x - 100, y + height, 0, 0);
        vertex(x + 300, y + height, 1, 0);
        vertex(x + 200, y, 1 ,1);
        endShape(CLOSE);

    }

    private void cTex(int x, int y) {
        int size = 200;
        int margin = textSize / 2;
        text(x, y - margin, "c)");

        stroke(red);
        strokeWeight(1);
        beginShape();
        texture(tex);
        vertex(x, y, 3, 0);
        vertex(x, y + size, 0, 0);
        vertex(x + size, y + size, 0, 2);
        vertex(x + size, y, 3, 2);
        endShape(CLOSE);
    }

    private void bTex(int x, int y) {
        int height = 100;
        int width = 250;
        int margin = textSize / 2;
        text(x, y - margin, "b)");

        stroke(red);
        strokeWeight(1);
        beginShape();
        texture(tex);
        vertex(x, y, 0, 1);
        vertex(x, y + height, 0, 0);
        vertex(x + width, y + height, 2.5f, 0);
        vertex(x + width, y, 2.5f ,1);
        endShape(CLOSE);
    }

    private void aTex(int x, int y) {
        int size = 100;
        int margin = textSize / 2;
        text(x, y - margin, "a)");

        stroke(red);
        strokeWeight(1);
        beginShape();
        texture(tex);
        vertex(x, y, 0, 0.5f);
        vertex(x, y + size, 0, 0);
        vertex(x + size, y + size, 0.5f, 0);
        vertex(x + size, y, 0.5f ,0.5f);
        endShape(CLOSE);
    }

    private void originalTex(int x, int y) {
        int size = 100;
        int margin = textSize / 2;
        text(x, y - margin, "Original Texture");

        stroke(red);
        strokeWeight(1);
        beginShape();
        texture(tex);
        vertex(x, y, 0, 1);
        vertex(x, y + size, 0, 0);
        vertex(x + size, y + size, 1, 0);
        vertex(x + size, y, 1 ,1);
        endShape(CLOSE);
    }

    private void text(int x, int y, String text) {
        textSize(textSize);
        fill(textColor);
        noStroke();
        text(text, x, y);
        fill(bgColor);
    }
}
