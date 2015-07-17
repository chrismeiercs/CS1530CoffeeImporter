package coffeeimport;

import org.parse4j.*;
import org.parse4j.ParseException;
import org.parse4j.callback.DeleteCallback;
import org.parse4j.callback.FindCallback;
import org.parse4j.callback.LoginCallback;
import org.parse4j.callback.SaveCallback;

import javax.security.auth.login.LoginException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Chris on 6/20/2015.
 * Updated by Adam on 6/22/2015.
 * Updated "        " 7/3/2015.
 * Updated "        " 7/14/2015.
 */


/**
 *  For any function that accesses Parse, any method that looks like:
 *   @Override
 *   public void done([parameter], ParseException e){...}
 *
 *   The object "e" is the specific exception returned in the event that
 *   the interaction with Parse was unsuccessful in some way.
 *
 *   This is usually followed by an if statement like: if(e!=null)
 *
 *   If this if statement is true, then there was a failure and proper actions should be taken
 *   Else, the interaction with Parse was successful.
 *
 */




public class ParseAccessor {
    private String app_id;
    private String app_key;
    private Boolean success = true;
    private ParseObject temp;

    /**
     * Access the Parse Application
     */

    public ParseAccessor(String id, String key){    //If a different Parse database is used
        app_id = id;
        app_key = key;
        Parse.initialize(id,key);
    }

    public ParseAccessor(){
        //Need to assume an id and key;  Using mine (Adam's) until otherwise specified
        Parse.initialize("", "");               //Removed App ID and Key for safety reasons
    }

    /**
     *
     * @param shipment
     * This method takes in a shipment and saves the values to the Parse App Database
     * @return Boolean Value: True if Shipment Object successfully saved to Parse, False if error while saving
     */


    public boolean updateShipment(Shipment shipment){


        boolean shipmentSuccess;
        ParseObject inventoryShipment = new ParseObject("Shipments");
        inventoryShipment.put("ShipmentID", shipment.getShipmentId());
        inventoryShipment.put("ProductCost", shipment.getProductCost());
        inventoryShipment.put("TotalCost", shipment.getTotalCost());
        inventoryShipment.put("ShippingCost", shipment.getShippingCost());
        inventoryShipment.put("Weight", shipment.getWeight());
        inventoryShipment.put("Origin", shipment.getOrigin());
        inventoryShipment.put("DateReceived", shipment.getDateReceived());
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
        String id = shipment.getShipmentId();

        ParseQuery<ParseObject> query  = ParseQuery.getQuery("Shipments");
        query.whereEqualTo("ShipmentID", id);
        query.findInBackground(new FindCallback<ParseObject>() {        //Query for the shipment with the right ID
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (list!=null && list.size() >= 1) {   //Happy path: size == 1
                    //More than one of this shipment...bad JuJu
                    //However, ShipmentIDs should be unique, so there should never be more than one with the same ID
                    for (int i = 0; i < list.size(); i++) {
                        temp = list.get(i);
                        temp.deleteInBackground(new DeleteCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e != null) {
                                    success = false;
                                }
                            }
                        });
                    }
                } else {//Considering we are switching to inline deletion, the list should always have an item and never see this else case
                    success = false;
                }
            }
        });

        if(success){
            query = new ParseQuery<ParseObject>("Products");
            query.whereEqualTo("ShipmentID", id);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if(e==null && list!=null && list.size()>0){      //There are products associated with this shipment and there were no errors retrieving them
                        for(int i=0;i<list.size();i++){
                            list.get(i).deleteInBackground();   //Delete each Product stored in Parse associated with the Shipment
                        }
                    }
                }
            });
        }

        shipmentSuccess = success;
        success = true;
        return shipmentSuccess;             //No error detected;  Shipment Object successfully deleted from Parse
    }


    /**
     *
     * @param product
     * This method takes a product and creates a Product ParseObject with all the values of the object
     * @return true upon successful object creation
     */

    public boolean updateProduct(Product product){

        boolean productSuccess;
        ParseObject inventoryProduct = new ParseObject("Products");     //These lines of code are responsible for giving values to the Parse Product Object
        inventoryProduct.put("ProductID",product.getProductId());
        inventoryProduct.put("ProductName",product.getProductName());
        inventoryProduct.put("ProductCost",product.getProductCost());
        inventoryProduct.put("Sold", product.getHasBeenSold());
        inventoryProduct.put("PriceSold", product.getPriceSold());
        inventoryProduct.put("Weight", product.getProductWeight());
        inventoryProduct.put("PriceListedForSale", product.getPriceListedForSale());

        inventoryProduct.put("ShipmentID", product.getShipmentId());



        inventoryProduct.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {        //This saves the object to Parse
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

    public boolean deleteProduct(Product product){

        boolean productSuccess;
        ParseQuery<ParseObject> query  = ParseQuery.getQuery("Products");
        query.whereEqualTo("ProductID", product.getProductId());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(list!=null && list.size()>=1){                      //There was more than one of this product for some reason; If size === 1 -> Happy Path
                    for(int i=0;i<list.size();i++){                     //Same case as above.  Switching to inline deletion
                        temp = list.get(i);
                        temp.deleteInBackground(new DeleteCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e!=null){        //There was an error in deleting
                                    success=false;
                                }
                            }
                        });
                    }
                }
                else{   //Considering we are switching to inline deletion, the list should always have an item and never see this else case
                    success = false;
                }
            }
        });


        productSuccess = success;
        success = true;
        return productSuccess;             //No error detected;  Shipment Object successfully deleted from Parse
    }

    /**
     * This method checks that the person trying to access the data base is actually
     * someone allowed to.
     * @param username  Username address of the user
     * @param password  The user's password
     * @return  Returns corresponding ParseUser if one exists, else returns null indicating a lack of such
     */
    public ParseUser verifyParseUser(String username, String password) throws Exception{
        boolean result;
        ParseUser user;
        try {           //Attempt to login
            user = ParseUser.login(username, password);
        }
        catch (ParseException e){       //Indicates error in logging in
            throw new LoginException("Incorrect Username and/or Password");
        }
        return user;
    }

}
