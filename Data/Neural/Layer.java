package Data.Neural;

import java.io.Serializable;

public class Layer implements Serializable {
    public Node[] nodes;

    // initialize Layer
    public Layer(int numInputs, int numNodes) {
        nodes = new Node[numNodes];
        for (int i = 0; i < numNodes; i++)
            nodes[i] = new Node(numInputs);
    }

    // generic adjust layer nodes
    public void adjustLayer() {
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].adjustNode();
        }
    }

    // calculate nodes in layer
    public double[] calcLayer(double[] inputs) {
        double[] outputs = new double[nodes.length];
        for (int i = 0; i < outputs.length; i++) {
            outputs[i] = nodes[i].calcNode(inputs);
        }
        return outputs;
    }
}