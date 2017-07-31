package hr.mstuban.homeexchange.controller;

import hr.mstuban.homeexchange.domain.Address;
import hr.mstuban.homeexchange.domain.Home;
import hr.mstuban.homeexchange.domain.form.NewHomeForm;
import hr.mstuban.homeexchange.domain.mapper.HomeMapper;
import hr.mstuban.homeexchange.services.AddressService;
import hr.mstuban.homeexchange.services.HomeService;
import hr.mstuban.homeexchange.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


/**
 * Created by mstuban on 13.05.17..
 */
@Controller
public class HomeController {

    private final HomeService homeService;

    private final AddressService addressService;

    private final UserService userService;

    @Autowired
    private HomeMapper homeMapper;

    @Autowired
    public HomeController(HomeService homeService, AddressService addressService, UserService userService) {
        this.homeService = homeService;
        this.addressService = addressService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String getHome(Model model, Principal principal) {
        return "home";
    }

    @PostMapping("/searchForHomes")
    public String searchForHomes(Model model, @RequestParam(value = "searchParameter") String searchParameter, Principal principal) {
        List<Address> addresses = addressService.findAddressesBySearchParameter(searchParameter);
        model.addAttribute("addresses", addresses);
        model.addAttribute("username", principal.getName());

        return "home-search-results";
    }

    @GetMapping("/homes")
    public String getAllHomes(Model model) {
        List<Address> addresses = addressService.findAddressesBySearchParameter("");
        model.addAttribute("addresses", addresses);

        return "home-search-results";
    }

    @GetMapping("/home/new")
    public String getHomeForm(Model model) {
        model.addAttribute("newHomeForm", new NewHomeForm());

        return "new-home-form";
    }

    @RequestMapping(value = "/home/new", method = RequestMethod.POST)
    public String locationFormSubmit(@ModelAttribute("newHomeForm") NewHomeForm newHomeForm, Model model, Principal principal) {

        Home home = homeMapper.formToHome(newHomeForm);
        home.setUser(userService.findByUsername(principal.getName()));

        Address address = new Address();
        address.setCity(newHomeForm.getCity());
        address.setHome(home);
        address.setCountry(newHomeForm.getCountry());
        address.setPostalCode(newHomeForm.getPostalCode());
        address.setStreet(newHomeForm.getStreet());

        home.setAddress(address);

        addressService.save(address);
        homeService.save(home);
        return "redirect:/homes";
    }

    @GetMapping("/home/delete/{id}")
    public String deleteHomeById(@PathVariable Long id, Model model){

        homeService.deleteById(id);

        return "redirect:/homes";
    }

}


