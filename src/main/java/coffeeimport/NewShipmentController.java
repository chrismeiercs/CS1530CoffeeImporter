package coffeeimport;/**
 * Created by Chris on 6/21/2015.
 */

import org.springframework.beans.propertyeditors.CurrencyEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.text.SimpleDateFormat;
import org.springframework.web.bind.WebDataBinder;

import java.util.Currency;
import java.util.Date;

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

        //if the form is incorrect
        System.out.println(bindingResult.hasErrors());
        if(bindingResult.hasErrors()){
            return "main";
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

    //sets rules for dateReceived field. If the date is not formatted correctly, an error will occur
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        //Add a currency validator was added to the fields
        binder.registerCustomEditor(Currency.class, "shippingCost", new CurrencyEditor());
        binder.registerCustomEditor(Currency.class, "productCost", new CurrencyEditor());
        }

}