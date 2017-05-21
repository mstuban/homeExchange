package hr.mstuban.homeexchange.controller;

/**
 * Created by mstuban on 09.05.17..
 */

import hr.mstuban.homeexchange.domain.form.NewUserForm;
import hr.mstuban.homeexchange.services.UserRoleService;
import hr.mstuban.homeexchange.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("/")
    String getHome() {
        return "redirect:/home";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(Model model) {

        model.addAttribute("registerForm", new NewUserForm());
        model.addAttribute("userRoles", userRoleService.findAll());

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleUserCreateForm(@ModelAttribute("registerForm") NewUserForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            userService.createUser(form);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("email.exists", "Email already exists");
            return "register";
        }
        return "redirect:/";
    }
}