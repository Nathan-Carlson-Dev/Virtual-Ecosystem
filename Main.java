import java.awt.*;
import java.util.*;

import Rendering.*;
import Data.Maps.*;

class Main {
    public static void main(String arg[]) {
        // init setup
        int width = 950;
        int height = 600;
        PixelMap pixelMap = new PixelMap(width, height);
        Terrain t = new Terrain();
        ItemMap i = new ItemMap(width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (t.map[x][y] == 0)
                    pixelMap.changeColor(x, y, new Color(255, 255, 255));
                if (t.map[x][y] == 1)
                    pixelMap.changeColor(x, y, new Color(200, 200, 200));
                if (t.map[x][y] == 2)
                    pixelMap.changeColor(x, y, new Color(100, 100, 100));
                if (t.map[x][y] == 3)
                    pixelMap.changeColor(x, y, new Color(0, 0, 0));
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (i.map[x][y] == 1)
                    pixelMap.changeColor(x, y, Color.GREEN);
                if (i.map[x][y] == 2)
                    pixelMap.changeColor(x, y, Color.YELLOW);
                if (i.map[x][y] == 3)
                    pixelMap.changeColor(x, y, Color.BLUE);
                if (i.map[x][y] == 4)
                    pixelMap.changeColor(x, y, Color.PINK);
            }
        }
        Renderer r = new Renderer(pixelMap, width, height, i);
        // update loop
        while (true) {
            r.update();
            i.update();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (t.map[x][y] == 0)
                        pixelMap.changeColor(x, y, new Color(255, 255, 255));
                    if (t.map[x][y] == 1)
                        pixelMap.changeColor(x, y, new Color(200, 200, 200));
                    if (t.map[x][y] == 2)
                        pixelMap.changeColor(x, y, new Color(100, 100, 100));
                    if (t.map[x][y] == 3)
                        pixelMap.changeColor(x, y, new Color(0, 0, 0));
                }
            }
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (i.map[x][y] == 1)
                        pixelMap.changeColor(x, y, Color.GREEN);
                    if (i.map[x][y] == 2)
                        pixelMap.changeColor(x, y, Color.YELLOW);
                    if (i.map[x][y] == 3)
                        pixelMap.changeColor(x, y, Color.BLUE);
                    if (i.map[x][y] == 4)
                        pixelMap.changeColor(x, y, Color.PINK);
                }
            }
        }
    }
}