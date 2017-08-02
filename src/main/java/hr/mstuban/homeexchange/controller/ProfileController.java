package hr.mstuban.homeexchange.controller;

import hr.mstuban.homeexchange.domain.User;
import hr.mstuban.homeexchange.domain.form.EditUserForm;
import hr.mstuban.homeexchange.domain.mapper.UserMapper;
import hr.mstuban.homeexchange.services.HomeService;
import hr.mstuban.homeexchange.services.UserService;
import hr.mstuban.homeexchange.validator.EditUserFormValidator;
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
import java.security.Principal;

/**
 * Created by mstuban on 01/08/17.
 */

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private HomeService homeService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EditUserFormValidator editUserFormValidator;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfile(Model model, Principal principal) {

        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("numberOfHomes", homeService.countByUser_UserId(userService.findByUsername(principal.getName()).getUserId()));

        return "profile";
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public String editProfile(Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());

        EditUserForm editUserForm = userMapper.userToForm(user);

        editUserForm.setId(user.getUserId());

        model.addAttribute("editUserForm", editUserForm);

        return "edit-profile";
    }

    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public String handleUserEditForm(@Valid @ModelAttribute("editUserForm") EditUserForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "edit-profile";
        }
        try {
            User user = userService.findByUsername(principal.getName());
            userService.editUser(form, user.getUserId());
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("email.exists", "Email already exists");
            return "edit-profile";
        }

        return "redirect:/logout";
    }

    @InitBinder("editUserForm")
    public void addEditUserFormValidator(WebDataBinder dataBinder) {
        dataBinder.addValidators(editUserFormValidator);
    }

}
