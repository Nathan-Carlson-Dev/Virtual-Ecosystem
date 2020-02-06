import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Data.Maps.World;
import Population.Population;
import Population.Archetypes.Incrementor;
import Rendering.PixelMap;
import Rendering.Renderer;

class Main {

    public static void main(String arg[]) {
        // init setup
        int width = 950;
        int height = 600;
        PixelMap pixelMap = new PixelMap(width, height);
        World w = new World(width, height, pixelMap, false);
        Renderer r = new Renderer(pixelMap, width, height, w);
        Thread Updater = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    r.update();
                    w.Update();
                }
            }
        });
        Updater.start();
    }

}