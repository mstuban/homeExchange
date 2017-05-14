package hr.mstuban.homeexchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by mstuban on 13.05.17..
 */
@Controller
public class HomeController {

    @GetMapping("/home")
    public String getHome(Model model) {

        return "home";
    }

    @PostMapping("/searchForHomes")
    public String searchForHomes(Model model, @RequestParam(value = "location") String address) {


        return "home-search-results";
    }
}
