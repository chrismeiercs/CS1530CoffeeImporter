package coffeeimport;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.Date;

/**
 * Created by Tron(Adam) on 6/22/2015.
 * Updated by Adam on 6/22/2015.
 */

/**
 * Check "Parse Method Manual Test.v1.txt to see the actual tests performed on
 * the ParseAccessor methods and on Parse in general.
 */

public class ParseAccessorTests {

    ParseAccessor parseAccessor;
    Shipment testShipment;
    Product testProduct;
    Date date;


    /**
     * Tests that id and key values are successfully passed into class ParseAccessor
     */

    /*@Test
    public void returnValues(){
        String id = parseAccessor.getApp_id();
        String key = parseAccessor.getApp_key();
        Assert.assertNotNull(id);
        Assert.assertNotNull(key);

        Assert.assertNotNull(parseAccessor.shipmentCreationExampleMethod(testShipment));
    }*/


    /**
     * Test Verifies that Shipment Object is deleted from Parse Database
     */

    //@Test
    //public void shipmentDeletion(){
      //  Assert.assertTrue(parseAccessor.deleteShipment(testShipment));
    //}

    /**
     * Test Verifies that Product Object is deleted from Parse Database
     */

    @Test
    public void productDeletion(){
        Assert.assertTrue(parseAccessor.deleteProduct(testProduct));
    }
    /**
     * Tests performed on product method
     */
    @Test
    public void productTests(){
        Assert.assertTrue(parseAccessor.updateProduct(testProduct));
    }
}
