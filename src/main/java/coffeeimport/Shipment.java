package coffeeimport;

import org.springframework.format.annotation.DateTimeFormat;
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
     * Prevents NullPointerExceptions
     */

    private String shipmentId ="";
    private double pricePerKg = -1;
    private String origin = "";
    private double weight = -1;
    private double shippingCost = -1;
    private double totalCost = -1;
    private LinkedList<Product> products = new LinkedList<Product>();
    private double productCost = -1;
    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Date dateReceived = parseDate("1200-01-01");    // 1200 Jan 1 00:00:00 EST 2

    //throws exception so that error can be caught and easily displayed to the user
    //Check is more natural to happen here
    /**
        @return calculated shipping cost for this instance
        @throws Exception The cost of the products can not be greater that the total cost of the shipment
     **/
    
    public double calculateShippingCost() throws Exception {

        if(this.productCost > this.totalCost){
            throw new Exception("Product costs can't be greater than the total cost");
        }

        this.shippingCost = this.totalCost - this.productCost;

        return this.shippingCost;
    }

    public LinkedList<Product> getProducts(){ return products; }

    /**
     *
     * @param product - Product to be added this shipment's inventory
     *          Also, updates product's shipmentId if it has not already been set
     * @return  True upon successful addition; False upon Failure
     */
    //Todo Might need modification
    public boolean addProductToShipment(Product product){
        boolean result = false;

        if(product.getShipmentId().equals("")){     // If the product's shipment ID was not set beforehand,
            product.setShipmentId(shipmentId);      // set the product's shipmentID to the shipment it was added to.
        }                                           // Todo This is only here until the proper empty value handling code is made.  Good for an edge case

        products.add(product);
        if(products.add(product)){
            result = true;
        }
        return result;
    }

    /**
     * Get the cost of all products from this shipment
     * @return  Cost of all products summed together
     */

    public double getProductCost() {
        return productCost;
    }

    /**
     * Sets the summed cost of all products in the shipment
     * @param productCost Amount of money the product cost to acquire
     */

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

    /**
     * Return the shipment cost of this shipment
     * @return shipping cost of the shipment
     */

    public double getShippingCost() {
        return this.shippingCost;
    }

    /**
     * Gets the total cost of this shipment
     * @return total cost of shipment
     */
    public double getTotalCost(){
        return this.totalCost;
    }

    /**
     * Sets the total cost of the shipment
     * @param totalCost total cost of the shipment
     */
    public void setTotalCost(double totalCost){
        this.totalCost = totalCost;
    }

    /**
     * Set the weight of the shipment in Kg
     * @param weight weight of the shipment in Kg
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Get the weight of the shipment
     * @return weight of the shipment
     */

    public double getWeight() {
        return weight;
    }

    /**
     * Get the date the shipment was received
     * @return date received
     */
    public Date getDateReceived() {
        return dateReceived;
    }

    /**
     * Get the price per Kg of the shipment
     * @return price per Kg
     */
    @ModelAttribute("pricePerKg")
    public double getPricePerKg() {
        return pricePerKg;
    }

    /**
     * Get where the shipment was sent from
     * @return location where the shipment was sent from
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Get the shipment id
     * @return shipment id
     */
    public String getShipmentId() {
        return shipmentId;
    }

    public LinkedList<Product> getProducts(){ return products; }

    /**
     * Sets the date the shipment was received
     * @param dateReceived Date the shipment was received
     */
    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    /**
     * Sets the origin for the shipment
     * @param origin Name of the shipment's origin
     */

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Set the price per Kg to either the calculated price or a price desired by the user
     * @param pricePerKg price per kg for the shipment products
     */

    public void setPricePerKg(double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    /**
     * Set the shipment id to the one specified by the user
     * @param shipmentId id of the shipment
     */

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }
    
    /**
     * Set the shipping cost of the shipment
     * @param shippingCost cost of shipping
     */
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
     * Necessary to prevent odd occurrences in Parse;  Helps follow Null Object Pattern
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
