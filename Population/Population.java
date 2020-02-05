package Population;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import Data.Maps.ItemMap;
import Data.Maps.Terrain;
import Data.Maps.World;
import Population.Archetypes.*;

public class Population {
    public List<Prototype> members = new ArrayList<Prototype>(0);
    public int IDIndex = 1;
    public World world;

    public Population(int initSize, int[] ratio, boolean grabFromSave, World w)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        world = w;
        ItemMap im = w.itemMap;
        Terrain t = w.terrain;
        if (grabFromSave) {
            int index = IDIndex;
            File saveFile = new File("./MemberSaves/" + Integer.toString(index));
            while (saveFile.exists()) {
                Prototype member = null;
                FileInputStream f = new FileInputStream(saveFile);
                ObjectInputStream o = new ObjectInputStream(f);
                member = (Prototype) o.readObject();
                o.close();
                f.close();
                if (member.livingState) {
                    if (IDIndex < member.ID)
                        IDIndex = member.ID + 1;
                    members.add(member);
                    saveFile = new File("./MemberSaves/" + Integer.toString(index));
                    System.out.println("ID Index:\t" + IDIndex);
                    System.out.println("Member Array Size: " + members.size());
                }
                ++index;
            }
        } else {
            for (int i = 0; i < initSize; i++) {
                try {
                    if (i % ratio[0] == 0) {
                        Incrementor member = new Incrementor(t, im);
                        member.ID = IDIndex;
                        members.add(member);
                        IDIndex++;
                    }
                } catch (Exception e) {
                }
                try {
                    if (i % ratio[1] == 0) {
                        Reducer member = new Reducer(t, im);
                        member.ID = IDIndex;
                        members.add(member);
                        IDIndex++;
                    }
                } catch (Exception e) {
                }
                try {
                    if (i % ratio[2] == 0) {
                        Oscillator member = new Oscillator(t, im);
                        member.ID = IDIndex;
                        members.add(member);
                        IDIndex++;
                    }
                } catch (Exception e) {
                }
                System.out.println("ID Index:\t" + IDIndex);
                System.out.println("Member Array Size: " + members.size());
            }
        }
    }
    
    public void adjustPopulation(){
        for (int i = 0; i < members.size(); i++) {
            Prototype p = members.get(i);
            p.move();
            world.pixelMap.changeColor(p.x, p.y, new Color(255, 0, 0, 100));
        }
    }
    
    public void Save() {
        for (int i = 0; i < members.size(); i++) {
            Prototype member = members.get(i);
            try {
                File saveFile = new File("./MemberSaves/" + Integer.toString(member.ID));
                FileOutputStream f = new FileOutputStream(saveFile);
                ObjectOutputStream o = new ObjectOutputStream(f);
                o.writeObject(member);
                o.close();
                f.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}