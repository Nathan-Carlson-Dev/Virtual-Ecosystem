package Data.Neural;

//neural network strategy
public interface AdjustmentStrategy {
    public void adjust(Layer[] layers);
}