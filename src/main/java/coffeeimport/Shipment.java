package coffeeimport;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Date;

/**
 * Created by Chris on 6/20/2015.
 */
public class Shipment {

    private String shipmentId;
    private double pricePerKg;
    private Date dateRecieved;
    private String origin;
    private double weight;

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

    public String getShipmentId() {
        return shipmentId;
    }

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

    public boolean updateShipment(){

        return true;
    }
}
