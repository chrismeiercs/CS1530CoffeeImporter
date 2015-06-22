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
    @Before
    public void setUp() throws Exception{
        shipment = new Shipment();
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


}