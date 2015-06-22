package coffeeimport;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by Tron on 6/22/2015.
 */
public class ParseAccessorTests {

    ParseAccessor parseAccessor;
    Shipment testShipment;
    Product testProduct;

    /**
     * Initialize objects necessary to run tests
     * @throws Exception in the even anything went wonky
     */
    @Before
    public void instantiate() throws Exception{
        parseAccessor = new ParseAccessor("NyVAmF4GHENom2dWu7mKBGUWk8HAhPpxPSbFcSMP", "cZQI9V32nbWCj29nbH68gknoCtr9hNJnS4HJRUxp");
        testShipment = new Shipment();
        testProduct = new Product();
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
    }

    /**
     * Tests performed on shipment method
     */

    @Test
    public void shipmentTests(){
        //String shipmentName = null;

        //Assert.assertNotNull(shipmentName);


        Assert.assertTrue(parseAccessor.updateShipment(testShipment));
    }

    /**
     * Tests performed on product method
     */
    @Test
    public void productTests(){
        Assert.assertTrue(parseAccessor.updateProduct(testProduct));
    }
}
