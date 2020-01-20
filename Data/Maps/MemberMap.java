package Data.Maps;

import java.io.*;
import java.nio.*;

public class MemberMap {

    File f;
    public long[][] map = new long[950][600];
    public long MemberCounter = 0;

    // init member map
    public MemberMap(String file) {
        f = new File(file);
        // init map array
        for (int x = 0; x < 950; x++) {
            for (int y = 0; y < 600; y++) {
                map[x][y] = 0;
            }
        }
        try {
            // splice data into containers of map array
            if (f.exists() && !f.isDirectory()) {
                BufferedReader bf = new BufferedReader(new FileReader(f));
                String line = bf.readLine();

                int x = 0, y = 0;
                while (y < 600 && line != null) {
                    byte[] ByteLine = line.getBytes();
                    byte temp[] = new byte[8];
                    for (int i = 0; i < 7; i++)
                        temp[i] = ByteLine[i];
                    map[x][y] = ByteBuffer.wrap(temp).getLong();
                    line = bf.readLine();
                    x++;
                    if (x == 950) {
                        x = 0;
                        y++;
                    }
                }
                bf.close();
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    // save member map
    public void saveMemberMap() {
        try {
            // format and save data in file
            BufferedWriter bf = new BufferedWriter(new FileWriter(f));
            int y = 0;
            while (y < 600) {
                for (int x = 0; x < 950; x++) {
                    byte[] temp = ByteBuffer.allocate(8).putLong(map[x][y]).array();
                    for (int i = 0; i < 8; i++)
                        bf.write(temp[i]);
                }
                y++;
            }
            bf.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}