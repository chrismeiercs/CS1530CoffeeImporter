package coffeeimport;

import org.parse4j.Parse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoffeeImportApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeImportApplication.class, args);





        ParseAccessor p = new ParseAccessor("NyVAmF4GHENom2dWu7mKBGUWk8HAhPpxPSbFcSMP", "cZQI9V32nbWCj29nbH68gknoCtr9hNJnS4HJRUxp"); //Keys no longer valid


        /*
        *  Please ignore the following but do not delete any of it.
        *  For some reason, Testing Parse function have no effect
        *  and must be used in an actual program file to see results.
        *
        Shipment nop = new Shipment();
        p.shipmentCreationExampleMethod(nop);

        nop.setShipmentId("Le'mare");
        p.updateShipment(nop);

        p.deleteShipment(nop);
        */

    }
}
