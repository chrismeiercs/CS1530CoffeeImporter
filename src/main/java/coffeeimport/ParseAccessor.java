package coffeeimport;

import org.parse4j.Parse;
import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;
import org.parse4j.callback.DeleteCallback;
import org.parse4j.callback.FindCallback;
import org.parse4j.callback.SaveCallback;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Chris on 6/20/2015.
 * Updated by Adam on 6/22/2015.
 */


public class ParseAccessor {
    private String app_id;
    private String app_key;
    private Boolean success = true;
    private ParseObject temp;

    /**
     * Access the Parse Application
     */

    public ParseAccessor(String id, String key){
        app_id = id;
        app_key = key;
        Parse.initialize(id,key);
    }

    public ParseAccessor(){
        //Need to assume an id and key;  Using mine (Adam's) until otherwise specified
        Parse.initialize("lXpYJrImjyl3YSKDvxX9R6H3GGqIKQrB6WbI6Eu1", "dRwFZFBIxUiHP8nL3JYKsjtJpCLX1SZCl7Zez5C3");
        // George's ID & Key
     //   Parse.initialize("lXpYJrImjyl3YSKDvxX9R6H3GGqIKQrB6WbI6Eu1", "tmfM1mnCsFEE9onzPSAqwKubvgKakqPppNx8dnsq");
    }


    /**
     *
     * @param shipment
     * This method takes in a shipment and saves the values to the Parse App Database
     * @return Boolean Value: True if Shipment Object successfully saved to Parse, False if error while saving
     */


