package coffeeimport;


import org.springframework.web.bind.annotation.ModelAttribute;
//import java.util.Date; 
import java.util.LinkedList;


/**
 * Created by Chris on 6/20/2015. 
 * George Totolos 6/22/2015
 * Copied from George's Branch 6/29/2015
 * Modified by Adam 7/2/2015
 *
 */
public class Product
{
    private String productId = "";
    private String shipmentId = "";
    private String productName = "";
    private double productCost = -1;
    private double priceListedForSale = -1;
    private boolean hasBeenSold = false;
    private double priceSold = -1;
    private double productWeight = -1;

    public Product(boolean productHasBeenSold, double priceProductWasSoldAt){ //Constructor with initial conditions
        hasBeenSold = productHasBeenSold;
        priceSold = priceProductWasSoldAt;
    }

    public Product(){}

    /**
     *  Getter Methods
     */

    public String getProductId()    { return productId;}
    public String getShipmentId()   { return shipmentId; }
    public String getProductName()  { return productName; }
    public double getProductCost()   { return productCost; }
    public boolean getHasBeenSold() { return hasBeenSold; }
    public double getPriceSold()    { return priceSold; }
    public double getProductWeight()   { return productWeight; }
    public double getPriceListedForSale() { return  priceListedForSale; }


    /**
    *  Setter Methods
    */

    public void setProductId(String productId)      { this.productId = productId;}
    public void setShipmentId(String shipmentId)    { this.shipmentId = shipmentId; }
    public void setProductName(String productName)  { this.productName = productName;}
    public void setProductCost(double productCost)   { this.productCost = productCost; }
    public void setHasBeenSold(boolean hasBeenSold) { this.hasBeenSold = hasBeenSold; }
    public void setPriceSold(double priceSold)      { this.priceSold = priceSold; }
    public void setProductWeight(double weight)     { this.productWeight = weight; }
    public void setPriceListedForSale(double priceListed)  { this.priceListedForSale = priceListed; }

}