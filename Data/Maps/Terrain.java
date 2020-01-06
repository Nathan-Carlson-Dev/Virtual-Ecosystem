package Data.Maps;

import java.io.*;

public class Terrain {

    public byte map[][] = new byte[950][600];
    File f;

    // init Terrain
    public Terrain(String file) {
        try {
            f = new File(file);
            BufferedReader bf = new BufferedReader(new FileReader(f));
            String line = bf.readLine();
            int y = 0;
            while (line != null) {
                line = line.replace(",", "");
                for (int i = 0; i < 950; i++) {
                    if (line.charAt(i) == '0')
                        map[i][y] = 0;
                    if (line.charAt(i) == '1')
                        map[i][y] = 1;
                    if (line.charAt(i) == '2')
                        map[i][y] = 2;
                    if (line.charAt(i) == '3')
                        map[i][y] = 3;
                }
                y++;
                line = bf.readLine();
            }
            bf.close();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}