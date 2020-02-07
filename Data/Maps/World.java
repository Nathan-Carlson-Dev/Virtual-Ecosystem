package Data.Maps;

import Rendering.PixelMap;

import java.io.*;

import Population.Population;

import java.awt.*;

public class World {

    public PixelMap pixelMap;
    public ItemMap itemMap;
    public Terrain terrain;
    public Population population;
    public File statistics = new File(".");
    public BufferedWriter bw;

    // init world
    public World(int width, int height, PixelMap pixelMap, boolean fromSave) {
        this.pixelMap = pixelMap;
        itemMap = new ItemMap(width, height, (new File(".")).getAbsolutePath() + "/Items.dat");
        terrain = new Terrain((new File(".")).getAbsolutePath() + "/terrain.csv");
        try {
            population = new Population(4000, new int[] { 2, 2, 2 }, fromSave, this);
            bw = new BufferedWriter(new FileWriter(statistics.getAbsolutePath() + "/Statistics.csv"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // update maps
    public void Update() {
        // update maps
        itemMap.update();

        if (population.members.size() > 0) {
            // update and render population
            population.adjustPopulation(255, 0, 0);

            System.out.println("Status Report:");
            System.out.println("Current Tick: " + population.popTick);
            System.out.println("Current Available ID: " + population.IDIndex);
            System.out.println("Current Size: " + population.members.size());
            System.out.println("Age of Oldest Living Member: " + population.OldestLivingAge);
            System.out.println("Age of Oldest Member: " + population.OldestAgeEver);
            System.out.println("Number of Births in Tick: " + population.numReproductionTick);
            System.out.println("Number of Births in Total: " + population.numReproductionsTotal);
            System.out.println("Average Age: " + population.AgeAvg);

            try {
                bw.append(Integer.toString(population.popTick) + "," + Integer.toString(population.IDIndex) + ","
                        + Integer.toString(population.members.size()) + ","
                        + Integer.toString(population.OldestLivingAge) + ","
                        + Integer.toString(population.OldestAgeEver) + ","
                        + Integer.toString(population.numReproductionTick) + "," + Double.toString(population.AgeAvg));
                bw.newLine();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        // update pixel map
        for (int x = 0; x < 950; x++) {
            for (int y = 0; y < 600; y++) {
                pixelMap.changeColor(x, y, Color.BLACK);
                // drawTerrain(x, y);
                // drawItemMap(x, y);
            }
        }

    }

    // save maps
    public void Save() {
        // itemMap.saveItemMap();
        try {
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // population.Save();
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