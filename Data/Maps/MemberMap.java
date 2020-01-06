package Data.Maps;

import java.io.*;

public class MemberMap {

    File f;

    public MemberMap(String file) {
        f = new File(file);
        try {
            if (f.exists() && !f.isDirectory()) {
                BufferedReader bf = new BufferedReader(new FileReader(f));
                String line = bf.readLine();
                int y = 0;
                while (line != null) {
                    for (int i = 0; i < 950; i++) {
                        // FILL ME WITH CONTENT
                    }
                    y++;
                    line = bf.readLine();
                }
                bf.close();

            } else {
                BufferedWriter bf = new BufferedWriter(new FileWriter(f));
                int y = 0;
                char c = '0';
                while (y < 600) {
                    for (int i = 0; i < 950; i++) {
                        // FILL ME WITH CONTENT
                        bf.write(c);
                    }
                    bf.newLine();
                    y++;
                }
                bf.close();
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    public void saveMemberMap() {

    }
}