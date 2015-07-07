package coffeeimport;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by George Totolos on 7/6/2015.
 */
@Controller
@RequestMapping("/inventory")

public class InventoryController
{

    @RequestMapping(method= RequestMethod.GET)
    public String printWelcome(Model model){
        return "inventory";
    }


}
