package coffeeimport;

import org.parse4j.ParseObject;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Chris on 6/20/2015.
 * Updated by Adam on 6/22/2015.
 * "                " 6/29/2015.
 */
public class Shipment {
    /**
     * Instantiate each case in the event one is not given a value
     * Prevents NullPointerExceptions...Will remove values once
     * a better empty case handling solution is implement.
     */

    private String shipmentId ="";
    private double pricePerKg = -1;
    private String origin = "";
    private double weight = -1;
    private double shippingCost = -1;
    private double totalCost = -1;
    private LinkedList<Product> products = new LinkedList<Product>();
    private double productCost = -1;
    private Date dateReceived = parseDate("1200-01-01");    // 1200 Jan 1 00:00:00 EST 2


    //Methods that use this method need to be able to throw an exception as well
    public double calculateShippingCost() throws Exception {

        if(this.productCost > this.totalCost){
            throw new Exception("Product costs can't be greater than the total cost");
        }

        this.shippingCost = this.totalCost - this.productCost;

        return this.shippingCost;
    }

    /**
     * This are all of our Setter and getter methods
     */

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

    public LinkedList<Product> getProducts(){ return products; }

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


    /**
     *
     * @param product - Product to be added this shipment's inventory
     *          Also, updates product's shipmentId if it has not already been set
     * @return  True upon successful addition; False upon Failure
     */

    public boolean addProductToShipment(Product product){
        boolean result = false;

        products.add(product);
        if(products.add(product)){
            result = true;
        }
        return result;
    }

    /**
     * This method is used to generate the default date object
     * @param date this is the String date that will be converted into a Date Object
     * @return On success, returns a Date object created from the String parameter.
     *          On failure, returns a null Date object.
     */
    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }



}
