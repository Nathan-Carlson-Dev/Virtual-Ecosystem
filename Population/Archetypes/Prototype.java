package Population.Archetypes;

import Data.Neural.*;
import Data.Maps.*;

import java.io.Serializable;
import java.util.*;

public abstract class Prototype implements Serializable {

    protected Random randomizor = new Random();

    public int x = 0, y = 0, ID = -1;
    public boolean livingState = true;
    public float hunger = 50, thirst = 50;
    public Neural mind = null;
    public transient ItemMap itemMap = null;
    public transient Terrain terrain = null;
    public AdjustmentStrategy EvolutionStrategy = new AdjustmentStrategy() {
        @Override
        public void adjust(Layer[] layers) {
            for (int i = 0; i < layers.length; i++) {
                layers[i].adjustLayer();
            }
        }
    };

    public void move() {
        if(livingState){

        double[] inputs = new double[301];

        inputs[0] = hunger;
        inputs[1] = thirst;
        inputs[2] = x;
        inputs[3] = y;

        int i = 0;
        for (int xx = x - 5; xx < x + 5; xx++) {
            for (int yy = y - 5; yy < y + 5; yy++) {
                if (xx != x && yy != y && i < 99) {
                    inputs[4 + i] = terrain.map[xx][yy];
                    inputs[103 + i] = itemMap.map[xx][yy];
                    i++;
                }
            }
        }

        double[] outputs = mind.calcNetwork(inputs);

        if (outputs[0] > outputs[1])
            y--;
        else if (outputs[0] < outputs[1])
            y++;
        if (outputs[2] > outputs[3])
            x--;
        else if (outputs[2] < outputs[3])
            x++;

        if (x <= 10)
            x = 939;
        if (x > 939)
            x = 11;
        if (y <= 10)
            y = 589;
        if (y > 589)
            y = 11;

        thirst++;
        hunger++;

        if (itemMap.map[x][y] == 1) {
            hunger -= 2;
        } 
        else if(itemMap.map[x][y] == 2){
            hunger -= 3;
        }
        else if(itemMap.map[x][y] == 3){
            thirst -= 2;
        }
        else if(itemMap.map[x][y] == 4){
            thirst += 3;
            hunger += 3;
        }

        if (terrain.map[x][y] == 0) {
            hunger -= 2;
        } 
        else if(terrain.map[x][y] == 2){
            hunger -= 3;
        }
        else if(terrain.map[x][y] == 3){
            thirst -= 1;
            hunger += 1;
        }

        if(thirst > 100 || thirst < 0 || hunger > 100 || hunger < 0)
        livingState = false;

        }
    }

    public Prototype reproduce() throws CloneNotSupportedException {
        Oscillator child = (Oscillator) this.clone();
        child.thirst = 50;
        child.hunger = 50;
        child.mind.adjustNetwork(EvolutionStrategy);
        return child;
    }

}