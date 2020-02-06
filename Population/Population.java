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
import java.util.Random;

import Data.Maps.ItemMap;
import Data.Maps.Terrain;
import Data.Maps.World;
import Population.Archetypes.*;

public class Population {
    public List<Prototype> members = new ArrayList<Prototype>(0);
    public int IDIndex = 1;
    public int popTick = 0;
    public int OldestLivingAge = 0;
    public int OldestAgeEver = 0;
    public int numReproductionsTotal = 0, numReproductionTick = 0;
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
                        member.birthTick = popTick;
                        members.add(member);
                        IDIndex++;
                    }
                } catch (Exception e) {
                }
                try {
                    if (i % ratio[1] == 0) {
                        Reducer member = new Reducer(t, im);
                        member.ID = IDIndex;
                        member.birthTick = popTick;
                        members.add(member);
                        IDIndex++;
                    }
                } catch (Exception e) {
                }
                try {
                    if (i % ratio[2] == 0) {
                        Oscillator member = new Oscillator(t, im);
                        member.ID = IDIndex;
                        member.birthTick = popTick;
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

    public void adjustPopulation() {
        popTick++;
        numReproductionTick = 0;
        OldestLivingAge = 0;
        for (int i = 0; i < members.size(); i++) {
            Prototype p = members.get(i);
            p.move();
            if (p.livingState)
                world.pixelMap.changeColor(p.x, p.y, new Color(255, 0, 0, 100));
            else {
                members.remove(i);
            }
            if ((new Random()).nextInt() % 20 == 1) {
                try {
                    Prototype child = p.reproduce();
                    child.ID = IDIndex;
                    child.birthTick = popTick;
                    IDIndex++;
                    members.add(child);
                    numReproductionTick++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (popTick - p.birthTick > OldestLivingAge)
                OldestLivingAge = popTick - p.birthTick;
        }
        if (OldestLivingAge > OldestAgeEver)
            OldestAgeEver = OldestLivingAge;
        numReproductionsTotal += numReproductionTick;
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