package Data.Maps;

import Rendering.PixelMap;

import java.io.*;
import java.awt.*;

public class World {

    public PixelMap pixelMap;
    public ItemMap itemMap;
    public Terrain terrain;

    // init world
    public World(int width, int height, PixelMap pixelMap) {
        this.pixelMap = pixelMap;
        itemMap = new ItemMap(width, height, (new File(".")).getAbsolutePath() + "/Items.dat");
        terrain = new Terrain((new File(".")).getAbsolutePath() + "/terrain.csv");
    }

    // update maps
    public void Update() {
        // update maps
        itemMap.update();

        // update pixel map
        for (int x = 0; x < 950; x++) {
            for (int y = 0; y < 600; y++) {
                pixelMap.changeColor(x, y, Color.BLACK);
                drawTerrain(x, y);
                drawItemMap(x, y);
            }
        }
    }

    // save maps
    public void Save() {
        itemMap.saveItemMap();
    }

    // draw item map to pixel map
    private void drawItemMap(int x, int y) {
        try {
            if (itemMap.map[x][y] == 4)
                pixelMap.changeColor(x, y, new Color(180, 0, 180));
            if (itemMap.map[x][y] == 3)
                pixelMap.changeColor(x, y, new Color(10, 100, 150));
            if (itemMap.map[x][y] == 2)
                pixelMap.changeColor(x, y, new Color(100, 100, 100));
            if (itemMap.map[x][y] == 1)
                pixelMap.changeColor(x, y, new Color(0, 0, 0));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // draw terrain map to pixel map
    private void drawTerrain(int x, int y) {
        try {
            if (terrain.map[x][y] == 3)
                pixelMap.changeColor(x, y, new Color(0, 0, 255));
            if (terrain.map[x][y] == 2)
                pixelMap.changeColor(x, y, new Color(255, 255, 0));
            if (terrain.map[x][y] == 1)
                pixelMap.changeColor(x, y, new Color(0, 255, 0));
            if (terrain.map[x][y] == 0)
                pixelMap.changeColor(x, y, new Color(0, 150, 0));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}