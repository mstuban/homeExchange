package hr.mstuban.homeexchange.controller;

import hr.mstuban.homeexchange.domain.Message;
import hr.mstuban.homeexchange.domain.User;
import hr.mstuban.homeexchange.domain.form.NewMessageForm;
import hr.mstuban.homeexchange.domain.mapper.MessageMapper;
import hr.mstuban.homeexchange.services.MessageService;
import hr.mstuban.homeexchange.services.UserService;
import hr.mstuban.homeexchange.validator.NewMessageFormValidator;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by mstuban on 03/08/17.
 */

@Controller
@RequestMapping("/message")
public class MessageController {


    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private NewMessageFormValidator newMessageFormValidator;

    @GetMapping("{id}")
    public String getMessage(@PathVariable Long id, Model model, Principal principal, RedirectAttributes redirectAttributes) {

        Message message = messageService.findById(id);

        if (message == null) {
            redirectAttributes.addFlashAttribute("messageDoesNotExist", "That message does not exist! You were redirected to the home page.");
            return "redirect:/home";
        }

        if (!principal.getName().equals(message.getAuthor()) && !principal.getName().equals(message.getReceiver().getUserName()) && !((UsernamePasswordAuthenticationToken) principal).getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            redirectAttributes.addFlashAttribute("messageViewFailed", "You cannot view this message!");
            return "redirect:/home";
        }

        if (!message.isHasBeenRead() && principal.getName().equals(message.getReceiver().getUserName())) {
            messageService.updateIsMessageRead(true, id);
        }

        model.addAttribute("message", message);

        return "message";

    }

    @GetMapping("/new")
    public String getMessageForm(Model model) {

        List<User> users = userService.findAll();

        model.addAttribute("users", users);

        NewMessageForm newMessageForm = new NewMessageForm();

        model.addAttribute("newMessageForm", newMessageForm);

        return "new-message";
    }

    @GetMapping("/new/{receiver}")
    public String getMessageForm(Model model, @PathVariable(required = false) String receiver) {

        List<User> users = userService.findAll();

        model.addAttribute("users", users);

        NewMessageForm newMessageForm = new NewMessageForm();

        if (receiver != null) {
            newMessageForm.setReceiver(receiver);
        }

        model.addAttribute("newMessageForm", newMessageForm);

        return "new-message";
    }


    @PostMapping("/new")
    public String sendMessage(@Valid @ModelAttribute("newMessageForm") NewMessageForm newMessageForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, Principal principal) {

        if (bindingResult.hasErrors()) {
            return "new-message";
        }

        Message message = messageMapper.newMessageFormToMessage(newMessageForm);

        message.setAuthor(principal.getName());
        message.setCreatedOn(LocalDateTime.now());
        messageService.store(message);

        redirectAttributes.addFlashAttribute("messageSentSuccess", "Message sent successfully!");

        return "redirect:/message/" + message.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteMessage(@PathVariable Long id, HttpServletRequest request, RedirectAttributes redirectAttributes, Principal principal) {

        Message message = messageService.findById(id);

        if (!principal.getName().equals(message.getAuthor()) && !principal.getName().equals(message.getReceiver().getUserName()) && !((UsernamePasswordAuthenticationToken) principal).getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            redirectAttributes.addFlashAttribute("messageDeleteFailed", "You cannot delete this message!");
            return "redirect:/home";
        }

        String referer = request.getHeader("Referer");

        messageService.delete(id);

        redirectAttributes.addFlashAttribute("messageDeletedSuccess", "Message deleted successfully!");

        return "redirect:" + referer;

    }


    @InitBinder("newMessageForm")
    public void addNewMessageFormValidator(WebDataBinder dataBinder) {
        dataBinder.addValidators(newMessageFormValidator);
    }


}
