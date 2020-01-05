package Rendering;

import javax.swing.*;

import Data.Maps.ItemMap;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class Renderer extends JPanel {

    private static final long serialVersionUID = 1L;

    // Controls
    private int width = 500;
    private int height = 500;
    private PixelMap pixelMap;
    private BufferedImage bi;
    private BufferStrategy bs;

    // init
    public Renderer(PixelMap pixelMap, int width, int height, ItemMap itemMap) {

        // init controls
        this.pixelMap = pixelMap;
        this.width = width;
        this.height = height;

        // init canvas
        setSize(width, height);

        // init window
        JFrame win = new JFrame();
        win.setSize(width, height);
        win.setTitle("Virtual Ecosystems");
        win.add(this);
        win.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.print("Saving Information...");
                itemMap.saveItemMap();
                System.out.print("\tDone!");
                win.dispose();
                System.exit(0);
            }
        });
        win.setVisible(true);

    }

    // how to paint the screen
    public void paintComponent(Graphics g) {
        // use pixel map to fill the pixels on the screen
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                g.setColor(pixelMap.getColor(x, y));
                g.fillRect(x, y, 1, 1);
            }
        }
    }

    // update the screen
    public void update() {
        repaint();
    }
}