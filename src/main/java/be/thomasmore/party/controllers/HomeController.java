package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
public class HomeController {
    private final Venue[] venue = {};

    private final String dayToday = LocalDate.now().format(DateTimeFormatter.ofPattern("E"));

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime payDay = now.plusDays(30);

    String dateTimeString1 = now.format(formatter);
    String dateTimeString2 = payDay.format(formatter);

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/pay")
    public String pay(Model model) {
        model.addAttribute("dayToday", dayToday);
        model.addAttribute("dateNow", dateTimeString1);
        model.addAttribute("payDay", dateTimeString2);
        return "pay";
    }

    @GetMapping("/venuelist")
    public String venuelist(Model model) {
        model.addAttribute("venue", venue);
        return "venuelist";
    }

    @GetMapping({"/venuedetails", "/venuedetails/{index}"})
    public String venueDetails(Model model,
                               @PathVariable(required = false)  Integer index) {
        if (index!=null && index>=0 && index<venue.length ) {
            model.addAttribute("venue", venue[index]);
            model.addAttribute("prevIndex", index>0 ? index-1 : venue.length-1);
            model.addAttribute("nextIndex", index<venue.length-1 ? index+1 : 0);
        }
        return "venuedetails";
    }
}
