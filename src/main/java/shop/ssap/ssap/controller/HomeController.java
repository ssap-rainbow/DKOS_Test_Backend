package shop.ssap.ssap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(origins = "*") 
@Controller
public class HomeController {

    @RequestMapping(value="/api/home", method= RequestMethod.GET)
    @ResponseBody
    public String home() {
        return "helloworld";
    }
}
