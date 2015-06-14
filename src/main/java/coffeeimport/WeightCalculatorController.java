package coffeeimport;

/**
 * Created by Chris on 6/13/2015.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weightcalc")
public class WeightCalculatorController {
    @RequestMapping(method=RequestMethod.GET)
    public String renderCalculator(Model model){
        model.addAttribute("weightCalc",new WeightCalculator());
        return "weightcalc";
    }

    @RequestMapping(value="/weightcalc", method=RequestMethod.POST)
    public String weightSubmit(@ModelAttribute WeightCalculator weightCalc, Model model){
        model.addAttribute("weightCalc", weightCalc);
        return "weightCalculation";
    }

}
