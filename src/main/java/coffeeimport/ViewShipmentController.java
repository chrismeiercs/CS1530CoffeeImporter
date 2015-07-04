package coffeeimport;/**
 * Created by Chris on 7/4/2015.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

//maps the view shipment page to the shipmentviewer address
@Controller
@RequestMapping("/shipmentviewer")
public class ViewShipmentController {

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(Model model) {

        return "ViewShipments";
    }

}