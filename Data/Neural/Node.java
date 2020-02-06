package Data.Neural;

import java.io.Serializable;
import java.util.*;

public class Node implements Serializable, Cloneable {
    public double[] weights;
    public double bias = 0;

    // init Node
    public Node(int numWeights) {
        weights = new double[numWeights];
        for (int i = 0; i < numWeights; i++)
            weights[i] = 0;
    }

    // calculate node output
    public double calcNode(double[] inputs) {
        try {
            double sum = bias;
            for (int i = 0; i < weights.length; i++)
                sum += weights[i] * inputs[i];
            sum = sig(sum);
            return sum;
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        return 0;
    }

    // adjust weights and biases
    public void adjustNode() {
        try {
            Random r = new Random();
            if (r.nextInt() % 2 == 0)
                bias += r.nextDouble();
            else
                bias -= r.nextDouble();
            for (int i = 0; i < weights.length; i++) {
                if (r.nextInt() % 2 == 0)
                    weights[i] += r.nextDouble();
                else
                    weights[i] -= r.nextDouble();
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    // sigmoid function
    public double sig(double x) {
        return 1 / (1 + Math.pow(Math.E, -x));
    }
}