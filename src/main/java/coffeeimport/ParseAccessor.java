package coffeeimport;

import org.parse4j.Parse;
import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;
import org.parse4j.callback.DeleteCallback;
import org.parse4j.callback.FindCallback;
import org.parse4j.callback.SaveCallback;

import java.util.HashMap;
import java.util.List;


/**
 * Created by Chris on 6/20/2015.
 * Updated by Adam on 6/22/2015.
 */


public class ParseAccessor {
    private String app_id;
    private String app_key;
    private Boolean shipmentSuccess = true;
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
        Parse.initialize("NyVAmF4GHENom2dWu7mKBGUWk8HAhPpxPSbFcSMP", "cZQI9V32nbWCj29nbH68gknoCtr9hNJnS4HJRUxp");
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

        ParseObject inventoryShipment = new ParseObject("Shipments");
        inventoryShipment.put("ShipmentID", shipment.getShipmentId());
        inventoryShipment.put("ProductCost", shipment.getProductCost());
        inventoryShipment.put("TotalCost", shipment.getTotalCost());
        inventoryShipment.put("ShippingCost", shipment.getShippingCost());
        inventoryShipment.put("Weight", shipment.getWeight());
        inventoryShipment.put("Origin", shipment.getOrigin());
       // inventoryShipment.put("Date", shipment.getDateRecieved());
        inventoryShipment.put("PricePerKg", shipment.getPricePerKg());

        inventoryShipment.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null){
                    shipmentSuccess=false;
                }
            }
        }) ;
            return shipmentSuccess;         //No error was detected;  Shipment Object successfully saved to Parse
        }

    /**
     *
     * @param shipment
     *   Takes in a Shipment Object, determines if it exists in the Parse database, and deletes it if it does exist
     * @return Boolean value: True if deletion of Shipment Object successful, False if error occurred while deleting in background
     */
    public boolean deleteShipment(Shipment shipment){
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
                                    shipmentSuccess=false;
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
                                shipmentSuccess=false;
                            }
                        }
                    });
                }
                else{
                    //Todo Represent to user that no such shipment exists
                }
            }
        });



        return shipmentSuccess;             //No error detected;  Shipment Object successfully deleted from Parse
    }


    public boolean updateProduct(Product product){
        return true;
    }

    public boolean deleteProduct(Product product){
        return true;
    }


    public ParseObject shipmentCreationExampleMethod(Shipment ship){
        ParseObject inventoryShipment = new ParseObject("T");
        inventoryShipment.put("a", "a");
        inventoryShipment.saveInBackground();
        return inventoryShipment;
    }

    public String getApp_id(){
        return app_id;
    }
    public String getApp_key(){
        return app_key;
    }
}
