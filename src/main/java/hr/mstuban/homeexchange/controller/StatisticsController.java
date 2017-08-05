package hr.mstuban.homeexchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by mstuban on 05/08/17.
 */

@Controller
public class StatisticsController {

    @GetMapping("/generateStatistics")
    public String generateStatistics(Model model) {


        return "statistics";
    }

}
