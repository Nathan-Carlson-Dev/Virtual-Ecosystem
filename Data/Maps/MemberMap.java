package Data.Maps;

import java.io.*;
import java.nio.*;

public class MemberMap {

    File f;
    public long[][] map = new long[950][600];
    public long MemberCounter = 0;

    public MemberMap(String file) {
        f = new File(file);
        for(int x = 0; x < 950; x++){
            for(int y = 0; y < 600; y++){
                map[x][y] = 0;
            }
        }
        try {
            if (f.exists() && !f.isDirectory()) {
                BufferedReader bf = new BufferedReader(new FileReader(f));
                String line = bf.readLine();
                int y = 0;
                while (line != null) {
                    for(int x = 0; x < 950; x++){
                        byte temp[] = new byte[8];
                        for(int i = 0; i < 8; i++) temp[i] = (byte) line.charAt(i);
                    }
                    y++;
                    line = bf.readLine();
                }
                bf.close();

            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    public void saveMemberMap() {
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(f));
            int y = 0;
            char c = '0';
            while (y < 600) {
                for (int i = 0; i < 950; i++) {
                    // FILL ME WITH CONTENT
                    for (int d = 0; d < 8; d++)
                        bf.write(c);
                }
                bf.newLine();
                y++;
            }
            bf.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}