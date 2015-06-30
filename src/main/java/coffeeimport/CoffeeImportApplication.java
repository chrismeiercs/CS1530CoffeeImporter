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
        */
        Shipment nop = new Shipment();
        p.shipmentCreationExampleMethod(nop);

        nop.setShipmentId("Le'mare");
        p.updateShipment(nop);

        p.deleteShipment(nop);


    }
}
