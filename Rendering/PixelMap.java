package Rendering;

import java.awt.*;

public class PixelMap {
    private Color[][] pixels;
    public int width, height;

    // init pixels
    public PixelMap(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new Color[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++)
                pixels[x][y] = new Color(0, 0, 0);
        }
    }

    // change the color
    public void changeColor(int x, int y, Color c) {
        pixels[x][y] = c;
    }

    // retrieve color
    public Color getColor(int x, int y) {
        return pixels[x][y];
    }
}