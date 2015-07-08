package coffeeimport;

import org.parse4j.*;
import org.parse4j.callback.DeleteCallback;
import org.parse4j.callback.FindCallback;
import org.parse4j.callback.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Chris on 6/20/2015.
 * Updated by Adam on 6/22/2015.
 * Updated "        " 7/3/2015.
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


    //TODO Possibly return Parse ObjectID as opposed to boolean value; -1 on failure
    //TODO Make put "Date" functional (Mainly handle Edge Cases)
    public boolean updateShipment(Shipment shipment){

        //ParseFile file;
        //byte[] bytes;
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
        //inventoryShipment.put("LinkedListOfProducts", shipment.getProducts());      //Storing the LinkedList as an Object Object in Parse

        //Todo, implement these lines of code;  At the moment, this seems like the only option
        //bytes = converter.serialize(shipment.getProducts());          //Use this to convert the LinkedList to a byte array so that it can be stored as a ParseFile
        //file = new ParseFile("ProductList.txt", bytes);                   //It appears that the third-party library function that stores Object is disfunctional
        //inventoryShipment.put("Products", file);                      //so this is the easiest method until a fix has been found


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
    //TODO if shipment is deleted, delete it's products
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
                } else {
                    //Todo Represent to user that no such shipment exists
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
        //Todo
        updateShipmentList(product.getShipmentId(), product, false);


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

    public boolean deleteProduct(Product product){      //Todo: modify this so that product will be removed from Shipment inventory list

        boolean productSuccess;
        ParseQuery<ParseObject> query  = ParseQuery.getQuery("Products");
        query.whereEqualTo("ProductID", product.getProductId());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(list!=null && list.size()>=1){                      //There was more than one of this product for some reason; If size === 1 -> Happy Path
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

    public boolean updateShipmentList(String shipment, final Product product, final boolean deletion) {
        boolean updateSuccess;
        ParseQuery<ParseObject> query  = ParseQuery.getQuery("Shipments");
        query.whereEqualTo("ShipmentID", shipment);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e==null && list!=null) {
                    if (list.size() <= 0 || list.size() > 1) {
                        //Todo This shipment does not exist, inform user OR there is more than one shipment with the same ID, this should never be the case
                        success = false;
                    } else {       //Only one shipment matched
                        LinkedList<Product> products;

                        if (!deletion) {        //This is an addition; Append the product to the Shipment's LinkedList

                            //Need to re-adjust strategy
                            //Currently trying to convert LinkedList to a ParseFile via byte array
                            //and then bring it back with the commented out code below. VVV
                            //Currently yields an unhandled exception error that I cannot fix >> Looking for advice

                           /* ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            FileInputStream fis = new FileInputStream(list.get(0).getParseFile("Products").getUrl());

                            byte[] buf = new byte[1024];
                            int n;
                            while (-1 != (n = fis.read(buf)))
                                baos.write(buf, 0, n);

                            byte[] videoBytes = baos.toByteArray(); //this is the video in bytes.
                            */

                            //products = (LinkedList<Product>)list.get(0).get("LinkedListOfProducts");
                           //todo products.add(product);


                        } else {                   //This is a deletion; Remove the product from the LinkedList

                            //These lines will break the code if implemented the way they are.
                            //The third-party library functions appear to be broken for certain features

                            //products = (LinkedList<Product>) list.get(0).get("LinkedListOfProducts");
                            //if(!products.remove(product)){
                                //Product was not in the shipment
                                //Todo represent this to the user
                            //}
                        }

                       //todo list.get(0).put("LinkedListOfProducts", products);
                        list.get(0).saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e!=null){
                                    //Todo represent error
                                    success=false;
                                }
                            }
                        });
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

    /**
     *
     * @param ship
     * This was only a test method to ensure the connection to Parse
     * was stable.  Calling this method successfully creates an Object in
     * a Parse Class "T" with one value, "a", in a column labeled "a".
     * It will be removed before the final web app is complete
     * @return ParseObject that was saved in Background
     */
    /*public ParseObject shipmentCreationExampleMethod(Shipment ship){
        ParseObject inventoryShipment = new ParseObject("T");
        inventoryShipment.put("a", "a");
        inventoryShipment.saveInBackground();
        return inventoryShipment;
    }*/

}
