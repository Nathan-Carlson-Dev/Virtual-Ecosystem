package Data.Maps;

import Rendering.PixelMap;

public class World {

    public PixelMap pixelMap;
    public ItemMap itemMap;
    public Terrain terrain;
    public MemberMap memberMap;

    public World(int width, int height, PixelMap pixelMap) {
        this.pixelMap = pixelMap;
        itemMap = new ItemMap(width, height, "Virtual-Ecosystem/Data/Maps/Items.dat");
        terrain = new Terrain("Virtual-Ecosystem/Data/Maps/terrain.csv");
        memberMap = new MemberMap("Virtual-Ecosystem/Data/Maps/Members.dat");
    }

    public void Update() {
        // update maps
        itemMap.update();

        // update pixel map
        for (int x = 0; x < 950; x++) {
            for (int y = 0; y < 600; y++) {
                // FILL THIS WITH CONTENT
            }
        }
    }

    public void Save() {
        itemMap.saveItemMap();
        memberMap.saveMemberMap();
    }
}