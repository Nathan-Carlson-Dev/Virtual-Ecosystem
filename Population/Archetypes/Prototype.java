package Population.Archetypes;

import Data.Neural.*;
import Data.Maps.*;
import java.util.*;

public abstract class Prototype {

    protected Random randomizor = new Random();

    public int x = 0, y = 0;
    public float hunger = 100, thirst = 100;
    public Neural mind = null;
    public MemberMap memberMap = null;
    public ItemMap itemMap = null;
    public Terrain terrain = null;
    public AdjustmentStrategy EvolutionStrategy = new AdjustmentStrategy(){
        @Override
        public void adjust(Layer[] layers) {
            for (int i = 0; i < layers.length; i++) {
                layers[i].adjustLayer();
            }
        }
    };
    public void move() {
        
        System.out.println(x + ", " + y);

        double[] inputs = new double[301];
        
        inputs[0] = hunger;
        inputs[1] = thirst;
        inputs[2] = x;
        inputs[3] = y;
        
        int i = 0;
        for(int xx = x - 5; xx < x + 5; xx++){
            for(int yy = y - 5; yy < y + 5; yy++){
                if(xx != x && yy != y && i < 99){
                    inputs[4 + i] = terrain.map[xx][yy];
                    inputs[103 + i] = itemMap.map[xx][yy];
                    inputs[202 + i] = memberMap.map[xx][yy];
                    i++;
                }
            }
        }

        double[] outputs = mind.calcNetwork(inputs);
        
        if(outputs[0] > outputs[1]) y--;
        else if(outputs[0] < outputs[1]) y++;
        if(outputs[2] > outputs[3]) x--;
        else if(outputs[2] < outputs[3]) x++;

        if(x <= 10) x = 939;
        if(x > 939) x = 11;
        if(y <= 10) y = 589;
        if(y > 589) y = 11;

        System.out.println(x + ", " + y);
    }
    public Prototype reproduce() throws CloneNotSupportedException{
        Oscillator child = (Oscillator) this.clone();
        child.mind.adjustNetwork(EvolutionStrategy);
        return child;
    }

}