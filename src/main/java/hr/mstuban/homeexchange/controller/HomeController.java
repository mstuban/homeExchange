package hr.mstuban.homeexchange.controller;

import hr.mstuban.homeexchange.domain.Address;
import hr.mstuban.homeexchange.domain.Home;
import hr.mstuban.homeexchange.domain.User;
import hr.mstuban.homeexchange.domain.form.EditHomeForm;
import hr.mstuban.homeexchange.domain.form.NewHomeForm;
import hr.mstuban.homeexchange.domain.mapper.HomeMapper;
import hr.mstuban.homeexchange.services.*;
import hr.mstuban.homeexchange.validator.EditHomeFormValidator;
import hr.mstuban.homeexchange.validator.NewHomeFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Objects;


/**
 * Created by mstuban on 13.05.17..
 */
@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private HomeMapper homeMapper;

    @Autowired
    private NewHomeFormValidator newHomeFormValidator;

    @Autowired
    private EditHomeFormValidator editHomeFormValidator;

    @GetMapping("/home")
    public String getHome(Model model) {

        List<User> users = userService.findAll();

        Integer usersWithHomesAddedCounter = 0;

        for (User user : users) {
            if (user.getHomes().size() > 0) {
                usersWithHomesAddedCounter++;
            }
        }

        Home home = homeService.findHomeWithMostTimeExchanged();

        model.addAttribute("usersWithHomesAddedCounter", usersWithHomesAddedCounter);
        model.addAttribute("userCount", users.size());
        model.addAttribute("homeCount", homeService.findAll().size());
        model.addAttribute("userWithLongestStay", home.getUser().getFirstName() +  ' '  + home.getUser().getLastName());
        model.addAttribute("numberOfCountries", addressService.getByCountryIsUnique().size());
        model.addAttribute("messagesSentCount", messageService.findAll().size());

        return "home";


    }

    @PostMapping("/searchForHomes")
    public String searchForHomes(Model model, @RequestParam(value = "searchParameter") String searchParameter, Principal principal) {

        List<Address> addresses = addressService.findAddressesBySearchParameter(searchParameter);
        model.addAttribute("addresses", addresses);

        return "home-search-results";
    }

    @GetMapping("/searchForMyHomes")
    public String searchForHomes(Model model, Principal principal) {

        List<Address> addresses = addressService.getAddressesByHome_User_UserName(principal.getName());
        model.addAttribute("addresses", addresses);

        return "home-search-results";
    }

    @GetMapping("/homes")
    public String getAllHomes(Model model) {

        List<Address> addresses = addressService.findAddressesBySearchParameter("");
        model.addAttribute("addresses", addresses);

        return "home-search-results";
    }

    @RequestMapping(value = "/home/new", method = RequestMethod.GET)
    public String getHomeForm(Model model) {

        model.addAttribute("newHomeForm", new NewHomeForm());

        return "new-home-form";
    }

    @RequestMapping(value = "/home/new", method = RequestMethod.POST)
    public String newHomeFormSubmit(@Valid @ModelAttribute("newHomeForm") NewHomeForm newHomeForm, BindingResult bindingResult, Principal principal, RedirectAttributes redirectAttributes, Model model) {

        if (bindingResult.hasErrors()) {
            return renderNewNewHomeForm(model);
        }

        Home home = homeMapper.newHomeFormToHome(newHomeForm);
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

        redirectAttributes.addFlashAttribute("homeCreatedSuccess", "You have successfully added a new home!");

        return "redirect:/homes";
    }

    @GetMapping("/home/delete/{id}")
    public String deleteHomeById(@PathVariable Long id, Model model, Principal principal, RedirectAttributes redirectAttributes) {

        Home home = homeService.findById(id);

        if (!Objects.equals(home.getUser().getUserName(), principal.getName()) && !((UsernamePasswordAuthenticationToken) principal).getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            redirectAttributes.addFlashAttribute("failedToDeleteError", "You are not the owner of that home, so therefore, you cannot delete it.");
            return "redirect:/homes";
        }

        if (home.getImage() != null) {
            Long imageId = home.getImage().getId();
            home.setImage(null);
            imageService.delete(imageId);
        }

        addressService.deleteByHome_HomeId(home.getHomeId());

        redirectAttributes.addFlashAttribute("deleteSuccess", "You have successfully deleted your home!");

        return "redirect:/homes";
    }

    @GetMapping("/home/edit/{userHomeId}")
    public String editHome(@PathVariable Long userHomeId, Model model, Principal principal, RedirectAttributes redirectAttributes) {

        Home home = homeService.findById(userHomeId);

        if (!Objects.equals(home.getUser().getUserName(), principal.getName()) && !((UsernamePasswordAuthenticationToken) principal).getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            redirectAttributes.addFlashAttribute("failedToEditError", "You are not the owner of that home, so therefore, you cannot edit it.");
            return "redirect:/homes";
        }

        EditHomeForm editHomeForm = homeMapper.homeToEditHomeForm(home);

        model.addAttribute("homeId", userHomeId);

        Address address = addressService.findByHome_HomeId(userHomeId);

        editHomeForm.setId(userHomeId);

        editHomeForm.setCountry(address.getCountry());
        editHomeForm.setCity(address.getCity());
        editHomeForm.setStreet(address.getStreet());
        editHomeForm.setPostalCode(address.getPostalCode());

        model.addAttribute("editHomeForm", editHomeForm);

        return "edit-home";

    }

    @RequestMapping(value = "/home/edit/{userHomeId}", method = RequestMethod.POST)
    public String handleUserEditForm(@Valid @ModelAttribute("editHomeForm") EditHomeForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, Principal principal, @PathVariable Long userHomeId) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("homeId", userHomeId);
            return renderNewEditHomeForm(model);
        }

        try {
            Address address = addressService.findByHome_HomeId(userHomeId);
            Long addressId = address.getAddressId();
            addressService.editAddress(form.getCity(), form.getCountry(), form.getPostalCode(), form.getStreet(), addressId);
            homeService.editHome(form.getName(), form.getSizeInSquareMeters(), form.getDescription(), form.getTimeOfExchangeInMonths(), form.getType(), form.isAvailable(), userHomeId);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("dataIntegrityException", e.getMessage());
            return "redirect:/home/edit/" + userHomeId;
        }

        redirectAttributes.addFlashAttribute("editHomeSuccess", "You have successfully edited your home!");

        return "redirect:/home/edit/" + userHomeId;
    }

    @RequestMapping("/home/{homeId}/deleteImage/{imageId}")
    public String deleteHomeImage(@PathVariable Long homeId, @PathVariable("imageId") Long imageId, RedirectAttributes redirectAttributes) {

        homeService.findById(homeId).setImage(null);
        imageService.delete(imageId);

        redirectAttributes.addFlashAttribute("imageDeletedSuccessfully", "Image deleted successfully!");
        return "redirect:/homes";

    }

    @ResponseBody
    @RequestMapping("/get-addresses")
    public List<Address> getHomes(@RequestParam(name = "q") String query) {
        return addressService.findAddressesBySearchParameter(query);
    }

    private String renderNewEditHomeForm(Model model) {
        return "edit-home";
    }

    private String renderNewNewHomeForm(Model model) {
        return "new-home-form";
    }

    @InitBinder("newHomeForm")
    public void addNewHomeFormValidator(WebDataBinder dataBinder) {
        dataBinder.addValidators(newHomeFormValidator);
    }

    @InitBinder("editHomeForm")
    public void addNewEditFormValidator(WebDataBinder dataBinder) {
        dataBinder.addValidators(editHomeFormValidator);
    }

}


