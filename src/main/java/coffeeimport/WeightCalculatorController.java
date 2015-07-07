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
    ParseAccessor parseAccessor = new ParseAccessor();
    @RequestMapping(value="/weightcalc", method=RequestMethod.GET)
    public String renderCalculator(Model model){
        //WeightCalculator weightCalc = new WeightCalculator();
        model.addAttribute("weightCalculator", new WeightCalculator());
        return "weightcalc";
    }

    @RequestMapping(value="/weightcalc", method=RequestMethod.POST)
    public  String weightSubmit(@ModelAttribute WeightCalculator weightCalculator, Model model, BindingResult bindingResult){

        double pricePerUnit = weightCalculator.calcPricePerUnit(weightCalculator.getShippingCost(),
                weightCalculator.getWeight()) ;


        model.addAttribute("pricePerUnit", pricePerUnit);
        model.addAttribute("shipment", new Shipment());
        return "weightCalculation";
    }

    @RequestMapping(value="/updateWeight", method=RequestMethod.POST)
    public  String weightUpdate(@ModelAttribute Shipment shipment, Model model, BindingResult bindingResult){
        model.addAttribute("id", shipment.getShipmentId());
        model.addAttribute("price", shipment.getPricePerKg());
        if(parseAccessor.updateShipment(shipment)){
            return "weightUpdated";
        }
        else{
            return "updateFailure";
        }


    }

}
