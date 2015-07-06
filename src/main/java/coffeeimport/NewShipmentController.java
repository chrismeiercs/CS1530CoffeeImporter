package coffeeimport;/**
 * Created by Chris on 6/21/2015.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class NewShipmentController {
    //user enters in new shipment
    @RequestMapping(value="/newshipment",method = RequestMethod.GET)
    public String beginShipment(Model model) {
        /*sends a shipment object as a form backing object
        The form fields will then be set in the shipment object
         */
        model.addAttribute("shipment", new Shipment());
        return "newShipment";
    }
    //update shipment
    @RequestMapping(value="/newshipment", method=RequestMethod.POST)
    public String calcShipmentCosts(@ModelAttribute Shipment shipment, Model model, BindingResult bindingResult){

        //check date formatted correctly
        String date = shipment.getDateReceived();
        String[] dateSplit = date.split("/");
        String month = dateSplit[0];
        String day = dateSplit[1];
        String year = dateSplit[2];



        if(bindingResult.hasErrors() && (month.length() == 2) && (day.length() == 2) && (year.length() == 4)){
            return "updateFailure";
        }


        //takes the shipment object that was the form backing object
        double shippingCost;
        try {
            shippingCost = shipment.calculateShippingCost();

        } catch (Exception e) { //displays exception as error to the user
            shippingCost = 0;
            model.addAttribute("errorMessage", e.getMessage());
        }
        double percentageOfCost = shippingCost/shipment.getTotalCost();

        //adds shipment variables to the model of the page
        model.addAttribute("id", shipment.getShipmentId());
        model.addAttribute("totalCost", shipment.getTotalCost());
        model.addAttribute("productCost", shipment.getProductCost());
        model.addAttribute("shippingCost", shipment.getShippingCost());
        model.addAttribute("percentCost", percentageOfCost*100);
        model.addAttribute("origin", shipment.getOrigin());
        model.addAttribute("dateReceived", shipment.getDateReceived());

        //updates to parse
        ParseAccessor parse = new ParseAccessor();
        if(parse.updateShipment(shipment)){
            return "shipmentcalc";
        }


       else{
            return "updateFailure";
        }
    }

}