package Population.Archetypes;

import java.util.*;

import Data.Maps.ItemMap;
import Data.Maps.MemberMap;
import Data.Maps.Terrain;
import Data.Neural.AdjustmentStrategy;
import Data.Neural.Layer;
import Data.Neural.Neural;

public class Reducer extends Prototype{

    public Reducer(Terrain t, ItemMap i, MemberMap m) {

        terrain = t;
        itemMap = i;
        memberMap = m;

        x = Math.abs(randomizor.nextInt() % 930 + 10);
        y = Math.abs(randomizor.nextInt() % 580 + 10);
        mind = new Neural(new int[]{301, 150, 75, 30, 4});
        EvolutionStrategy.adjust(mind.layers);
        EvolutionStrategy = new AdjustmentStrategy(){
            @Override
            public void adjust(Layer[] layers) {
                for (int i = 0; i < layers.length; i++) {
                    for (int j = 0; j < layers[i].nodes.length; j++) {
                        if(randomizor.nextInt() % 100 == 1) layers[i].nodes[j].adjustNode();
                    }
                }
            }
        };
    }

}