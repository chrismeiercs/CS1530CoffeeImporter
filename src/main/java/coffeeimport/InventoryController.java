package coffeeimport;/**
 * Created by Chris on 7/16/2015.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(Model model) {

        return "inventory";
    }

}