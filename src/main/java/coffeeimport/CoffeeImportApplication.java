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

       /* Shipment testShipment = new Shipment();
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
        */

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Initialize Objects
        /**Shipment testShipment = new Shipment();
        Product productA = new Product();
        Product productB = new Product();


        //Set ProductA's values
        productA.setProductCost(7.99);
        productA.setHasBeenSold(false);
        productA.setProductName("Tijuana's Finest Beans");
        productA.setProductId("XYZ");
        productA.setProductWeight(4.02);

        //Set ProductB's values
        productB.setProductCost(8.95);
        productB.setHasBeenSold(false);
        productB.setProductName("Coffee Crafters' Best");
        productB.setProductId("ABC");
        productB.setProductWeight(3.80);

        //Set Shipment Values
        testShipment.setOrigin("Mexico");
        testShipment.setWeight((double)Math.round(1000.0*(productA.getProductWeight()+productB.getProductWeight()))/1000);        //Combined weight
        testShipment.setProductCost((double) Math.round(100.0*(productA.getProductCost()+productB.getProductCost()))/100);       //Combined price
        testShipment.setPricePerKg((double)Math.round(100.0*testShipment.getProductCost()/testShipment.getWeight())/100);
        testShipment.setShipmentId("Id.123");
        testShipment.setTotalCost(24.91);
        testShipment.setShippingCost((double) Math.round(100*(testShipment.getTotalCost()-testShipment.getProductCost()))/100);    ///Todo For some reason Shipment.calculateShippingCost() isn't working

        //Add Products to shipment's list and set products' shipmentID
        testShipment.addProductToShipment(productA);
        testShipment.addProductToShipment(productB);

        productA.setShipmentId(testShipment.getShipmentId());
        productB.setShipmentId(testShipment.getShipmentId());

        //Send Everything to Parse
        p.updateShipment(testShipment);
        p.updateProduct(productA);
        p.updateProduct(productB);

        //Verify that the following objects have made it to Parse
////////////////////////////////////////////////////////////////////////////////

        /**Results in Parse:
         *  7/2/15
         * In Shipments Class:
         *
         *  objectId: Ow6TxIC1ko
         *  ShipmentID: Id.123
         *  createdAt: 2015-07-02T05:52:47.384Z
         *  updatedAt: 2015-07-02T05:52:47.384Z
         *  PricePerKg: 2.17
         *  Origin:  Mexico
         *  Weight: 7.82
         *  ShippingCost:  7.97
         *  TotalCost:  24.91
         *  ProductCost: 16.94
         *  Products: Undefined
         *  DateReceived: Undefined
         *
         *
         * In Products Class:
         *
         *  (ProductA)
         *  objectId: liBQ116wRn
         *  ProductID: XYZ
         *  createdAt: 2015-07-02T05:52:47.400Z
         *  updatedAt: 2015-07-02T05:52:47.400Z
         *  ShipmentID: Id.123
         *  ProductCost: 7.99
         *  Sold: false
         *  PriceSold: -1
         *  ProductName: Tijuana's Finest Beans
         *
         *  (ProductB)
         *  objectId: nmNsTMJds5
         *  ProductID: ABC
         *  createdAt: 2015-07-02T05:52:47.399Z
         *  updatedAt: 2015-07-02T05:52:47.399Z
         *  ShipmentID: Id.123
         *  ProductCost: 8.95
         *  Sold: false
         *  PriceSold: -1
         *  ProductName: Coffee Crafters' Best
         *
         */





    }
}
