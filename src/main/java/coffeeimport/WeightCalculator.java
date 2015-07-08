package coffeeimport;

import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by Chris on 6/13/2015.
 */

/**
 * Weight Calculator Model
 */
public class WeightCalculator {

    private double shippingCost;
    private double weight;
    private String weightUnit;

    /**
     * Gets the shipping cost used in the calculation
     * @return shipping cost
     */

    @ModelAttribute("shippingCost")
    public double getShippingCost() {
        return shippingCost;
    }

    /**
     * Get the weight in Kg used in the calculation
     * @return weight of shipment in Kg
     */

    @ModelAttribute("weight")
    public double getWeight() {
        return weight;
    }


    /**
     * Set shipping cost for calculation
     * @param shippingCost
     */

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    /**
     * Set weight for shipment in calculation
     * @param weight in Kg
     */

    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     *
     * @param cost Cost of the shipment in dollars
     * @param weight Weight of the shipment in Kg
     * @throws IllegalArgumentException The shipment weight must be greater than zero to prevent a divide by zero error
     * @return Cost per Kg of shipment
     */
    public double calcPricePerUnit(double cost, double weight){
        if(weight <= 0){
            throw new IllegalArgumentException("Shipment weight must be greater than zero");
        }

        return cost/weight;
    }
}
