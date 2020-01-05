package Data.Maps;

import java.io.*;

public class ItemMap {

    public byte[][] map = new byte[950][600];
    File f = new File("Virtual-Ecosystem/Data/Maps/Items.dat");

    public ItemMap(int width, int height) {
        try {
            if (f.exists() && !f.isDirectory()) {
                BufferedReader bf = new BufferedReader(new FileReader(f));
                String line = bf.readLine();
                int y = 0;
                while (line != null) {
                    for (int i = 0; i < 950; i++) {
                        if (line.charAt(i) == '0')
                            map[i][y] = 0;
                        if (line.charAt(i) == '1')
                            map[i][y] = 1;
                        if (line.charAt(i) == '2')
                            map[i][y] = 2;
                        if (line.charAt(i) == '3')
                            map[i][y] = 3;
                        if (line.charAt(i) == '4')
                            map[i][y] = 4;
                    }
                    y++;
                    line = bf.readLine();
                }
                bf.close();

            } else {
                for (int y = 0; y < 600; y++) {
                    for (int x = 0; x < 950; x++) {
                        map[x][y] = 0;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    public void saveItemMap() {
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(f));
            int y = 0;
            char c = '0';
            while (y < 600) {
                for (int i = 0; i < 950; i++) {
                    if (map[i][y] == 0)
                        c = '0';
                    else if (map[i][y] == 1)
                        c = '1';
                    else if (map[i][y] == 2)
                        c = '2';
                    else if (map[i][y] == 3)
                        c = '3';
                    else if (map[i][y] == 4)
                        c = '4';
                    bf.write(c);
                }
                bf.newLine();
                y++;
            }
            bf.close();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    public void update() {
        for (int y = 0; y < 600; y++) {
            for (int x = 0; x < 950; x++) {
                if ((int) (Math.random() * 10000) == 1 && map[x][y] == 0)
                    map[x][y] = (byte) (Math.random() * 4 + 1);
            }
        }
    }

}