    //TODO Possibly return Parse ObjectID as opposed to boolean value; -1 on failure
    //TODO Make put "Data" functional (Mainly handle Edge Cases); Add put function for products Array...Currently a LinkedList in Shipment Class, should convert to Array for easy Parse storage
    public boolean updateShipment(Shipment shipment){

        boolean shipmentSuccess;
        ParseObject inventoryShipment = new ParseObject("Shipments");
        inventoryShipment.put("ShipmentID", shipment.getShipmentId());
        inventoryShipment.put("ProductCost", shipment.getProductCost());
        inventoryShipment.put("TotalCost", shipment.getTotalCost());
        inventoryShipment.put("ShippingCost", shipment.getShippingCost());
        inventoryShipment.put("Weight", shipment.getWeight());
        inventoryShipment.put("Origin", shipment.getOrigin());
       // inventoryShipment.put("Date", shipment.getDateReceived());
        inventoryShipment.put("PricePerKg", shipment.getPricePerKg());

        inventoryShipment.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null){
                    success=false;
                }
            }
        }) ;
            shipmentSuccess = success;
            success = true;
            return shipmentSuccess;         //No error was detected;  Shipment Object successfully saved to Parse
        }

    /**
     *
     * @param shipment
     *   Takes in a Shipment Object, determines if it exists in the Parse database, and deletes it if it does exist
     * @return Boolean value: True if deletion of Shipment Object successful, False if error occurred while deleting in background
     */
    public boolean deleteShipment(Shipment shipment){
        boolean shipmentSuccess;
        ParseQuery<ParseObject> query  = ParseQuery.getQuery("Shipments");
        query.whereEqualTo("ShipmentID", shipment.getShipmentId());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(list.size()>1){
                    //More than one of this shipment...bad JuJu
                    //Todo Cannot assume that user wants to delete every item with the same ShipmentID...Could be a problem
                    for(int i=0;i<list.size();i++){
                        temp = list.get(i);
                        temp.deleteInBackground(new DeleteCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e!=null){
                                    success=false;
                                }
                            }
                        });
                    }
                }
                else if(list.size()==1){
                    temp = list.get(0);
                    temp.deleteInBackground(new DeleteCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e!=null){
                                success=false;
                            }
                        }
                    });
                }
                else{
                    //Todo Represent to user that no such shipment exists
                    success=false;
                }
            }
        });


        shipmentSuccess = success;
        success = true;
        return shipmentSuccess;             //No error detected;  Shipment Object successfully deleted from Parse
    }


    /**
     *
     * @param product
     * This method takes a product and creates a Product ParseObject
     * @return true upon successful object creation
     */

    public boolean updateProduct(Product product){

        boolean productSuccess;
        ParseObject inventoryProduct = new ParseObject("Products");
        inventoryProduct.put("shipmentID",product.getProductId());
        inventoryProduct.put("ProductName",product.getProductName());
        inventoryProduct.put("ProductCost",product.getProductCost());
        inventoryProduct.put("Sold", product.getHasBeenSold());
        inventoryProduct.put("PriceSold", product.getPriceSold());

        inventoryProduct.put("ShipmentID", product.getShipmentId());
        //Todo
        updateShipmentList(product.getShipmentId(), product.getProductId(), false);


        inventoryProduct.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null){
                    success=false;
                }
            }
        }) ;


        productSuccess=success;
        success=true;
        return productSuccess;
    }

    /**
     *
     * @param product
     * This method takes a product object and searches for it within  the Parse data base.
     * If the object exists, it will be deleted.  If it doesn't, need to display to the user that it
     * does not exist.
     * @return True upon successful interaction with Parse
     */

    public boolean deleteProduct(Product product){      //Todo: modify this so that product will be removed from Shipment inventory list

        boolean productSuccess;
        ParseQuery<ParseObject> query  = ParseQuery.getQuery("Products");
        query.whereEqualTo("ProductID", product.getProductId());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(list.size()>1){                      //There was more than one of this product for some reason
                    //Todo Need to establish Product ID'ing format, otherwise cannot assume that user wants to delete every item with the same ProductID...Could be a problem
                    for(int i=0;i<list.size();i++){
                        temp = list.get(i);
                        temp.deleteInBackground(new DeleteCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e!=null){
                                    success=false;
                                }
                            }
                        });
                    }
                }
                else if(list.size()==1){    //One item returned; Happy Path
                    temp = list.get(0);
                    temp.deleteInBackground(new DeleteCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e!=null){
                                success=false;




                            }
                        }
                    });
                }
                else{
                    //Todo Represent to user that no such product exists
                    success = false;
                }
            }
        });


        productSuccess = success;
        success = true;
        return productSuccess;             //No error detected;  Shipment Object successfully deleted from Parse
    }

    /**
     * Upon creating a new product, its ID will be added to the array of its corresponding shipment in Parse
     * OR if this was called during the deletion of the product, this will remove the product from the
     *    Shipment's array in Parse
     * @param shipment - String Id of the Shipment
     * @param product - String Id of the product
     * @param deletion - Boolean denoting if this is addition or deletion
     * @return Returns true if there were no errors
     */

    private boolean updateShipmentList(String shipment, final String product, final boolean deletion){
        boolean updateSuccess;
        ParseQuery<ParseObject> query  = ParseQuery.getQuery("Shipments");
        query.whereEqualTo("ShipmentID", shipment);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e==null && list!=null) {
                    if (list.size() <= 0) {
                        //Todo This shipment does not exist, inform user
                        success = false;
                    } else if (list.size() > 1) {
                        //Todo Handle this case where there is more than one shipment with that ID
                        success = false;
                    } else {       //Only one shipment matched
                        if (!deletion) {        //This is an addition; Append the product to the Shipment's product array in Parse
                            list.get(0).add("Products", product);
                        } else {                   //This is a deletion; Remove the product from the list
                            list.get(0).removeAll("Products", Arrays.asList(product));
                        }
                    }
                }
                else{   //If this is the case, something bad has happened;  One such case, no such shipment exists
                    success = false;
                }
            }
        });

        updateSuccess = success;
        success = true;
        return updateSuccess;
    }

    public ParseObject shipmentCreationExampleMethod(Shipment ship){
        ParseObject inventoryShipment = new ParseObject("T");
        inventoryShipment.put("a", "a");
        inventoryShipment.saveInBackground();
        return inventoryShipment;
    }

    /**
     * @return Parse App ID
     */

    public String getApp_id(){
        return app_id;
    }

    /**
     * @return  Parse API Key
     */

    public String getApp_key(){
        return app_key;
    }
}
