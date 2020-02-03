package Data.Neural;

import java.io.Serializable;

//neural network strategy
public interface AdjustmentStrategy extends Serializable {
    public void adjust(Layer[] layers);
}