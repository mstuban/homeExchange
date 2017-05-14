package hr.mstuban.homeexchange.controller;

import hr.mstuban.homeexchange.domain.Address;
import hr.mstuban.homeexchange.services.AddressService;
import hr.mstuban.homeexchange.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Created by mstuban on 13.05.17..
 */
@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/home")
    public String getHome(Model model) {
        return "home";
    }

    @PostMapping("/searchForHomes")
    public String searchForHomes(Model model, @RequestParam(value = "searchParameter") String searchParameter) {
        List<Address> addresses = addressService.findByParameter(searchParameter);
        model.addAttribute("addresses", addresses);

        return "home-search-results";
    }

    @GetMapping("/homes")
    public String getAllHomes(Model model) {
        model.addAttribute("allHomes", homeService.findAll());

        return "all-homes";
    }

}


