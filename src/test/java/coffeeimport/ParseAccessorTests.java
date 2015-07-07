package coffeeimport;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.parse4j.Parse;

import java.util.Date;

/**
 * Created by Tron(Adam) on 6/22/2015.
 * Updated by Adam on 6/22/2015.
 */
public class ParseAccessorTests {

    ParseAccessor parseAccessor;
    Shipment testShipment;
    Product testProduct;
    Date date;

    /**
     * Initialize objects necessary to run tests
     * @throws Exception in the even anything went wonky
     */
    @Before
    public void instantiate() throws Exception{
        // Adam's ID:
        parseAccessor = new ParseAccessor("lXpYJrImjyl3YSKDvxX9R6H3GGqIKQrB6WbI6Eu1", "dRwFZFBIxUiHP8nL3JYKsjtJpCLX1SZCl7Zez5C3");
        testShipment = new Shipment();
        testProduct = new Product();
       // date.setTime(1000000000);

        testShipment.setOrigin("Canada");
        testShipment.setPricePerKg(2.0);
        testShipment.setProductCost(4.1);
        testShipment.setShipmentId("Id");
        testShipment.setShippingCost(16.00);
        testShipment.setTotalCost(21.21);
        testShipment.setDateReceived(date);
        testShipment.setWeight(7.2);

        parseAccessor.updateShipment(testShipment);
    }

    /**
     * Tests that id and key values are successfully passed into class ParseAccessor
     */

    @Test
    public void returnValues(){
        String id = parseAccessor.getApp_id();
        String key = parseAccessor.getApp_key();
        Assert.assertNotNull(id);
        Assert.assertNotNull(key);

        Assert.assertNotNull(parseAccessor.shipmentCreationExampleMethod(testShipment));
    }

    /**
     * Tests performed on shipment method
     */

    @Test
    public void shipmentTests(){
        //String shipmentName = null;

        //Assert.assertNotNull(shipmentName);
        testShipment.updateShipment();

        //Assert.assertNotNull(parseAccessor.updateShipment(testShipment));
        Assert.assertTrue(parseAccessor.updateShipment(testShipment));
    }

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