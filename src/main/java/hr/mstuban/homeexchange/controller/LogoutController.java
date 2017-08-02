package hr.mstuban.homeexchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by mstuban on 01/08/17.
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {

    @RequestMapping(method= RequestMethod.GET)
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login?editProfileSuccess";
    }
}
