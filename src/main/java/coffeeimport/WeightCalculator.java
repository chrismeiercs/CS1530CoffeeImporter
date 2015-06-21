package coffeeimport;

import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by Chris on 6/13/2015.
 */
public class WeightCalculator {

    private double shippingCost;
    private double weight;
    private String weightUnit;

    @ModelAttribute("shippingCost")
    public double getShippingCost() {
        return shippingCost;
    }

    @ModelAttribute("weight")
    public double getWeight() {
        return weight;
    }

    @ModelAttribute("units")
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
