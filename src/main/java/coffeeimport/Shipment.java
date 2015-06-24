package coffeeimport;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Chris on 6/20/2015.
 * Updated by Adam on 6/22/2015.
 */
public class Shipment {
    /**
     * Instantiate each case in the event one is not given a value
     */

    private String shipmentId ="";
    private double pricePerKg = -1;
    private Date dateReceived = null;
    private String origin = "";
    private double weight = -1;
    private double shippingCost = -1;
    private double totalCost = -1;
    private LinkedList<Product> products;
    private double productCost = -1;

    public double calculateShippingCost() throws Exception {

        if(this.productCost > this.totalCost){
            throw new Exception("Product costs can't be greater than the total cost");
        }

        this.shippingCost = this.totalCost - this.productCost;

        return this.shippingCost;
    }

    public double getProductCost() {
        return productCost;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

    public double getShippingCost() {
        return this.shippingCost;
    }

    public double getTotalCost(){
        return this.totalCost;
    }

    public void setTotalCost(double totalCost){
        this.totalCost = totalCost;
    }


    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    @ModelAttribute("pricePerKg")
    public double getPricePerKg() {
        return pricePerKg;
    }

    public String getOrigin() {
        return origin;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setPricePerKg(double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public void setShippingCost(double shippingCost){
        this.shippingCost = shippingCost;
    }



    public boolean updateShipment(){

        return true;
    }
}
