package coffeeimport;

/**
 * Created by George  on 7/1/2015.
 * *
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewProductController
{

    @RequestMapping(value="/newproduct",method = RequestMethod.GET)
    public String beginProduct(Model model)
    {
        model.addAttribute("product", new Product());
        return "newShipment";
    }

    @RequestMapping(value="/newproduct", method=RequestMethod.POST)
    public String addProductData(@ModelAttribute Product product, Model model, BindingResult bindingResult)
    {
        model.addAttribute("productId", product.getProductId());
        model.addAttribute("productName", product.getProductName());
        model.addAttribute("productCost", product.getProductCost());
        model.addAttribute("hasBeenSold", product.getHasBeenSold());
        model.addAttribute("priceSold", product.getPriceSold());

        ParseAccessor parse = new ParseAccessor();
        if(parse.updateProduct(product))
        {
            return "updateSuccess";
        }
        else
        {
            return "updateFailure";
        }
    }

}