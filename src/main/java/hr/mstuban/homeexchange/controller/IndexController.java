package hr.mstuban.homeexchange.controller;

/**
 * Created by mstuban on 09.05.17..
 */

import hr.mstuban.homeexchange.domain.form.NewUserForm;
import hr.mstuban.homeexchange.services.UserService;
import hr.mstuban.homeexchange.validator.NewUserFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private NewUserFormValidator newUserFormValidator;

    @RequestMapping("/")
    String getHome() {
        return "redirect:/home";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(Model model) {

        model.addAttribute("newUserForm", new NewUserForm());

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("newUserForm") NewUserForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            userService.createUser(form);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("email.exists", "Email already exists");
            return "register";
        }

        redirectAttributes.addFlashAttribute("registrationSuccess", "You have successfully created an account! Please login!");

        return "redirect:/login";
    }


    // form validators
    @InitBinder("newUserForm")
    public void addNewUserFormValidator(WebDataBinder dataBinder) {
        dataBinder.addValidators(newUserFormValidator);
    }

}