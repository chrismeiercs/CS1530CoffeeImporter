package coffeeimport;

import org.parse4j.Parse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoffeeImportApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeImportApplication.class, args);





        ParseAccessor p = new ParseAccessor("lXpYJrImjyl3YSKDvxX9R6H3GGqIKQrB6WbI6Eu1", "dRwFZFBIxUiHP8nL3JYKsjtJpCLX1SZCl7Zez5C3");


        /*
        *  Please ignore the following but do not delete any of it.
        *  For some reason, Testing Parse function have no effect
        *  and must be used in an actual program file to see results.

        Shipment nop = new Shipment();
        p.shipmentCreationExampleMethod(nop);

        nop.setShipmentId("La mer");
        p.updateShipment(nop);

        //p.deleteShipment(nop);
        */

        Shipment testShipment = new Shipment();
        testShipment.setOrigin("Canada");
        testShipment.setPricePerKg(2.0);
        testShipment.setProductCost(4.1);
        testShipment.setShipmentId("Id");
        testShipment.setShippingCost(16.00);
        testShipment.setTotalCost(21.21);
        //testShipment.setDateReceived(date);
        testShipment.setWeight(7.2);

        p.updateShipment(testShipment);



        Product testProduct = new Product();
        p.updateProduct(testProduct);



    }
}
