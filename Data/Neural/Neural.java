package Data.Neural;

import java.io.Serializable;

public class Neural implements Serializable, Cloneable {

    public Layer[] layers;

    // initialize neural network
    public Neural(int[] layerDiscriptors) {
        layers = new Layer[layerDiscriptors.length - 1];
        for (int i = 1; i < layerDiscriptors.length; i++) {
            layers[i - 1] = new Layer(layerDiscriptors[i - 1], layerDiscriptors[i]);
        }
    }

    // adjust network based off of specified strategy
    public void adjustNetwork(AdjustmentStrategy adjustmentStrategy) {
        adjustmentStrategy.adjust(layers);
    }

    // calculate and return outputs
    public double[] calcNetwork(double[] inputs) {
        double[] outputs = inputs;
        for (int i = 0; i < layers.length; i++) {
            outputs = layers[i].calcLayer(outputs);
        }
        return outputs;
    }
}