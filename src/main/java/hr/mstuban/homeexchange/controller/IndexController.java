package hr.mstuban.homeexchange.controller;

/**
 * Created by mstuban on 09.05.17..
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    String getHome(){
        return "redirect:/home";
    }
}