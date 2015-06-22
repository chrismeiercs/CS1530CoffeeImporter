package coffeeimport;

import org.parse4j.Parse;

import java.util.HashMap;


/**
 * Created by Chris on 6/20/2015.
 */


public class ParseAccessor {
    private String app_id;
    private String app_key;

    /**
     * Access the Parse Application
     */

    public ParseAccessor(String id, String key){
        app_id = id;
        app_key = key;
        Parse.initialize(id,key);
    }

    public ParseAccessor(){
        //Need to assume an id and key;  Using mine until otherwise specified
        Parse.initialize("NyVAmF4GHENom2dWu7mKBGUWk8HAhPpxPSbFcSMP", "cZQI9V32nbWCj29nbH68gknoCtr9hNJnS4HJRUxp");
    }

    public boolean updateShipment(Shipment shipment){
        return true;
    }

    public boolean updateProduct(Product product){
        return true;
    }



    public String getApp_id(){
        return app_id;
    }
    public String getApp_key(){
        return app_key;
    }
}
