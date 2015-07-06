package coffeeimport;

/**
 * Created by Chris on 6/13/2015.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
//@RequestMapping("/weightcalc")
public class WeightCalculatorController {

    //weight calculator page where user inputs parameters
    @RequestMapping(value="/weightcalc", method=RequestMethod.GET)
    public String renderCalculator(Model model){
        //add weight calculator object as form backing object
        model.addAttribute("weightCalculator", new WeightCalculator());
        return "weightcalc";
    }

    //post request so that the calculation can be performed server side
    @RequestMapping(value="/weightcalc", method=RequestMethod.POST)
    public  String weightSubmit(@ModelAttribute WeightCalculator weightCalculator, Model model, BindingResult bindingResult){
        //calculate the price per kg
        double pricePerUnit = weightCalculator.calcPricePerUnit(weightCalculator.getShippingCost(),
                weightCalculator.getWeight()) ;

        //show price per kg by adding to model which is displayed on the page
        model.addAttribute("pricePerUnit", pricePerUnit);
        model.addAttribute("shipment", new Shipment());
        return "weightCalculation";
    }
    //updates weight with parse
    @RequestMapping(value="/updateWeight", method=RequestMethod.POST)
    public  String weightUpdate(@ModelAttribute Shipment shipment, Model model, BindingResult bindingResult){

        //halt update if the form has any errors
        if(bindingResult.hasErrors()){
            return "updateFailure";
        }
        //Adds shipment id and price to be displayed on the page
        model.addAttribute("id", shipment.getShipmentId());
        model.addAttribute("price", shipment.getPricePerKg());

        //update to parse
        ParseAccessor parse = new ParseAccessor();
        if(parse.updateShipment(shipment)){
            return "weightUpdated";
        }
        else{
            return "updateFailure";
        }


    }

}
