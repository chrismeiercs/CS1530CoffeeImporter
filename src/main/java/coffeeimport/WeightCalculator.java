package coffeeimport;

/**
 * Created by Chris on 6/13/2015.
 */
public class WeightCalculator {

    private double shippingCost;
    private double weight;
    private String weightUnit;

    public double getShippingCost() {
        return shippingCost;
    }

    public double getWeight() {
        return weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }
}
