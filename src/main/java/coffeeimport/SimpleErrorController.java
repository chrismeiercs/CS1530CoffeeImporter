package coffeeimport;/**
 * Created by Chris on 7/16/2015.
 */

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class SimpleErrorController implements ErrorController {


    private static final String PATH = "/error";

    //when redirected to the error url, show the updateFailure Page
    @RequestMapping(value = PATH)
    public String error() {

        return "updateFailure";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}