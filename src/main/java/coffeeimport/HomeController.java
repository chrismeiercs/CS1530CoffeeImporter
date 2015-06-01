package coffeeimport;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController{
	@RequestMapping(method=RequestMethod.GET)
	public String printWelcome(Model model){
		model.addAttribute("message","Welcome to Coffee Importer Project!");
		return "main";
	}
	
	

}
