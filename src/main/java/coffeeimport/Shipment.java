package coffeeimport;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Chris on 6/20/2015.
 */
public class Shipment {

    private String shipmentId;
    private double pricePerKg;
    private Date dateRecieved;
    private String origin;
    private double weight;
    private double shippingCost;
    private double totalCost;
    private LinkedList<Product> products;
    private double productCost;

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

    public Date getDateRecieved() {
        return dateRecieved;
    }

    @ModelAttribute("pricePerKg")
    public double getPricePerKg() {
        return pricePerKg;
    }

    public String getOrigin() {
        return origin;
    }

    public String getShipmentId() { return shipmentId; }

    public void setDateRecieved(Date dateRecieved) {
        this.dateRecieved = dateRecieved;
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
