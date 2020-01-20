import java.awt.*;
import java.util.*;
import java.io.*;

import Rendering.*;
import Data.Maps.*;
import Data.Neural.*;

class Main {

    public static void main(String arg[]) {
        // init setup
        int width = 950;
        int height = 600;
        PixelMap pixelMap = new PixelMap(width, height);
        World w = new World(width, height, pixelMap);
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