package coffeeimport;/**
 * Created by Chris on 6/21/2015.
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CoffeeImportApplication.class)
@WebAppConfiguration
public class NewShipmentTests {

    Shipment shipment;
    Product product;
    @Before
    public void setUp() throws Exception{
        shipment = new Shipment();
        product = new Product();
    }

    @Test(expected = Exception.class)
    public void productCostGreaterThanTotalCost() throws Exception {
        shipment.setProductCost(10);
        shipment.setTotalCost(9);
        shipment.calculateShippingCost();
    }

    @Test
    public void calculateShippingCostTest() throws Exception {
        shipment.setTotalCost(10);
        shipment.setProductCost(5);
        double shipmentCost = shipment.calculateShippingCost();
        Assert.assertEquals(5,shipmentCost,0);
    }

    /**
     * Tests if Product can be added to a Shipment list
     * @throws Exception
     * Fails if Product is not successfully added
     */
    @Test
    public void addProductToShipmentListTest() throws Exception{
        Assert.assertTrue(shipment.addProductToShipment(product));


    }

    /**
     * Test method checks that null case Date is properly generated for new Shipment Class
     * @throws Exception
     */
    @Test
    public void testDefaultShipmentDate() throws Exception{
        Assert.assertTrue(shipment.getDateReceived().toString().equals("Wed Dec 31 00:00:00 EST 2"));
    }
}