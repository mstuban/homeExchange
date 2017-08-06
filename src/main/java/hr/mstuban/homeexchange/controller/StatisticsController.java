package hr.mstuban.homeexchange.controller;


import hr.mstuban.homeexchange.controller.facade.StatisticsFacade;
import hr.mstuban.homeexchange.domain.Home;
import hr.mstuban.homeexchange.domain.form.GenerateStatisticsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mstuban on 05/08/17.
 */

@Controller
public class StatisticsController {

    @Autowired
    private StatisticsFacade statisticsFacade;

    @GetMapping("/statistics")
    public String getStatisticsForm(Model model) {

        model.addAttribute("generateStatisticsForm", new GenerateStatisticsForm());

        return "generate-statistics-form";
    }

    @PostMapping("/generateStatistics")
    public String generateStatistics(@Valid @ModelAttribute("generateStatisticsForm") GenerateStatisticsForm generateStatisticsForm, Model model) {


        Iterable<Home> homes = statisticsFacade.searchHomeByFilter(generateStatisticsForm);
        List<Home> homeList = new ArrayList<>();

        homes.forEach(homeList::add);

        Integer numberOfHomesExchanged = homeList.size();
        double averageStay = 0;
        Integer longestStay = 0;
        String mostVisitedCountry = "No data";
        Integer initialFrequencyOfCountry = 0;
        Integer frequencyOfCountry;
        Integer timeOfExchangeSum = 0;

        List<String> countries = new ArrayList<>();

        for (Home home : homes) {
            countries.add(home.getAddress().getCountry());
        }

        for (Home home : homes) {

            frequencyOfCountry = Collections.frequency(countries, home.getAddress().getCountry());
            if (frequencyOfCountry > initialFrequencyOfCountry) {
                mostVisitedCountry = home.getAddress().getCountry();
            }

            timeOfExchangeSum += home.getTimeOfExchangeInMonths();

            if (home.getTimeOfExchangeInMonths() > longestStay) {
                longestStay = home.getTimeOfExchangeInMonths();
            }
        }

        if (timeOfExchangeSum > 0) {
            averageStay = (timeOfExchangeSum / (double) homeList.size());
        }

        model.addAttribute("homeList", homeList);
        model.addAttribute("numberOfHomesExchanged", numberOfHomesExchanged);
        model.addAttribute("averageStay", averageStay);
        model.addAttribute("longestStay", longestStay);
        model.addAttribute("mostVisitedCountry", mostVisitedCountry);

        return "statistics";
    }

}
